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
package com.javatechnics.rs232;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kerry
 */
public class SerialTest {
    
    private static Serial serial = null;
    private static final String NATIVE_LIB_VERSION = "1.0.0";
    private static final String SERIAL_PORT_PREFIX = "ttyS";
    private static final String USB_SERIAL_PORT_PREFIX = "ttyUSB";
    
    
    public SerialTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Test 1.1 - Test for java.lang.UnsatifiedLinkError when Serial object
        // is created
        try {
           serial = new Serial();
            assert(true);
            System.out.println("libj232.so loaded");
        } catch (UnsatisfiedLinkError exception){
            fail("Runtime Exception: " + exception);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            serial.close();
            
            System.out.println("Serial port closed.");
        } catch (IOException ex) {
            fail("Failed to close serial port: " + ex);
        }
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }

    
    @Test
    public void testNativeVersion(){
        // Test for the correct native version
        System.out.print("Testing for Native library version " +
                            NATIVE_LIB_VERSION + " ...");
        assertEquals("Native library version is not " + NATIVE_LIB_VERSION,
                            NATIVE_LIB_VERSION,
                            serial.getNativeLibraryVersion());
        System.out.print("passed.\n");
        
    }
    
    @Test
    public void testSerialOpen(){
        System.out.println("Testing open() method");
        try {            
            assertTrue("Opening file", serial.open("/dev/" + SERIAL_PORT_PREFIX
                                            + "0",
                                            OpenFlags.O_RDWR.value));
        } catch (IOException ex) {
            fail("IOException: " + ex);
        }
    }
    
}
