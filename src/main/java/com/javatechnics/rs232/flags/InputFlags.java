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
 * This enum represents the underlying Unix local flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files. The Javadoc comments for each
 * member were taken from the termios man pages release 3.54.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum InputFlags implements EnumValue{
    /**
     * Ignore BREAK condition on input.
     */
    IGNBRK	(0000001),
    /**
     * If IGNBRK is set, a BREAK is ignore. If it is not set but BRKINT is set,
     * then a BREAK causes the input and output queues to be flushed, and if the
     * terminal is the controlling terminal of a foreground process group, it 
     * will cause a SIGINT to be sent to this foreground process group.  When 
     * neither IGNBRK nor BRKINT are set, a BREAK reads as a null byte ('\0'), 
     * except when  PARMRK is set, in which case it reads as the 
     * sequence \377 \0 \0.
     */
    BRKINT	(0000002),
    /**
     * Ignore framing errors and parity errors.
     */
    IGNPAR	(0000004),
    /**
     * If IGNPAR is not set, prefix a character with a parity error or framing 
     * error with \377 \0.  If neither IGNPAR nor PARMRK is set, read a 
     * character with a parity error or framing error as \0.
     */
    PARMRK	(0000010),
    /**
     * Enable input parity checking.
     */
    INPCK	(0000020),
    /**
     * Strip off eighth bit.
     */
    ISTRIP	(0000040),
    /**
     * Translate NL to CR on input.
     */
    INLCR	(0000100),
    /**
     * Translate carriage return to newline on input (unless IGNCR is set).
     */
    IGNCR	(0000200),
    /**
     * Translate carriage return to newline on input (unless IGNCR is set).
     */
    ICRNL	(0000400),
    /**
     *(not in POSIX) Map uppercase character to lowercase on input.
     */
    IUCLC	(0001000),
    /**
     * Enable XON/XOFF flow control on output.
     */
    IXON	(0002000),
    /**
     * (XSI) Typing any character will restart stopped output. 
     * (The default is to allow just the START character to restart output.)
     */
    IXANY	(0004000),
    /**
     * Enable XON/XOFF flow control on input.
     */
    IXOFF	(0010000),
    /**
     * (not in POSIX) Ring bell when input queue is full.  
     * Linux does not implement this bit, and acts as if it is always set.
     */
    IMAXBEL	(0020000),
    /**
     * (since Linux 2.6.4) (not in POSIX) Input is UTF8; 
     * this allows character-erase to be correctly performed in cooked mode.
     */
    IUTF8	(0040000);
    
    public final int value;
    
    InputFlags(int value){
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
