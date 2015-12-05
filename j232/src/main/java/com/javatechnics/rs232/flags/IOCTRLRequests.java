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
 * This enum represents the underlying Linux IOCTRL flags relevant to the 
 * serial port and as found in <termios.h>. The values found here make no
 * attempt to match those found in native header files in case these change.
 * Instead the native JNI function transforms these values (which are known and 
 * under our control) to those found in the native header files.
 * For further information on the native functions relevant to this Enum, see
 * man tty_ioctl. These Javadocs are based upon release 3.54 of the Linux 
 * man-pages project.  A description of the project, and information about 
 * reporting bugs, can be found at http://www.kernel.org/doc/man-pages/.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum IOCTRLRequests {
    /**
     * Equivalent to tcgetattr(fd, argp).
     * Get the current serial port settings.
     */
    TCGETS	(0x5401),
    /**
     * Equivalent to tcsetattr(fd, TCSANOW, argp).
     * Set the current serial port settings.
     */
    TCSETS	(0x5402),
    /**
     * Equivalent to tcsetattr(fd, TCSADRAIN, argp).
     * Allow the output buffer to drain, and set the current serial port 
     * settings.
     */
    TCSETSW	(0x5403),
    /**
     * Equivalent to tcsetattr(fd, TCSAFLUSH, argp).
     * Allow the output buffer to drain, discard pending input, and set 
     * the current serial port settings.
     */
    TCSETSF	(0x5404),
    TCGETA	(0x5405),
    TCSETA	(0x5406),
    TCSETAW	(0x5407),
    TCSETAF	(0x5408),
    TCSBRK	(0x5409),
    TCXONC	(0x540A),
    TCFLSH	(0x540B),
    /**
     * Get the status of the modem bits.
     */
    TIOCMGET	(0x5415),
    /**
     * Set the status of the modem bits.
     */
    TIOCMSET	(0x5418),
    TIOCGSOFTCAR    (0x5419),
    TIOCSSOFTCAR    (0x541A),
    FIONREAD    (0x541B);
    //Modem Control lines
    
    
    public final int value;
    private IOCTRLRequests(int value) {
        this.value = value;
    }
    
    
}
