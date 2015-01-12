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
 *
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum OutputFlags implements EnumValue{
OPOST	(0000001),
OLCUC	(0000002),
ONLCR	(0000004),
OCRNL	(0000010),
ONOCR	(0000020),
ONLRET	(0000040),
OFILL	(0000100),
OFDEL	(0000200),
NLDLY	(0000400),
NL0	(0000000),
NL1	(0000400),
CRDLY	(0003000),
CR0	(0000000),
CR1	(0001000),
CR2	(0002000),
CR3	(0003000),
TABDLY	(0014000),
TAB0	(0000000),
TAB1	(0004000),
TAB2	(0010000),
TAB3	(0014000),
BSDLY	(0020000),
BS0	(0000000),
BS1	(0020000),
FFDLY	(0100000),
FF0	(0000000),
FF1	(0100000),
VTDLY	(0040000),
VT0	(0000000),
VT1	(0040000),
XTABS	(0014000);

    public final int value;
    
    OutputFlags(int value){
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
