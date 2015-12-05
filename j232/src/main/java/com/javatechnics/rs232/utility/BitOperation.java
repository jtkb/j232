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
package com.javatechnics.rs232.utility;

import com.javatechnics.rs232.EnumValue;
import java.util.Iterator;
import java.util.Set;

/**
 * This class provides several methods that provide bitwise operations
 * on the values passed on e.g. OR.
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class BitOperation {
    
    /**
     * This function converts a Set of EnumValue objects
     * into an OR-ed single int value.
     * @param <T> any type that extends EnumValue.
     * @param flags A Set of EnumValues.
     * @return a single int that represents the OR-ed values within the specified
     * set.
     */
    public  static <T extends EnumValue> int orValues(Set<T> flags){
        int f = 0;
        Iterator<T> it = flags.iterator();
        while (it.hasNext()) {
            f |= it.next().getValue();
        }
        return f;
    }
    
}
