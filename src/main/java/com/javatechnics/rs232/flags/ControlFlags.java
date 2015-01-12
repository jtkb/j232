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

/**
 * This enum represents the underlying Unix control flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum ControlFlags implements EnumValue{
    CBAUD       (0010017),
    B0          (0000000),
    B50         (0000001),
    B75         (0000002),
    B110        (0000003),
    B134        (0000004),
    B150        (0000005),
    B200        (0000006),
    B300        (0000007),
    B600        (0000010),
    B1200       (0000011),
    B1800       (0000012),
    B2400       (0000013),
    B4800       (0000014),
    B9600       (0000015),
    B19200      (0000016),
    B38400      (0000017),
    B57600      (0010001),
    B115200     (0010002),
    B230400     (0010003),
    B460800     (0010004),
    B500000     (0010005),
    B576000     (0010006),
    B921600     (0010007),
    B1000000    (0010010),
    B1152000    (0010011),
    B1500000    (0010012),
    B2000000    (0010013),
    B2500000    (0010014),
    B3000000    (0010015),
    B3500000    (0010016),
    B4000000    (0010017),
    EXTA        (0000016),
    EXTB        (0000017),
    CSIZE       (0000060),
    CS5         (0000000),
    CS6         (0000020),
    CS7         (0000040),
    CS8         (0000060),
    CSTOPB      (0000100),
    CREAD       (0000200),
    PARENB	(0000400),
    PARODD	(0001000),
    HUPCL	(0002000),
    CLOCAL	(0004000),
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
    
}
