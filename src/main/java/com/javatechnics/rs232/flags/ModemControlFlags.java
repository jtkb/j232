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
 *
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum ModemControlFlags {
    TIOCM_LE	(0x001),
    TIOCM_DTR	(0x002),
    TIOCM_RTS	(0x004),
    TIOCM_ST	(0x008),
    TIOCM_SR	(0x010),
    TIOCM_CTS	(0x020),
    TIOCM_CAR	(0x040),
    TIOCM_RNG	(0x080),
    TIOCM_DSR	(0x100),
    TIOCM_CD	(TIOCM_CAR.value),
    TIOCM_RI	(TIOCM_RNG.value);
    
    public final int value;

    private ModemControlFlags(int value) {
        this.value = value;
    }
    
    
    
}
