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

/**
 * This enum represents the underlying Unix local flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum ControlCharacters {
VINTR       (0),
VQUIT       (1),
VERASE      (2),
VKILL       (3),
VEOF        (4),
VTIME       (5),
VMIN        (6),
VSWTC       (7),
VSTART      (8),
VSTOP       (9),
VSUSP       (10),
VEOL        (11),
VREPRINT    (12),
VDISCARD    (13),
VWERASE     (14),
VLNEXT      (15),
VEOL2       (16);

    public final int value;

    private ControlCharacters(int value) {
        this.value = value;
    }

    
    
}
