package com.javatechnics.j232.manager;

import com.javatechnics.j232.manager.exception.NoSuchPort;
import com.javatechnics.j232.manager.exception.PortNotAvailable;
import com.javatechnics.rs232.Serial;
import java.util.List;

public interface SerialPortManager {
    
    /**
     * Obtain a list of current serial ports available. The returned list should
     * be immutable.
     * @return List of serial ports.
     */
    public List<String> listSerialPorts();
    
    /**
     * Obtain a list of current serial ports of the specified type. The returned
     * list should be immutable.
     * @param portType the serial port type to list.
     * @return List of the specified serial port type.
     */
    public List<String> listSerialPorts(PortTypes portType);
    
    /**
     * Enum of types of serial port hardware. 
     * FIXED: Non-removable type e.g. on motherboard
     * USB: Removable type via USB ports
     * ALL: All removable and non-removable serial ports.
     */
    public enum PortTypes {FIXED, USB, ALL};
    
    /**
     * Obtain a list of serial port prefixes e.g. ttyS, ttyUSB
     * @return a list of serial port prefixes.
     */
    public List<String> getPortPrefixes();
    
    /**
     * Set the current list of port prefixes which the manager uses to enumerate
     * serial ports. Note that the current list of prefixes will be over-written
     * by the specified list.
     * @param prefixes a list of the prefixes to use e.g. ttyS 
     */
    public void setPortPrefixes(List<String> prefixes);
    
    /**
     * Append the current list of prefixes with the specified list. The current
     * list is preserved.
     * @param prefixes the list of prefixes to append.
     */
    public void appendPortPrefixes(List<String> prefixes);
    
    /**
     * Obtain a specified port.
     * @param device the specified serial port.
     * @return
     * @throws PortNotAvailable if the requested port is not available, e.g
     * in use.
     * @throws NoSuchPort if the requested port is not available
     */
    public Serial obtainSerialPort(String device) throws PortNotAvailable,
                                                        NoSuchPort;
    
    /**
     * Release a specified Serial object. Once released the serial port becomes
     * available for use.
     * @param serialPort the Serial object to release.
     * @return true if successfully released.
     */
    public boolean releaseSerialPort(Serial serialPort);
    
}