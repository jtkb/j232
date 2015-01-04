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
package com.javatechnics.rs232.struct;

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
