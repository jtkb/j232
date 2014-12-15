/*
 * Copyright 2014 Kerry Billingham <java@avionicengineers.com>.
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

/**
 * This class mimics the termios native structure.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public class TermIOS {
        
    /**
     * Input mode flags.
     */
    public int c_iflag = 0;
    /**
     * Output mode flags.
     */
    public int c_oflag = 0;
    /**
     * Control flags.
     */
    public int c_cflag = 0;
    /**
     * Local/line line flags.
     */
    public int c_lflag = 0;
    
    public final int c_ccArraySize = 32;
    /**
     * Control character flags.
     */
    public byte[] c_cc = new byte[c_ccArraySize]; //32 matches termios array size
    
    
}
