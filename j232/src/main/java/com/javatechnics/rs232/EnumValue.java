/*
 * Copyright (C) 2015 Kerry Billingham <contact@AvionicEngineers.com>.
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
package com.javatechnics.rs232;

/**
 * This method is primarily intended to be implemented by Enums and 
 * allows the retrieval of the numeric value associated with a member of the 
 * enum. Implementing this interface ensures type-safe method parameter passing. 
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public interface EnumValue {
    /**
     * Returns the numeric value of en enumeration member.
     * @return int value of the enum.
     */
    int getValue();
}
