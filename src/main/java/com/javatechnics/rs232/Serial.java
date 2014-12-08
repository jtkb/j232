/*
 * Kerry Billingham <java@avionicengineers.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javatechnics.rs232;

import com.javatechnics.rs232.stream.SerialPortInputStream;
import com.javatechnics.rs232.stream.SerialPortOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author kerry
 */
public class Serial implements Closeable, Openable {
    private SerialPortInputStream serialPortInputStream = null;
    private SerialPortOutputStream serialPortOutputStream = null;
    
    public native String getNativeLibraryVersion();
    
    static {
        //Load Native Library here.
        System.loadLibrary("j232");    
    }

    /**
     * Returns the underlying OutputStream.
     * @return the OutputStream
     * @throws IOException if an I/O error occurs including if outputstream
     * is NULL or closed.
     */
    public OutputStream getOutputStream() throws IOException {
        if (serialPortOutputStream == null) throw new IOException();
        if (serialPortOutputStream.isClosed()) throw new IOException();
        return serialPortOutputStream;
    }
 
    public InputStream getInputStream() throws IOException{
        if (serialPortInputStream == null) throw new IOException();
        if (serialPortInputStream.isClosed()) throw new IOException();
        return serialPortInputStream;
    }

    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void open() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
