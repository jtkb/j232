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
import com.javatechnics.rs232.flags.OutputFlags;
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
    private int c_oflag = 0;
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
    
    /**
     * Sets the desired output flags of this TermIOS class. The flags mimic
     * those found within the termios struct in termios.h.
     * @param outputFlags 
     */
    public void setOutputFlags(EnumSet<OutputFlags> outputFlags){
        c_oflag = BitOperation.orValues(outputFlags);
    }
    
    /**
     * Gets the current output flags of the TermIOS class as an EnumSet.
     * @return and EnumSet<OutputFlags> of output flags. For flags which have
     * a mask flag e.g. NLDLY, NL0 & NL1 if a zero value results if when 
     * c_oflag is AND with its delay mask then the zero value for that set of
     * flags is return. If no output flags have currently been set then an empty 
     * enumSet is returned.
     */
    public EnumSet<OutputFlags> getOutputFlagsEnumSet(){
        Set<OutputFlags> flags = new HashSet<OutputFlags>();
        for (OutputFlags ofg : OutputFlags.values()){
            switch (ofg){
                case NLDLY:
                    if ((ofg.getValue() & 
                            c_oflag & 
                            OutputFlags.NLDLY.getValue()) == OutputFlags.NL0.getValue()){
                        flags.add(OutputFlags.NL0);
                        
                    }else{
                        flags.add(OutputFlags.NL1);
                    }
                    break;
                case CRDLY:
                    if ((ofg.getValue() & c_oflag &
                            OutputFlags.CRDLY.getValue()) == OutputFlags.CR0.getValue()){
                        flags.add(OutputFlags.CR0);
                    }else{
                        if ((ofg.getValue() & c_oflag & OutputFlags.CR1.getValue()) == OutputFlags.CR1.getValue()) {
                            flags.add(OutputFlags.CR1);
                        }
                        if ((ofg.getValue() & c_oflag & OutputFlags.CR2.getValue()) == OutputFlags.CR2.getValue()){
                            flags.add(OutputFlags.CR2);
                        }
                        if ((ofg.getValue() & c_oflag & OutputFlags.CR3.getValue()) == OutputFlags.CR3.getValue()){
                            flags.add(OutputFlags.CR3);
                        }
                    }
                    break;
                case TABDLY:
                    if ((ofg.getValue() & c_oflag & OutputFlags.TABDLY.getValue()) == OutputFlags.TABDLY.getValue()){
                        flags.add(OutputFlags.TAB0);
                    }else{
                        int value = ofg.getValue();
                        if ((value & c_oflag & OutputFlags.TAB1.getValue()) == OutputFlags.TAB1.getValue()){
                            flags.add(OutputFlags.TAB1);
                        }
                        if ((value & c_oflag & OutputFlags.TAB2.getValue()) == OutputFlags.TAB2.getValue()){
                            flags.add(OutputFlags.TAB2);
                        }
                        if ((value & c_oflag & OutputFlags.TAB3.getValue()) == OutputFlags.TAB3.getValue()){
                            flags.add(OutputFlags.TAB3);
                        }
                    }
                    break;
                case BSDLY:
                    if ((ofg.getValue() & c_oflag &OutputFlags.BSDLY.getValue()) == OutputFlags.BS0.getValue()){
                        flags.add(OutputFlags.BS0);
                    }else{
                        flags.add(OutputFlags.BS1);
                    }
                    break;
                case FFDLY:
                    if ((ofg.getValue() & c_oflag & OutputFlags.FFDLY.getValue()) == OutputFlags.FF0.getValue()){
                        flags.add(OutputFlags.FF0);
                    }else{
                        flags.add(OutputFlags.FF1);
                    }
                    break;
                case VTDLY:
                    if ((ofg.getValue() & c_oflag & OutputFlags.VTDLY.getValue()) == OutputFlags.VT0.getValue()){
                        flags.add(OutputFlags.VT0);
                    }else{
                        flags.add(OutputFlags.VT1);
                    }
                    break;                    
                default:
                    if ((ofg.getValue() & c_oflag) == ofg.getValue()){
                        flags.add(ofg);
                    }
            }
            
        }
        
        return flags.isEmpty() ? EnumSet.noneOf(OutputFlags.class) : 
                                    EnumSet.copyOf(flags);
    }
    
    /**
     * Returns an integer of the OR-ed output flags set in this TermIOS class.
     * The values OR-ed together are those specified in 
     * {@link com.javatechnics.rs232.flags.OutputFlags} and do not necessarily
     * correspond with the values found in termios.h.
     * @return the OR-ed output flag value.
     */
    public int getOutputFlags(){
        return c_oflag;
    }
}
