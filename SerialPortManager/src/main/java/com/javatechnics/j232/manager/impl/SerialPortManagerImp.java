/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javatechnics.j232.manager.impl;

import com.javatechnics.j232.manager.SerialPortManager;
import com.javatechnics.j232.manager.exception.PortNotAvailable;
import com.javatechnics.rs232.flags.OpenFlags;
import com.javatechnics.rs232.port.Serial;
import com.javatechnics.rs232.port.impl.SerialImpl;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class SerialPortManagerImp implements SerialPortManager {
    
    private static SerialPortManagerImp serialPortManagerImp = null;
    private final Map<Serial,String> serialPortsInUse = Collections.synchronizedMap( new 
            WeakHashMap<Serial, String>());
    private ReentrantLock portlLock = new ReentrantLock();
    private final ReferenceQueue<Serial> serialReferenceQueue = new ReferenceQueue<Serial>();
    
    private SerialPortManagerImp(){
        
    }
    
    public static final synchronized SerialPortManagerImp getInstance(){
        return serialPortManagerImp == null ? 
                serialPortManagerImp = new SerialPortManagerImp() : 
                serialPortManagerImp;
    }

    public List<String> listSerialPorts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> listSerialPorts(PortTypes portType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getPortPrefixes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPortPrefixes(List<String> prefixes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void appendPortPrefixes(List<String> prefixes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Attempts to obtain the specified serial port. An attempt is made to obtain
     * the specified device using an empty set of OpenFlags.
     * @param device the full path string to the device to open.
     * @return the Serial device if successful.
     * @throws PortNotAvailable if the specified is already in use
     * @throws IOException typically thrown from the native library e.g. if
     * the specified port does not physically exist.
     */
    public Serial obtainSerialPort(String device) throws PortNotAvailable, IOException {
        return obtainSerialPort(device, EnumSet.noneOf(OpenFlags.class));
    }

    /**
     * Attempts to obtain the specified serial port using the specified OpenFlags.
     * @param device the full pathe string to the device to obtain.
     * @param openFlags an EnumSet of OpenFlags to specify to the underlying 
     * native library.
     * @return the opened serial port.
     * @throws PortNotAvailable if the specified port is not available.
     * @throws IOException typically thrown if an exception occurs in the 
     * underlying native library.
     */
    public Serial obtainSerialPort(String device, EnumSet<OpenFlags> openFlags) throws PortNotAvailable, IOException {
        Serial serial = null;
         try {
            if ( ! portlLock.tryLock(3, TimeUnit.SECONDS)) throw new 
                PortNotAvailable("Cannot obtain lock while attempting to obtain serial port."); 
            synchronized (serialPortsInUse){
                 if (serialPortsInUse.containsValue(device)){
//                    if ( ! serialPortsInUse.get(device).isEnqueued()){
                       throw new PortNotAvailable("Port " + device + " already in use.");
//                    }
                }
                serial = new SerialImpl();
                serial.open(device, openFlags);
                serialPortsInUse.put(serial, device);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(SerialPortManagerImp.class.getName()).log(Level.SEVERE, null, ex);
            throw new PortNotAvailable(ex.getLocalizedMessage());
        } finally {
            portlLock.unlock();
        }
        return serial;
    }

    /**
     * Release the specified serial port. Currently this does nothing as the 
     * manager will only allow the serial port to be used if there are no further 
     * strong references to it.
     * @param serialPort
     * @return always returns TRUE.
     */
    public boolean releaseSerialPort(Serial serialPort) {
        // No operation.
        return true;
    }
    
}