/*
 * Copyright 2014 kerry.
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

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author kerry
 */
public class Serial extends InputStream{
    static {
        //Load Native Library here.
        System.loadLibrary("libj232");
    }
    @Override
    public boolean markSupported() {
        return super.markSupported(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        super.close(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int available(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public int read() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
