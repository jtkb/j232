/*
 * Copyright (C) 2014 Kerry Billingham <contact@AvionicEngineers.com>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.javatechnics.rs232;

import com.javatechnics.rs232.struct.TermIOS;
import com.javatechnics.rs232.flags.IOCTRLRequests;
import com.javatechnics.rs232.flags.ModemControlFlags;
import com.javatechnics.rs232.flags.OpenFlags;
import com.javatechnics.rs232.flags.QueueSelector;
import com.javatechnics.rs232.flags.TerminalControlActions;
import com.javatechnics.rs232.stream.SerialPortDataInputStream;
import com.javatechnics.rs232.stream.SerialPortDataOutputStream;
import com.javatechnics.rs232.utility.BitOperation;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kerry Billingham <java@avionicengineers.com>.
 */
public class Serial implements Closeable, Openable {
    /**
     * This holds the native file descriptor returned from openSerialPort().
     */
    private int fileDescriptor = -1;
    
    /**
     * This field holds a flag to indicate if the hardware serial port is open.
     */
    private boolean portOpen = false;
    
    /**
     * The InputStream object for the serial port.
     */
    private SerialPortDataInputStream serialPortDataInputStream = null;
    /**
     * The OutputStream object for the serial port.
     */
    private SerialPortDataOutputStream serialPortDataOutputStream = null;
    /**
     * Returns the version number of the underlying native library.
     * @return the version number of the library.
     */
    public native String getNativeLibraryVersion();
    
    /**
     * Call through to the native library to open the specified device with
     * the specified flags.
     * 
     * @param deviceFile String describing the serial port to open e.g. "/dev/ttyS0"
     * for COM1.
     * @param flags representing the access mode for the file. See fcntl.h for
     * details.
     * @return the underlying native file-descriptor if successful or -1 if failed.
     * @throws IOException IOException is thrown if the underlying native call
     * to open fails.
     */
    private native int openSerialPort(String deviceFile, int flags) throws IOException;
    
    /**
     * Call through to the native library to close the specified device file.
     * @param deviceFile integer value representing the native file descriptor.
     * @return 0 upon success -1 on error
     * @throws IOException
     */
    private native int closeSerialPort(int deviceFile) throws IOException;
    
    /**
     * Call through to the native library to set the terminal (serial port)
     * settings.
     * @param fileDescriptor the file descriptor of the serial port.
     * @param terminalControlAction integer value indicating how the 
     * settings should be applied. Use the values found in 
     * {@link com.javatechnics.rs232.TerminalControlActions}.
     * @param termios A {@link com.javatechnics.rs232.TermIOS} class which
     * holds flags to be applied to the serial port.
     * @return 0 upon success, -1 upon fail to apply settings AND failure
     * to throw an exception if an error occurred in the native code.
     * @throws IOException throws IOException if an error occurred.
     */
    private native int setNativeTerminalAttributes(int fileDescriptor,
                                                    int terminalControlAction,
                                                    TermIOS termios) throws 
                                                        IOException;
    
    /**
     * Call through to the native library to get the serial port settings.
     * @param fileDescriptor the file decriptor of the serial port
     * @return a {@link com.javatechnics.rs232.TermIOS} object representing
     * the serial port's current settings or NULL if an error occurs and an 
     * exception is not thrown.
     * @throws IOException if an error occurs.
     */
    private native TermIOS getNativeTerminalAttributes(int fileDescriptor)
                                                    throws IOException;
    
    
    /**
     * Call through to native ioctl() function to get the Modem control/serial
     * port settings.
     * @param fileDescriptor the file descriptor of the serial port.
     * @param requestFlags integer value indicating the requested settings. This
     * value should be OR'd {@link com.javatechnics.rs232.IOCTRLRequests}
     * values. They will be transformed to the native values in this function.
     * @return the current modem control bits or -1 if an error and no exception
     * was thrown.
     * @throws IOException  if an error occurs.
     */
    private native int getNativeModemControlBits(int fileDescriptor,
                                                    int requestFlags)
                                                    throws IOException;
    /**
     * Call through to native ioctl() to set the Modem control bits.
     * @param fileDescriptor file descriptor of the serial port.
     * @param setFlags the modem control bits to set
     * @return 0 upon success -1 if an error and IOException not thrown.
     * @throws IOException if an error occurs.
     */
    private native int setNativeModemcontrolBits(int fileDescriptor,
                                                    int setFlags)
                                                    throws IOException;
    
