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
 * This enum represents the underlying Linux tcflush() queue_selector flags. 
 * The values found here make no attempt to match those found in native header 
 * files in case these change. Instead the native JNI function transforms these 
 * values (which are known and under our control) to those found in the native 
 * header files.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum QueueSelector {
    /**
     * Flushes the data received but nor read.
     */
    TCIFLUSH	(0),
    /**
     * Flushes the data written but not transmitted.
     */
    TCOFLUSH	(1),
    /**
     * Flushes both the data received but not read and data written but not
     * transmitted.
     */
    TCIOFLUSH	(2);
    
    public final int value;
    
    private QueueSelector (int value){
        this.value = value;
    }

}
