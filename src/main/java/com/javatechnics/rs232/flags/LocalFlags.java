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
public enum LocalFlags {
    ISIG	(0000001),
    ICANON	(0000002),
    XCASE	(0000004),
    ECHO	(0000010),
    ECHOE	(0000020),
    ECHOK	(0000040),
    ECHONL	(0000100),
    NOFLSH	(0000200),
    TOSTOP	(0000400),
    ECHOCTL     (0001000),
    ECHOPRT     (0002000),
    ECHOKE	(0004000),
    FLUSHO      (0010000),
    PENDIN	(0040000),
    IEXTEN      (0100000);
    public final int value;
    
    LocalFlags(int value){
        this.value = value;
    }
}