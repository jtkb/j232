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

import com.javatechnics.rs232.flags.InputFlags;
import com.javatechnics.rs232.utility.BitOperation;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * This class mimics the termios native structure.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public class TermIOS {
        
    /**
     * Input mode flags.
     */
    private int c_iflag = 0;
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
    
    /**
     * Sets the desired input flags of this TermIOS class. The flags mimic
     * those found within termios struct found within termios.h.
     * @param inputFlags The input flags to set.
     */
    public void setInputFlags(EnumSet<InputFlags> inputFlags){
        c_iflag = BitOperation.orValues(inputFlags);
    }
    
    /**
     * Gets the current input flags of the TermIOS class as an EnumSet.
     * @return and EnumSet<InputFlags> of the requested input flags. If
     * no input flags have currently been set then an empty enumSet is returned.
     */
    public EnumSet<InputFlags> getInputFlagsEnumSet(){
        Set<InputFlags> flags = new HashSet<InputFlags>();
        for (InputFlags ifg : InputFlags.values()){
            if ((ifg.getValue() & c_iflag) == ifg.getValue()){
                flags.add(ifg);
            }
        }
        return flags.isEmpty() ? EnumSet.noneOf(InputFlags.class) : EnumSet.copyOf(flags);
    }
    
    /**
     * Returns an integer OR-ed value of all the requested input values. the
     * values OR-ed together are those specified in
     * {@link com.javatechnics.rs232.flags.InputFlags} and do not necessarily
     * correspond with the values found in termios.h.
     * @return 
     */
    public int getInputFlags(){
        return c_iflag;
    }
}
