/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javatechnics.j232.manager.impl;

import com.javatechnics.j232.manager.SerialPortManager;
import com.javatechnics.rs232.port.Serial;
import java.util.List;
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
public class SerialPortManagerImpTest {
    
    public SerialPortManagerImpTest() {
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
     * Test of listSerialPorts method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testListSerialPorts_0args() {
        System.out.println("listSerialPorts");
        SerialPortManagerImp instance = new SerialPortManagerImp();
        List<String> expResult = null;
        List<String> result = instance.listSerialPorts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listSerialPorts method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testListSerialPorts_SerialPortManagerPortTypes() {
        System.out.println("listSerialPorts");
        SerialPortManager.PortTypes portType = null;
        SerialPortManagerImp instance = new SerialPortManagerImp();
        List<String> expResult = null;
        List<String> result = instance.listSerialPorts(portType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortPrefixes method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testGetPortPrefixes() {
        System.out.println("getPortPrefixes");
        SerialPortManagerImp instance = new SerialPortManagerImp();
        List<String> expResult = null;
        List<String> result = instance.getPortPrefixes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPortPrefixes method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testSetPortPrefixes() {
        System.out.println("setPortPrefixes");
        List<String> prefixes = null;
        SerialPortManagerImp instance = new SerialPortManagerImp();
        instance.setPortPrefixes(prefixes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendPortPrefixes method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testAppendPortPrefixes() {
        System.out.println("appendPortPrefixes");
        List<String> prefixes = null;
        SerialPortManagerImp instance = new SerialPortManagerImp();
        instance.appendPortPrefixes(prefixes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtainSerialPort method, of class SerialPortManagerImp.
     */
    @Test
    public void testObtainSerialPort() throws Exception {
        System.out.println("obtainSerialPort");
        String device = SerialPortManager.DefaultDebianPorts.TTYUSB0.getPath();
        SerialPortManagerImp instance = new SerialPortManagerImp();
        assertNotNull("Could not obtain " + SerialPortManager.DefaultDebianPorts.TTYUSB0.getPath(),
                instance.obtainSerialPort(device));
        System.out.println("Successfully obtained " + SerialPortManager.DefaultDebianPorts.TTYUSB0.getPath());
    }

    /**
     * Test of obtainSerialPort method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testObtainSerialPort_2args() throws Exception {
        System.out.println("obtainSerialPort");
        String device = SerialPortManager.DefaultDebianPorts.TTYUSB0.getPath();
        SerialPortManagerImp instance = new SerialPortManagerImp();
        assertNotNull("Successfully obtained " + SerialPortManager.DefaultDebianPorts.TTYUSB0.getPath(),
                instance.obtainSerialPort(device));
    }
    /**
     * Test of releaseSerialPort method, of class SerialPortManagerImp.
     */
    @Test @Ignore
    public void testReleaseSerialPort() {
        System.out.println("releaseSerialPort");
        Serial serialPort = null;
        SerialPortManagerImp instance = new SerialPortManagerImp();
        boolean expResult = false;
        boolean result = instance.releaseSerialPort(serialPort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
