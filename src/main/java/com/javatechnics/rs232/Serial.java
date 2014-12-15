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
    private SerialPortInputStream serialPortInputStream = null;
    /**
     * The OutputStream object for the serial port.
     */
    private SerialPortOutputStream serialPortOutputStream = null;
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
     * Static code here in particular this is where the 
     */
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

    /**
     * Implementation of the Closeable interface. Call this method when the
     * serial port is to be closed.
     * @throws IOException if an IOException occurs during call.
     */
    public void close() throws IOException {
        int return_value = closeSerialPort(fileDescriptor);
        if (return_value == -1) throw new IOException("");
        fileDescriptor = -1;    // Restore FD to -1
    }

    /**
     * Opens the underlying hardware serial port.
     * @param path the file-system path to the device e.g. /dev/ttyS0.
     * @param flags flags used to open the device with. See 
     * {@link com.javatechnics.rs232.OpenFlags}.
     * @return true if the port was successfully opened false if an error
     * occured AND the native code could not throw an IOException.
     * @throws IOException
     */
    public boolean open(String path, int flags) throws IOException {
        if ((fileDescriptor = openSerialPort(path, flags)) > -1){
            portOpen = true;
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
     * Call this method to set control attributes on the underlying serial
     * port.
     * @param terminalControlAction integer flag. 
     * See {@link com.javatechnics.rs232.TerminalControlActions}
     * @param termios a {@link com.javatechnics.rs232.TermIOS} class that
     * describes the desired control modes.
     * @return 0 upon success, -1 if an error occurred in the native code
     * @throws IOException 
     */
    public int setTerminalAttributes(int terminalControlAction, TermIOS termios)
            throws IOException {
        if (termios == null) throw new IOException("");
        return setNativeTerminalAttributes(fileDescriptor, 
                terminalControlAction, 
                termios);
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