    /**
     * Call through to the native tcflush() function to flush the input and/or
     * output queues. See man tcflush for more information.
     * @param fileDescriptor file descriptor of the serial port.
     * @param queueSelector The input and/or output queue as determined by
     * the flags defined in termios.h
     * @return 0 upon success -1 if an error occured and IOException not thrown.
     * @throws IOException if an error occured.
     */
    private native int nativeTCFlush(int fileDescriptor, int queueSelector)
                                                    throws IOException;
    
    static {
        /*
        * The following must NOT be removed. This and its accompanying native library
        * are intended for NON-Windows platforms. If the following check is removed
        * then you are responsible for any consequences that occur as a result.
        */
        if(System.getProperty("os.name").toLowerCase().contains("windows"))
            throw new RuntimeException("This library does not run on Windows.");
        
        //Load Native Library here.
        System.loadLibrary("j232");    
    }
    
    /**
     * This is a helper function that converts a Set of EnumValue objects
     * into an OR-ed single int value.
     * @param <T> any type that extends EnumValue.
     * @param flags A Set of EnumValues.
     * @return a single int that represents the OR-ed values within the specified
     * set.
     */
    public  static <T extends EnumValue> int orValues(Set<T> flags){
        int f = 0;
        Iterator<T> it = flags.iterator();
        while (it.hasNext()) {
            f |= it.next().getValue();
        }
        return f;
    }

    /**
     * Returns the underlying OutputStream.
     * @return the OutputStream
     * @throws IOException if an I/O error occurs including if outputstream
     * is NULL or closed.
     */
    public OutputStream getOutputStream() throws IOException {
        if (serialPortDataOutputStream == null) throw new IOException();
        return serialPortDataOutputStream;
    }
 
    /**
     * Returns the InputStream associated with the serial port.
     * @return the InputStream
     * @throws IOException if an I/O error occurs including if inputstream
     * is null.
     */
    public InputStream getInputStream() throws IOException{
        if (serialPortDataInputStream == null) throw new IOException();
        return serialPortDataInputStream;
    }

    /**
     * Returns the DataOutputStream associated with the serial port.
     * @return the DataOutputStream object.
     * @throws IOException if the DataOutputStream object is NULL.
     */
    public SerialPortDataOutputStream getDataOutputStream() throws IOException{
        if (serialPortDataOutputStream == null) throw new IOException("DataOutputStream is null.");
        return serialPortDataOutputStream;
    }
    
    /**
     * Returns the DataInputStream associated with the serial port.
     * @return the DataInputStream object.
     * @throws IOException if the DataInputStream object is NULL.
     */
    public SerialPortDataInputStream getDataInputStream() throws IOException{
        if (serialPortDataInputStream == null) throw new IOException("DataInputStream is null.");
        return serialPortDataInputStream;
    }
    
    /**
     * Implementation of the Closeable interface. Call this method when the
     * serial port is to be closed.
     * @throws IOException if an IOException occurs during call.
     */
    public void close() throws IOException {
        if (!portOpen) throw new IOException("Serial port already closed.");
        int return_value = closeSerialPort(fileDescriptor);
        if (return_value == -1) throw new IOException("");
        serialPortDataInputStream = null;
        portOpen = false;
        fileDescriptor = -1;    // Restore FD to -1
    }

