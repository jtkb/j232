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

/**
 * This enum represents the underlying Unix local flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum InputFlags {
    IGNBRK	(0000001),
    BRKINT	(0000002),
    IGNPAR	(0000004),
    PARMRK	(0000010),
    INPCK	(0000020),
    ISTRIP	(0000040),
    INLCR	(0000100),
    IGNCR	(0000200),
    ICRNL	(0000400),
    IUCLC	(0001000),
    IXON	(0002000),
    IXANY	(0004000),
    IXOFF	(0010000),
    IMAXBEL	(0020000),
    IUTF8	(0040000);
    
    public final int value;
    
    InputFlags(int value){
        this.value = value;
    }
    
}
