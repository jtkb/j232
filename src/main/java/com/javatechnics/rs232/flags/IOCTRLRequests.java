/*
 * Copyright 2014 Kerry Billingham <java@avionicengineers.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