    /**
     * Opens the underlying hardware serial port.
     * @param path the file-system path to the device e.g. /dev/ttyS0.
     * @param flags flags used to open the device with. See 
     * {@link com.javatechnics.rs232.OpenFlags}.
     * @throws IOException
     * @return the boolean
     */
    public boolean open(String path, EnumSet<OpenFlags> flags) throws IOException {
        if ((fileDescriptor = openSerialPort(path, orValues(flags))) > -1){
            portOpen = true;
            serialPortDataInputStream = 
                    new SerialPortDataInputStream(fileDescriptor);
            serialPortDataOutputStream = 
                    new SerialPortDataOutputStream(fileDescriptor);
        }
        return portOpen;
    }
    
    /**
     * Returns a boolean indicating whether the underlying hardware serial
     * port has been opened or not.
     * @return TRUE if the port is open, FALSE otherwise.
     */
    public boolean isOpen(){
        return portOpen;
    }
    
    /**
     * Call this method to get the underlying serial port control bits. This
     * uses a non-POSIX compliant call to ioctl() so get/setTerminalAttributes()
     * should be used if POSIX compliance is required. However ioctl allows the
     * getting of Modem control lines as required (DSR, RTS etc.)
     * @return the current control bits or -1 if error and IOException was not
     * thrown.
     * @throws IOException 
     */
    public EnumSet<ModemControlFlags> getModemControlBits() throws IOException{
        Set<ModemControlFlags> enumFlags = new HashSet<ModemControlFlags>();
        int flags = getNativeModemControlBits(fileDescriptor, 
                                        IOCTRLRequests.TIOCMGET.value);
        for (ModemControlFlags mcf : ModemControlFlags.values()){
            if ((mcf.getValue() & flags) == mcf.getValue()){
                enumFlags.add(mcf);
            }
        }
        return enumFlags.isEmpty() ? EnumSet.noneOf(ModemControlFlags.class) :
                                        EnumSet.copyOf(enumFlags);
    }
    
    /**
     * Call this method to set the control bits on the underlying serial port.
     * This is a non-POSIX compliant call through to ioctl().
     * @param flags and EnumSet of 
     * {@link com.javatechnics.rs232.flags.ModemControlFlags} indicating which
     * control line to set.
     * @return 0 upon success, -1 if an error occurred and an exception not
     * thrown.
     * @throws IOException if an error occurs.
     */
    public int setModemControlbits(EnumSet<ModemControlFlags> flags) throws IOException{
        
        return setNativeModemcontrolBits(fileDescriptor, 
                BitOperation.orValues(flags));
    }
    
    /**
     * Call this method to set control attributes on the underlying serial
     * port.
     * @param terminalControlAction integer flag. 
     * See {@link com.javatechnics.rs232.TerminalControlActions}
     * @param termios a {@link com.javatechnics.rs232.TermIOS} class that
     * describes the desired control modes.
     * @return 0 upon success, -1 if an error occurred in the native code
     * @throws IOException 
     */
    public int setTerminalAttributes(TerminalControlActions terminalControlAction, TermIOS termios)
            throws IOException {
        if (termios == null) throw new IOException("TermIOS structure is null.");
        return setNativeTerminalAttributes(fileDescriptor, 
                terminalControlAction.getValue(), 
                termios);
    }
    
    /**
     * Call this method to retrieve the control attributes set on the underlying
     * serial port.
     * @return a TermIOS object representing the termios struct for the serial port.
     * @throws IOException 
     */
    public TermIOS getTerminalAttributes()
            throws IOException{
        return getNativeTerminalAttributes(fileDescriptor);
    }
    
    /**
     * Call this method to flush the input, output or both data queues.
     * @param queueSelector one of 
     * {@link com.javatechnics.rs232.flags.QueueSelector}
     * @return 0 upon success, -1 if an error occurred and IOException not
     * thrown.
     * @throws IOException if an error occurs.
     */
    public int flushQueue(QueueSelector queueSelector) throws IOException{
        return nativeTCFlush(fileDescriptor, queueSelector.value);
    }
    
    @Override
    protected void finalize(){
        System.out.println("Finalize() method called.");
        try {
            closeSerialPort(fileDescriptor);
        } catch (IOException ex) {
            Logger.getLogger(Serial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
