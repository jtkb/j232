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
 * @author kerry
 */
public class SerialPortInputStream extends InputStream{

    private int serialPortFileDescriptor = -1;
    
    public SerialPortInputStream(int fileDescriptor){
        this.serialPortFileDescriptor = fileDescriptor;
    }
    
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

    @Override
    public void close() throws IOException {
        super.close(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    @Override
    public long skip(long n) throws IOException {
        throw new IOException("Skip not supported.");
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (len - off > b.length)
            throw new IOException("Buffer too short: Buffer length = " + b.length +
                                    " Buffer offset: " + off +
                                    " Read size: " + len);
        return readNative(serialPortFileDescriptor, b, off, len);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
