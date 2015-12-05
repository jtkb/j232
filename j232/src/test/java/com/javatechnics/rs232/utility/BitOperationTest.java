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
package com.javatechnics.rs232.utility;

import com.javatechnics.rs232.flags.InputFlags;
import java.util.EnumSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class BitOperationTest {
    
    public BitOperationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of orValues method, of class BitOperation.
     */
    @Test
    public void testOrValues() {
        System.out.println("orValues");
        int expResult = InputFlags.ICRNL.getValue() | 
                        InputFlags.IGNCR.getValue() |
                InputFlags.IXANY.getValue();
        EnumSet<InputFlags> inputFlags = EnumSet.of(InputFlags.ICRNL, InputFlags.IGNCR,
                                                    InputFlags.IXANY);
        int result = BitOperation.orValues(inputFlags);
        assertEquals("BitOperation.orValues() do not match expected result.", expResult, result);
        System.out.println("orValues() passed.");
    }
}
