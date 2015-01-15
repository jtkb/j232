/*
 * Copyright (C) 2015 Kerry Billingham <contact@AvionicEngineers.com>.
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
import com.javatechnics.rs232.flags.OutputFlags;
import java.util.EnumSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class TermIOSTest {
    
    private static TermIOS termios = null;
    
    public TermIOSTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        termios = new TermIOS();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of getInputFlagsEnumSet method, of class TermIOS.
     */
    
    @Test
    public void testGetInputFlagsEnumSet() {
        System.out.println("getInputFlagsEnumSet");
        TermIOS instance = new TermIOS();
        EnumSet<InputFlags> expResult = EnumSet.of(InputFlags.IGNPAR, InputFlags.ISTRIP,
                                                    InputFlags.IUTF8);
        instance.setInputFlags(expResult);
        EnumSet<InputFlags> result = instance.getInputFlagsEnumSet();
        assertEquals(expResult, result);
        assertEquals("The set InputFlags are not the same as retrieved InputFlags.", expResult, result);
    }

    /**
     * Test of getInputFlags method, of class TermIOS.
     */
    @Test
    public void testGetInputFlags() {
        System.out.print("Testing getInputFlags()...");
        TermIOS instance = new TermIOS();
        EnumSet<InputFlags> enumFlags = EnumSet.of(InputFlags.IGNPAR, InputFlags.ISTRIP,
                                                    InputFlags.IUTF8);
        int expResult = InputFlags.IGNPAR.getValue() | InputFlags.ISTRIP.getValue() |
                            InputFlags.IUTF8.getValue();
        instance.setInputFlags(enumFlags);
        int result = instance.getInputFlags();
        assertEquals("Input flags returned do NOT match those set.",expResult, result);
        System.out.print("passed.\n");
        
    }
    
    /**
     * Testing that TermIOS handles empty Input flags correctly.
     */
    @Test
    public void testEmptyGetInputFlags(){
        System.out.print("Testing the handling of an empty input flags...");
        TermIOS termIOS = new TermIOS();
        EnumSet<InputFlags> ifs = termIOS.getInputFlagsEnumSet();
        assertTrue("The TermIOS did not return an empty EnumSet <InputFlags>.", 
                ifs.isEmpty());
        System.out.print("passed.\n");
    }
    
    @Test
    public void testGetOutputFlags(){
        System.out.print("Testing getOutputFlags..");
        TermIOS instance = new TermIOS();
        EnumSet<OutputFlags> enumFlags = EnumSet.of(OutputFlags.OPOST,
                OutputFlags.NL0, OutputFlags.CR0, OutputFlags.TAB0,
                OutputFlags.BS0, OutputFlags.FF0, OutputFlags.VT0,
                                                    OutputFlags.OLCUC,
                                                    OutputFlags.FF0);
        instance.setOutputFlags(enumFlags);
        EnumSet<OutputFlags> result = instance.getOutputFlagsEnumSet();
        assertEquals("Output flags returned do NOT match those set.", enumFlags, result);
        System.out.print("passed.\n");
    }
    
    @Test
    public void testAppendingOutputFlags(){
        System.out.print("Testing the addition of output flags...");
        TermIOS termios = new TermIOS();
        assertEquals("Did not append new Set of Output flags.", true, 
                termios.addOutputFlagsEnumSet(EnumSet.of(OutputFlags.BS1, OutputFlags.OCRNL)));
        assertEquals("The expected Outputflags Enumset do not match those set.",
                EnumSet.of(OutputFlags.NL0, OutputFlags.CR0, OutputFlags.TAB0,
                OutputFlags.FF0, OutputFlags.VT0, 
                OutputFlags.OCRNL, OutputFlags.BS1), termios.getOutputFlagsEnumSet());
        System.out.print("TermIOS OutputFlags: " + termios.getOutputFlagsEnumSet() + " passed.\n");
    }
    
    @Test //@Ignore
    public void testSetControlFlags(){
        System.out.print("Testing the setting of the control flags...");
        TermIOS termios = new TermIOS();
        termios.setControlFlags(EnumSet.of(ControlFlags.B1200, ControlFlags.B2500000, ControlFlags.CS6, ControlFlags.CS7, ControlFlags.CS8));
        assertEquals("Control flags returned were not those requested.", 
                EnumSet.of(ControlFlags.B3000000, ControlFlags.CS8), termios.getControlFlagsEnumSet());
        System.out.print("passed.\n");
    }
    
    @Test
    public void testBaudRateEnumSet(){
        System.out.print("Testing the retrieval of the baud rates...");
        assertEquals("The set of baud rates is not correct.", 
                EnumSet.complementOf(EnumSet.of(
                    ControlFlags.CBAUD,
                    ControlFlags.EXTA,
                    ControlFlags.EXTB,
                    ControlFlags.CSIZE,
                    ControlFlags.CS5,
                    ControlFlags.CS6,
                    ControlFlags.CS7,
                    ControlFlags.CS8,
                    ControlFlags.CSTOPB,
                    ControlFlags.CREAD,
                    ControlFlags.PARENB,
                    ControlFlags.PARODD,
                    ControlFlags.HUPCL,
                    ControlFlags.CLOCAL,
                    ControlFlags.CRTSCTS)),
                ControlFlags.getBaudRates());
        System.out.print("passed.\n");
    }
}
