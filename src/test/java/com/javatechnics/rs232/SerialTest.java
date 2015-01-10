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

import com.javatechnics.rs232.struct.TermIOS;
import com.javatechnics.rs232.struct.ControlCharacters;
import com.javatechnics.rs232.flags.TerminalControlActions;
import com.javatechnics.rs232.flags.OpenFlags;
import com.javatechnics.rs232.flags.InputFlags;
import com.javatechnics.rs232.flags.ModemControlFlags;
import com.javatechnics.rs232.flags.LocalFlags;
import com.javatechnics.rs232.flags.ControlFlags;
import com.javatechnics.rs232.flags.QueueSelector;
import java.io.IOException;
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
        printMessage("Testing the setting and getting of termios structure.");
        assertTrue("Serial port is not open", serial.isOpen());
        
        try {
            TermIOS preTestTermIOS = serial.getTerminalAttributes();
            TermIOS termios = prepareTestTermIOS();
            printMessage("termios.c_lflag = " + termios.c_lflag);
            serial.setTerminalAttributes(TerminalControlActions.TCSANOW.value, termios);
            System.out.println("Terminal Control attributes set.");
            TermIOS setTermios = serial.getTerminalAttributes();
            
            assertEquals("Input flags do not match", 
                                termios.c_iflag, setTermios.c_iflag);
            assertEquals("Output flags do not match",
                               termios.c_oflag, setTermios.c_oflag);
            //assertEquals("Control flags do not match",
            //                    termios.c_cflag, setTermios.c_cflag);
            assertEquals("Local mode flags do not match",
                                termios.c_lflag, setTermios.c_lflag | 0xFFFF2000);
            assertArrayEquals("Control characters do not match.", 
                                termios.c_cc, setTermios.c_cc);
        } catch (IOException ex) {
            fail(ex.toString());
        }
    }
    
    @Test
    public void testModemControlBits(){
        printMessage("Testing the setting and getting of the Modem control bits.");
        int preTestModemControlBits = 0;
        int modemControlBitsToSet = 0;
        try {
            preTestModemControlBits = serial.getModemControlBits();
            modemControlBitsToSet = preTestModemControlBits & ~ModemControlFlags.TIOCM_RTS.value;
            modemControlBitsToSet &= ~ModemControlFlags.TIOCM_DTR.value;
            printMessage("Pre-test modem control bits :" + preTestModemControlBits);
            printMessage("Modem control bits to set: " +  modemControlBitsToSet);
            serial.setModemControlbits(modemControlBitsToSet);
            assertEquals("Unable to set modem control bits.", modemControlBitsToSet, serial.getModemControlBits() & 0x0E1FF);
            printMessage("Post-test modem control bits: " + serial.getModemControlBits());
        } catch (IOException ex) {
            fail("Testing Modem control bits failed: " + ex);
        }
    }
    
    @Test
    public void testFlushcontrol(){
        printMessage("Testing the input output queue flush control.");
        assertTrue(serial.isOpen());
        
        try {
            assertEquals("Failed to flush input queue", 0, serial.flushQueue(QueueSelector.TCIFLUSH));
            assertEquals("Failed to flush output queue", 0, serial.flushQueue(QueueSelector.TCOFLUSH));
            assertEquals("Failed to flush input and output queues", 0, serial.flushQueue(QueueSelector.TCIOFLUSH));
        } catch (IOException ex) {
            fail("Failed to flush input and/or output queue." + ex);
        }
    }
    private static void printMessage(String message){
        System.out.println(message);
    }
    
    private TermIOS prepareTestTermIOS(){
        TermIOS termios = new TermIOS();
        termios.c_cflag = ControlFlags.B115200.value | ControlFlags.CS8.value |
                          ControlFlags.CLOCAL.value | ControlFlags.CREAD.value;
        //termios.c_cflag = ControlFlags.B19200.value | ControlFlags.CS8.value | ControlFlags.CREAD.value;
        termios.c_iflag = InputFlags.ICRNL.value;
        termios.c_oflag = 0;
        termios.c_lflag = ~(LocalFlags.PENDIN.value | LocalFlags.IEXTEN.value | LocalFlags.FLUSHO.value);
        
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
        return termios;
    }
    
}
