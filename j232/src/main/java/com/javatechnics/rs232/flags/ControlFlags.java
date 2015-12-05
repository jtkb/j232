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
package com.javatechnics.rs232.flags;

import com.javatechnics.rs232.EnumValue;
import java.util.EnumSet;

/**
 * This enum represents the underlying Unix control flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files. The following Javadoc comments
 * are taken from the Linux man-pages 3.54. A description of the project, 
 * and information about reporting bugs, can be found at 
 * http://www.kernel.org/doc/man-pages/.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum ControlFlags implements EnumValue{
    /**
     * **MASK** (not in POSIX) Baud speed mask (4+1 bits). 
     * [requires _BSD_SOURCE or _SVID_SOURCE]
     */
    CBAUD       (0010017),
    /**
     * Instructs the modem to 'hang-up'.
     */
    B0          (0000000),
    /**
     * Baud-rate 50.
     */
    B50         (0000001),
    /**
     * Baud-rate 75.
     */
    B75         (0000002),
    /**
     * Baud-rate 110.
     */
    B110        (0000003),
    /**
     * Baud-rate 134.
     */
    B134        (0000004),
    /**
     * Baud-rate 150.
     */
    B150        (0000005),
    /**
     * Baud-rate 200.
     */
    B200        (0000006),
    /**
     * Baud-rate 300.
     */
    B300        (0000007),
    /**
     * Baud-rate 600.
     */
    B600        (0000010),
    /**
     * Baud-rate 1200.
     */
    B1200       (0000011),
    /**
     * Baud-rate 1800.
     */
    B1800       (0000012),
    /**
     * Baud-rate 2400.
     */
    B2400       (0000013),
    /**
     * Baud-rate 4800.
     */
    B4800       (0000014),
    /**
     * Baud-rate 9600.
     */
    B9600       (0000015),
    /**
     * Baud-rate 19200.
     */
    B19200      (0000016),
    /**
     * Baud-rate 38400.
     */
    B38400      (0000017),
    /**
     * Baud-rate 57600.
     */
    B57600      (0010001),
    /**
     * Baud-rate 115200.
     */
    B115200     (0010002),
    /**
     * Baud-rate 230400.
     */
    B230400     (0010003),
    /**
     * Baud-rate 460800.
     */
    B460800     (0010004),
    /**
     * Baud-rate 500000.
     */
    B500000     (0010005),
    /**
     * Baud-rate 576000.
     */
    B576000     (0010006),
    /**
     * Baud-rate 921600.
     */
    B921600     (0010007),
    /**
     * Baud-rate 1000000.
     */
    B1000000    (0010010),
    /**
     * Baud-rate 1152000.
     */
    B1152000    (0010011),
    /**
     * Baud-rate 1500000.
     */
    B1500000    (0010012),
    /**
     * Baud-rate 2000000.
     */
    B2000000    (0010013),
    /**
     * Baud-rate 2500000.
     */
    B2500000    (0010014),
    /**
     * Baud-rate 3000000.
     */
    B3000000    (0010015),
    /**
     * Baud-rate 3500000.
     */
    B3500000    (0010016),
    /**
     * Baud-rate 4000000.
     */
    B4000000    (0010017),
    
    EXTA        (0000016),
    EXTB        (0000017),
    /**
     * **MASK** Baud-rate.
     */
    CSIZE       (0000060),
    /**
     * Character size 5.
     */
    CS5         (0000000),
    /**
     * Character size 6.
     */
    CS6         (0000020),
    /**
     * Character size 7.
     */
    CS7         (0000040),
    /**
     * character size 8.
     */
    CS8         (0000060),
    /**
     * Set two stop bits, rather than one.
     */
    CSTOPB      (0000100),
    /**
     * Enable receiver.
     */
    CREAD       (0000200),
    /**
     * Enable parity generation on output and parity checking for input.
     */
    PARENB	(0000400),
    /**
     * If set, then parity for input and output is odd; 
     * otherwise even parity is used.
     */
    PARODD	(0001000),
    /**
     * Lower modem control lines after last process closes the device (hang up).
     */
    HUPCL	(0002000),
    /**
     * Ignore modem control lines.
     */
    CLOCAL	(0004000),
    /**
     * (not in POSIX) Enable RTS/CTS (hardware) flow control. 
     * [requires _BSD_SOURCE or _SVID_SOURCE].
     */
    CRTSCTS     (020000000000);
    
    public final int value;
    
    ControlFlags (int value){
        this.value = value;
    }

    /**
     * Returns the numeric value of this member of the enumeration.
     * @return the numeric value of this member.
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Convenience method that returns all the non-zero baud rates.
     * @return EnumSet of baud rates.
     */
    public static EnumSet<ControlFlags> getBaudRates(){
        EnumSet<ControlFlags> baudRates = 
                EnumSet.complementOf(EnumSet.of(CBAUD,
                                                EXTA,
                                                EXTB,
                                                CSIZE,
                                                CS5,
                                                CS6,
                                                CS7,
                                                CS8,
                                                CSTOPB,
                                                CREAD,
                                                PARENB,
                                                PARODD,
                                                HUPCL,
                                                CLOCAL,
                                                CRTSCTS));
                return baudRates;
    }
    
    /**
     * Convenience method that returns all the non-zero character sizes.
     * @return EnumSet of character sizes.
     */
    public static EnumSet<ControlFlags> getCharacterSizes(){
        return EnumSet.of(CS5,CS6,CS7,CS8);
    }
    
    /**
     * Gets all the flags which are non-maskable values. The list will
     * include those member which are masks themselves, e.g. CSIZE.
     * @return EnumSet of the Non-maskable values.
     */
    public static EnumSet<ControlFlags> getNonMaskableFlags(){
        return EnumSet.of(CBAUD, EXTA, EXTB,CSIZE, CSTOPB, CREAD, PARENB, PARODD,
                            HUPCL, CLOCAL, CRTSCTS);
    }
    
}
