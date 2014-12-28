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
 * man tty_ioctl.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum IOCTRLRequests {
    TCGETS	(0x5401),
    TCSETS	(0x5402),
    TCSETSW	(0x5403),
    TCSETSF	(0x5404),
    TCGETA	(0x5405),
    TCSETA	(0x5406),
    TCSETAW	(0x5407),
    TCSETAF	(0x5408),
    TCSBRK	(0x5409),
    TCXONC	(0x540A),
    TCFLSH	(0x540B),
    TIOCMGET	(0x5415),
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
