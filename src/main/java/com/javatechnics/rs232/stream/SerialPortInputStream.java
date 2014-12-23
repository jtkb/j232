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
