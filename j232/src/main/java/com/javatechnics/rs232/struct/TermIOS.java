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

import com.javatechnics.rs232.flags.ControlFlags;
import com.javatechnics.rs232.flags.InputFlags;
import com.javatechnics.rs232.flags.LocalFlags;
import com.javatechnics.rs232.flags.OutputFlags;
import com.javatechnics.rs232.utility.BitOperation;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private int c_cflag = 0;
    /**
     * Local flags.
     */
    private int c_lflag = 0;
    /**
     * The size of the control character array.
     */
    public final int c_ccArraySize = ControlCharacters.values().length;
    /**
     * Control character flags.
     */
    private byte[] c_cc = new byte[c_ccArraySize];
    
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
     * A helper function that produces a Set<OutputFlags> based upon
     * a single int value and a specified Set of Outputflags.
     * @param intFlag the integer flag value with will be transformed to a 
     * Set<OutputFlag>.
     * @param flagSubSet the EnumSet of OutputFlags from which one will be selected.
     * @return the Set<OutputFlag> which will contain one OutputFlag from those
     * specified in to the method.
     */
    private Set<OutputFlags> getOutputFlagFromSubSet(int intFlag, EnumSet<OutputFlags> flagSubSet){
        Set<OutputFlags> flags = new HashSet<OutputFlags>();
        System.out.println("Integer flag : " + intFlag + flagSubSet);
        for (OutputFlags ofg : flagSubSet){
            if (intFlag == ofg.getValue()){
                flags.add(ofg);
                break;
            }
        }
        return flags;
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
        System.out.println("Non-Maskable flags: " + OutputFlags.getNonMaskableFlags());
        System.out.println("Output Flags: " + c_oflag);
        for (OutputFlags ofg : OutputFlags.getNonMaskableFlags()){
            int flag = c_oflag & ofg.getValue();
            switch (ofg){
                case NLDLY:
                    flags.addAll(getOutputFlagFromSubSet(flag, OutputFlags.getNewLineDelayFlags()));
                    break;
                case CRDLY:
                    flags.addAll(getOutputFlagFromSubSet(flag, OutputFlags.getCarriageReturnDelayFlags()));
                    break;
                case TABDLY:
                    flags.addAll(getOutputFlagFromSubSet(flag, OutputFlags.getHorizontalTabDelayFlags()));
                    break;
                case BSDLY:
                    flags.addAll(getOutputFlagFromSubSet(flag, OutputFlags.getBackspaceDelayFlags()));
                    break;
                case FFDLY:
                    flags.addAll(getOutputFlagFromSubSet(flag, OutputFlags.getFormFeedDelayFlags()));
                    break;
                case VTDLY:
                    flags.addAll(getOutputFlagFromSubSet(flag, OutputFlags.getVerticalTabDelayFlags()));
                    break;
                case OCRNL:
                case OPOST:
                case OLCUC:
                case ONLCR:
                case ONOCR:
                case ONLRET:
                case OFILL:
                case OFDEL:
                case XTABS:
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
    
    /**
     * Sets the ControlFlags to be used by this instance of TermIOS.
     * @param flags an EnumSet<ControlFlags>
     */
    public void setControlFlags(EnumSet<ControlFlags> flags){
        c_cflag = BitOperation.orValues(flags);
    }
    
    /**
     * Returns the current ControlFlags as an EnumSet<ControlFlags>
     * @return an EnumSet<ControlFlags>.
     */
    public EnumSet<ControlFlags> getControlFlagsEnumSet(){
        Set<ControlFlags> flags = new HashSet<ControlFlags>();
        for (ControlFlags cfg : ControlFlags.values()){
            int flag = c_cflag & cfg.getValue();
            switch (cfg){
                case CBAUD:
                    for (ControlFlags baud : ControlFlags.getBaudRates()){
                        if (flag  == baud.getValue()){
                            flags.add(baud);
                            break;
                        }
                    }
                    break;
                case CSIZE:
                    for (ControlFlags cSizes : ControlFlags.getCharacterSizes()){
                        if (flag == cSizes.getValue()){
                            flags.add(cSizes);
                            break;
                        }
                    }
                    break;
                case CSTOPB:
                case CREAD:
                case PARENB:
                case PARODD:
                case HUPCL:
                case CLOCAL:
                case CRTSCTS:
                    if (flag == cfg.getValue()){
                        flags.add(cfg);
                    }
                    break;                        
            }
        }
        return flags.isEmpty() ? EnumSet.noneOf(ControlFlags.class) : 
                                    EnumSet.copyOf(flags);
    }
    
    /**
     * Gets the int OR-ed value of the Local Flags set in this TermIOS instance.
     * @return an int value of the flags.
     */
    public int getLocalFlags(){
        return c_lflag;
    }
    
    /**
     * Sets the Local flags into this instance of TermIOS. The flags mimic
     * those found within the termios struct in termios.h.
     * @param flags the LocalFlags to set.
     */
    public void setLocalFlagsEnumSet(EnumSet<LocalFlags> flags){
        c_lflag = BitOperation.orValues(flags);
    }
    
    /**
     * Gets the current Local flags set in this TermIOS instance as an
     * EnumSet<LocalFlags>.
     * @return an EnumSet<Localflags>
     */
    public EnumSet<LocalFlags> getLocalFlagsEnumSet(){
        Set<LocalFlags> flags = new HashSet<LocalFlags>();
        for (LocalFlags lfg : LocalFlags.values()){
            if((c_lflag & lfg.getValue()) == lfg.getValue()){
                flags.add(lfg);
            }
        }
        return flags.isEmpty() ? EnumSet.noneOf(LocalFlags.class) : 
                                    EnumSet.copyOf(flags);
    }
    
    /**
     * Returns the control characters set in this instance.
     * @return and array of bytes containing the control characters.
     */
    public byte [] getControlCharacters(){
        return c_cc;
    }
    
    /**
     * Gets the Control characters for this instance in the form of a Map.
     * @return a of Map<ControlCharacters, Byte> containing the current control
     * characters.
     */
    public Map<ControlCharacters,Byte> getControlCharactersMap(){
        HashMap<ControlCharacters, Byte> ccMap = new HashMap<ControlCharacters, Byte>();
        for (ControlCharacters cc : ControlCharacters.values()){
            ccMap.put(cc, c_cc[cc.getValue()]);
        }
        return ccMap;
    }
    
    /**
     * Sets the Control characters of this instance.
     * @param controlCharacters a Map<Controlcharacters, Byte> to set into this
     * instance.
     */
    public void setControlCharactersMap(Map<ControlCharacters, Byte> controlCharacters){
        byte[] cc = new byte[ControlCharacters.values().length];
        for (Map.Entry<ControlCharacters, Byte> entry : controlCharacters.entrySet()){
            cc[entry.getKey().getValue()] = entry.getValue();
        }
        c_cc = cc;
    }
    
    
}
