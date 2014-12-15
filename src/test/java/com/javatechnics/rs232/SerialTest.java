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
    
    @Test
    public void testSetTerminalControlStructure(){
        System.out.println("Testing the setting of termios stucture");
        TermIOS termios = new TermIOS();
        termios.c_cflag = ControlFlags.B2400.value | ControlFlags.CS8.value |
                          ControlFlags.CLOCAL.value | ControlFlags.CREAD.value;
        termios.c_iflag = InputFlags.ICRNL.value;
        termios.c_oflag = 0;
        termios.c_lflag = ~LocalFlags.ICANON.value;
        
        termios.c_cc[ControlCharacters.VINTR.value] = 0;
        termios.c_cc[ControlCharacters.VQUIT.value] = 0;
        termios.c_cc[ControlCharacters.VERASE.value] = 0;
        termios.c_cc[ControlCharacters.VKILL.value] = 0;
        termios.c_cc[ControlCharacters.VEOF.value] = 0;
        termios.c_cc[ControlCharacters.VTIME.value] = 1;
        termios.c_cc[ControlCharacters.VMIN.value] = 14;
        termios.c_cc[ControlCharacters.VSWTC.value] = 0;
        termios.c_cc[ControlCharacters.VSTART.value] = 17;
        termios.c_cc[ControlCharacters.VSTOP.value] = 19;
        termios.c_cc[ControlCharacters.VSUSP.value] = 0;
        termios.c_cc[ControlCharacters.VEOL.value] = 0;
        termios.c_cc[ControlCharacters.VREPRINT.value] = 0;
        termios.c_cc[ControlCharacters.VDISCARD.value] = 0;
        termios.c_cc[ControlCharacters.VWERASE.value] = 0;
        termios.c_cc[ControlCharacters.VLNEXT.value] = 0;
        termios.c_cc[ControlCharacters.VEOL2.value] = 0;
        
        try {
            serial.setTerminalAttributes(TerminalControlActions.TCSANOW.value, termios);
            System.out.println("Terminal Control attributes set.");
        } catch (IOException ex) {
            fail(ex.toString());
        }
    }
    
}
