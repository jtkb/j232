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
package com.javatechnics.rs232;

import com.javatechnics.rs232.flags.OpenFlags;
import java.io.IOException;
import java.util.EnumSet;

/** An Openable is an object that can open an RS232/COM port. The open method 
 * attempts to acquire system COM resource previously specified to the object
 * by some other means.
 *
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public interface Openable {
    
    /**
     * Open an RS232/COM port resource.
     * @param path String representing the path in the file structure to the port.
     * @param flags the flags used to open the COM port.
     * @throws IOException if an I/O error occurs.
     * @return the boolean
     */
    public boolean open(String path, EnumSet<OpenFlags> flags) throws IOException;
}
