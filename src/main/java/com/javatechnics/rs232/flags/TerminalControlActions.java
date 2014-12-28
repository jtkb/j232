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
 * This enumeration reflects the native control parameters passed to tcsetattr().
 * Refer to termios.h.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum TerminalControlActions {
    /**
     * The change occurs immediately.
     */
    TCSANOW     (00000001),
    /**
     * The change occurs after all output to the file descriptor has been 
     * transmitted.
     */
    TCSADRAIN   (00000002),
    /**
     * The change occurs after all output to the file descriptor has been
     * transmitted and all input that has been received but not read will be
     * discarded before the change is made.
     */
    TCSAFLUSH   (00000004);
    
    public final int value;
    
    TerminalControlActions(int value){
        this.value = value;
    }
}
