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
package com.javatechnics.rs232.stream;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class SerialPortInputStream extends InputStream{

    private int serialPortFileDescriptor = -1;
    
    public SerialPortInputStream(int fileDescriptor){
        this.serialPortFileDescriptor = fileDescriptor;
    }
    /**
     * The native function that reads data from the serial port.
     * @param fileDescriptor the file descriptor of the serial port.
     * @param buffer buffer where the data read will be written to.
     * @param offset the starting point in buffer where data will be written.
     * @param length the size of buffer. NOTE: length is buffer size regardless 
     * of any non-zero offset value.
     * @return 0 upon success, -1 if an error occurred and an exception was
     * not thrown.
     * @throws IOException if an error occurs in the native function.
     */
    private native int readNative(int fileDescriptor, 
                                    byte[] buffer,
                                    int offset,
                                    int length) throws IOException;
    
    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset(); 
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit); 
    }

    /**
     * Call this function to close the SerialPortInputStream. This function has
     * no effect. To close the SerialPortInputStream call the close method on 
     * the Serial object instead.
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    @Override
    public long skip(long n) throws IOException {
        throw new IOException("Skip not supported.");
    }

    /**
     * Reads the next bytes from the serial port and puts them into a byte
     * buffer beginning at a specified offset.
     * @param b the byte array where the data will be written.
     * @param off and index >= 0 which is an index into the buffer array where
     * data will be written.
     * @param len the length of the buffer array.
     * @return the number of bytes read or -1 if an error occurred and no
     * exception was thrown.
     * @throws IOException if an error occurs.
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (off < 0) throw new IOException("Offset less than zero.");
        if (len - off > b.length)
            throw new IOException("Buffer too short: Buffer length = " + b.length +
                                    " Buffer offset: " + off +
                                    " Read size: " + len);
        return readNative(serialPortFileDescriptor, b, off, len);
    }

    /**
     * Reads the data from the serial port and places it into the byte buffer
     * passed to the method
     * @param b the buffer to fill with data from the serial port.
     * @return the number of bytes read of -1 if an error occurred with no
     * exception thrown.
     * @throws IOException if an error occurs.
     */
    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    /**
     * Reads the next byte in the input stream and returns as an array.
     * @return
     * @throws IOException 
     */
    @Override
    public int read() throws IOException {
        byte[] b = {0};
        read(b, 0, 1);
        return (int)b[0];
    }
    
}
