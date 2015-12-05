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
import java.io.OutputStream;

/**
 *
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class SerialPortOutputStream extends OutputStream{
    
    private int serialPortFileDescriptor = -1;
    
    public SerialPortOutputStream(int fileDescriptor){
        serialPortFileDescriptor = fileDescriptor;
    }
    
    /**
     * The native function that writes data to the serial port.
     * @param fileDescriptor file descriptor of the serial port.
     * @param buffer is from where the data will be read.
     * @param offset is the index in buffer where reading will begin from.
     * @param length of the buffer.
     * @throws IOException if an error occurs
     */
    private native void nativeWrite(int fileDescriptor, byte[] buffer,
                                    int offset, int length) throws IOException;
    /**
     * Closes the Outputstream. This method does nothing. To close the
     * OutputStream close the associated Serial object.
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        super.close();
    }

    /**
     * Forces any buffered output data to be written to the serial port. this
     * method does nothing as no buffering takes place.
     * @throws IOException 
     */
    @Override
    public void flush() throws IOException {
        super.flush(); 
    }

    /**
     * Writes a specified buffer to the serial port beginning at a specified 
     * offset in the buffer.
     * @param b a byte array containing the byte data to be written.
     * @param off the index into the buffer from where to begin writing from. 
     * Must be greater or equal to zero otherwise exception will be thrown.
     * @param len the length of the byte array containing the data.
     * @throws IOException if an error occurs.
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (off < 0) throw new IOException("Offset less than zero.");
        nativeWrite(serialPortFileDescriptor, b, off, len);
    }

    /**
     * Writes a specified byte array to the serial port.
     * @param b a byte array containing the data to be written.
     * @throws IOException if an error occurs.
     */
    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    /**
     * Writes a single integer, as a byte, to the serial port. Only the lower
     * 8 bits of the integer are written.
     * @param b the integer value to write to the serial port.
     * @throws IOException if an error occurs.
     */
    @Override
    public void write(int b) throws IOException {
        byte[] b2 = {(byte)(0xFF & b)};
        write(b2, 0, 1);
    }
        
}
