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
 * This Enumeration represents the output flag-member of the C
 * struct 'termios'. Their values do not necessarily coincide with those
 * define in C header files however the String names do. 
 * The Javadoc comments are also taken from termios.h.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum OutputFlags implements EnumValue{
    /**
     * Enable implementation-defined output processing.
     */
    OPOST	(0000001),
    /**
     * Map lowercase characters to uppercase on output (NOT in POSIX).
     */
    OLCUC	(0000002),
    /**
     * Map NL to CR-NL on output (XSI).
     */
    ONLCR	(0000004),
    /**
     * Map CR to NL on output.
     */
    OCRNL	(0000010),
    /**
     * Don't output CR at column 0.
     */
    ONOCR	(0000020),
    /**
     * Don't output CR.
     */
    ONLRET	(0000040),
    /**
     * Send fill characters for a delay, rather than using a timed delay.
     */
    OFILL	(0000100),
    /**
     * Fill character is ASCII DEL (0177). If unset, fill character is ASCII NUL
     * ('\0'). (Not in POSIX). (Not in Linux).
     */
    OFDEL	(0000200),
    /**
     * **MASK** Newline delay mask. Values are NL0 and NL1. [requires native
     * library to have been compiled with _BSD_SOURCE or _SVID_SOURCE or
     * _XOPEN_SOURCE].
     */
    NLDLY	(0000400),
    /**
     * New line delay value 0. Note actual time delay not known.
     */
    NL0         (0000000),
    /**
     * New line delay value 1. Note actual time delay not known.
     */
    NL1         (0000400),
    /**
     * **MASK** Carriage return delay mask. Values are CR0, CR1, CR2 and CR3. 
     * [requires native libarary to have been compiled with _BSD_SOURCE
     * or _SVID_SOURCE or _XOPEN_SOURCE].
     */
    CRDLY	(0003000),
    /**
     * Carriage return delay 0. Note actual delay time unknown.
     */
    CR0         (0000000),
    /**
     * Carriage return delay 1. Note actual time delay unknown.
     */
    CR1         (0001000),
    /**
     * Carriage return delay 2. Note actual time delay unknown.
     */
    CR2         (0002000),
    /**
     * Carriage return delay 3. Note actual time delay unknown.
     */
    CR3         (0003000),
    /**
     * **MASK** Horizontal tab delay mask. Values are TAB0, TAB1, TAB2, TAB3
     * (or XTABS). A value of TAB3, that is XTABS, expands tabs to spaces (with
     * tab stops every eight columns. [requires _BSD_SOURCE or _SVID_SOURCE or
     * _XOPEN_SOURCE].
     */
    TABDLY	(0014000),
    /**
     * Tab delay 0. Note actual time delay unknown.
     */
    TAB0	(0000000),
    /**
     * Tab delay value 1. Note actual time delay unknown.
     */
    TAB1        (0004000),
    /**
     * Tab delay value 2. Note actual time delay unknown.
     */
    TAB2	(0010000),
    /**
     * Tab delay value 3. Note actual time delay unknown.
     */
    TAB3	(0014000),
    /**
     * **MASK** Backspace delay mask. Values are BS0 or BS1. 
     * (Has never been implemented) [requires the native library to have been
     * compiled with _BSD_SOURCE or _SVID_SOURCE or _XOPEN_SOURCE].
     */
    BSDLY	(0020000),
    /**
     * Backspace delay 0. Note actual time delay unknown.
     */
    BS0         (0000000),
    /**
     * Backspace delay 1. Note actual time delay unknown.
     */
    BS1         (0020000),
    /**
     * **MASK** Form feed delay mask. Values are FF0 or FF1. [requires the native
     * library to have been compiled with _BSD_SOURCE or 
     * _SVID_SOURCE or _XOPEN_SOURCE].
     */
    FFDLY	(0100000),
    /**
     * Form feed delay value 0. Note actual time delay unknown.
     */
    FF0         (0000000),
    /**
     * Form feed delay value 1. Note actual time delay unknown.
     */
    FF1         (0100000),
    /**
     * **MASK** Vertical tab delay mask. Values are VT0 or VT1.
     */
    VTDLY	(0040000),
    /**
     * Vertical tab delay value 0. Note actual time delay unknown.
     */
    VT0         (0000000),
    /**
     * Vertical tab delay value 1. Note actual time delay unknown.
     */
    VT1         (0040000),
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
