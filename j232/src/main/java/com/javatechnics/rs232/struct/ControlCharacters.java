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
package com.javatechnics.rs232.struct;

import com.javatechnics.rs232.EnumValue;

/**
 * This enum represents the underlying Unix local flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files. The following Javadoc comments
 * are taken from the Linux man-pages 3.54. A description of the project, 
 * and information about reporting bugs, can be found at 
 * http://www.kernel.org/doc/man-pages/.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum ControlCharacters implements EnumValue {
    /**
     * (003, ETX, Ctrl-C, or also 0177, DEL, rubout) Interrupt character (INTR).
     * Send a SIGINT signal.  Recognized when ISIG is set, and then not passed 
     * as input.
     */
    VINTR       (0),
    /**
     * (034, FS, Ctrl-\) Quit character (QUIT).  Send SIGQUIT signal.  
     * Recognized when ISIG is set, and then not passed as input.
     */
    VQUIT       (1),
    /**
     * (034, FS, Ctrl-\) Quit character (QUIT).  Send SIGQUIT signal.  
     * Recognized when ISIG is set, and then not passed as input.
     */
    VERASE      (2),
    /**
     * (003, ETX, Ctrl-C, or also 0177, DEL, rubout) Interrupt character (INTR).
     * Send a SIGINT signal.  Recognized when ISIG is set, and then not passed 
     * as input.
     */
    VKILL       (3),
    /**
     * (004, EOT, Ctrl-D) End-of-file character (EOF).  
     * More precisely: this character causes the pending tty buffer to be sent 
     * to the waiting user program without waiting for end-of-line.  If it is
       the first character of the line, the read(2) in the user program 
       * returns 0, which signifies end-of-file.  
       * Recognized when ICANON is set, and then not passed as input.
     */
    VEOF        (4),
    /**
     * Timeout in deciseconds for noncanonical read (TIME).
     */
    VTIME       (5),
    /**
     * Minimum number of characters for noncanonical read (MIN).
     */
    VMIN        (6),
    /**
     * (not in POSIX; not supported under Linux; 0, NUL) Switch character (
     * SWTCH).  Used in System V to switch shells in shell layers, a predecessor
     * to shell job control.
     */
    VSWTCH      (7),
    /**
     * (021, DC1, Ctrl-Q) Start character (START).  Restarts output stopped by 
     * the Stop character.  Recognized when IXON is set, and then not passed 
     * as input.
     */
    VSTART      (8),
    /**
     * (023, DC3, Ctrl-S) Stop character (STOP).  Stop output until Start 
     * character typed.  Recognized when IXON is set, and then not passed as 
     * input.
     */
    VSTOP       (9),
    /**
     * (032, SUB, Ctrl-Z) Suspend character (SUSP).  Send SIGTSTP signal.  
     * Recognized when ISIG is set, and then not passed as input.
     */
    VSUSP       (10),
    /**
     * (not in POSIX; 0, NUL) Yet another end-of-line character (EOL2).  
     * Recognized when ICANON is set.
     */
    VEOL        (11),
    /**
     * (not in POSIX; 022, DC2, Ctrl-R) Reprint unread characters (REPRINT).  
     * Recognized when ICANON and IEXTEN are set, and then not passed as input.
     */
    VREPRINT    (12),
    /**
     * (not in POSIX; not supported under Linux; 017, SI, Ctrl-O) 
     * Toggle: start/stop discarding pending output.  Recognized when IEXTEN is 
     * set, and then not passed as input.
     */
    VDISCARD    (13),
    /**
     * (not in POSIX; 027, ETB, Ctrl-W) Word erase (WERASE).  
     * Recognized when ICANON and IEXTEN are set, and then not passed as input.
     */
    VWERASE     (14),
    /**
     * (not in POSIX; 026, SYN, Ctrl-V) Literal next (LNEXT).  Quotes the next 
     * input character, depriving it of a possible special meaning.  
     * Recognized when IEXTEN is set, and  then  not  passed  as input.
     */
    VLNEXT      (15),
    /**
     * (not in POSIX; 0, NUL) Yet another end-of-line character (EOL2).  
     * Recognized when ICANON is set.
     */
    VEOL2       (16);

    public final int value;

    private ControlCharacters(int value) {
        this.value = value;
    }

    /**
     * Returns the value of this Enum member.
     * @return 
     */
    public int getValue() {
        return value;
    }

    
    
}
