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
 * those found in the native header files. The following Javadoc comments are
 * based upon release 3.54 of the Linux man-pages project.  
 * A description of the project, and information about reporting bugs, 
 * can be found at http://www.kernel.org/doc/man-pages/.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum LocalFlags implements EnumValue{
    /**
     * When any of the control characters INTR, QUIT, SUSP or DSUSP are received,
     * generate the corresponding signal.
     */
    ISIG	(0000001),
    /**
     * Enable canonical mode.
     */
    ICANON	(0000002),
    /**
     * (not in POSIX; not supported under Linux) If ICANON is also set, 
     * terminal is uppercase only.  Input is converted to lowercase, except for 
     * characters preceded by \.  On output, uppercase characters are preceded 
     * by \ and lowercase characters are converted to uppercase.  [
     * requires _BSD_SOURCE or _SVID_SOURCE or _XOPEN_SOURCE]
     */
    XCASE	(0000004),
    /**
     * Echo input characters.
     */
    ECHO	(0000010),
    /**
     * If ICANON is also set, the ERASE character erases the preceeding input
     * character and WERASE the preceeding word.
     */
    ECHOE	(0000020),
    /**
     * If ICANON is also set the kill character erases the current line.
     */
    ECHOK	(0000040),
    /**
     * If ICANON is also set, echo the NL character even if ECHO is not set.
     */
    ECHONL	(0000100),
    /**
     * Disable flushing the input and output queues when generating signals for 
     * the INT, QUIT, and SUSP characters.
     */
    NOFLSH	(0000200),
    /**
     * Send the SIGTTOU signal to the process group of a background process 
     * which tries to write to its controlling terminal.
     */
    TOSTOP	(0000400),
    /**
     * (not in POSIX) If ECHO is also set, terminal special characters other 
     * than TAB, NL, START, and STOP are echoed as ^X, where X is the character 
     * with ASCII code 0x40 greater  than  the  special character.  
     * For example, character 0x08 (BS) is echoed as ^H.  
     * [requires _BSD_SOURCE or _SVID_SOURCE]
     */
    ECHOCTL     (0001000),
    /**
     * (not in POSIX) If ICANON and ECHO are also set, characters are printed as
     * they are being erased.  [requires _BSD_SOURCE or _SVID_SOURCE]
     */
    ECHOPRT     (0002000),
    /**
     * (not in POSIX) If ICANON is also set, KILL is echoed by erasing each 
     * character on the line, as specified by ECHOE and ECHOPRT.  
     * [requires _BSD_SOURCE or _SVID_SOURCE]
     */
    ECHOKE	(0004000),
    /**
     * (not in POSIX; not supported under Linux) Output is being flushed.  
     * This flag is toggled by typing the DISCARD character.  
     * [requires _BSD_SOURCE or _SVID_SOURCE]
     */
    FLUSHO      (0010000),
    /**
     * (not  in  POSIX;  not  supported under Linux) All characters in the input
     * queue are reprinted when the next character is read.  
     * (bash(1) handles typeahead this way.)  
     * [requires _BSD_SOURCE or _SVID_SOURCE]
     */
    PENDIN	(0040000),
    /**
     * Enable implementation-defined input processing.  This flag, as well as 
     * ICANON must be enabled for the special characters EOL2, LNEXT, REPRINT, 
     * WERASE to be interpreted, and for the IUCLC flag to be effective.
     */
    IEXTEN      (0100000);
    public final int value;
    
    LocalFlags(int value){
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