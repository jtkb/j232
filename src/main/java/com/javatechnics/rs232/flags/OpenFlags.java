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

/** This class represents the underlying Unix open() function flags. They make
 * no attempt to match in value to those found in fcntl.h as these could, in
 * theory change. However The values defined here are under our control and
 * so can be #define (with the same values) in a C header and then converted to the underlying C
 * values in fcntl.h.
 *
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum OpenFlags {
    O_APPEND    (0x00001),
    O_ASYNC     (0x00002),
    O_CLOEXEC   (0x00004),
    O_CREAT     (0x00008),
    O_DIRECT    (0x00010),
    O_DIRECTORY (0X00020),
    O_DSYNC     (0X00040),
    O_EXCL      (0X00080),
    O_LARGEFILE (0X00100),
    O_NOATIME   (0X00200),
    O_NOCTTY    (0X00400),
    O_NOFOLLOW  (0X00800),
    O_NONBLOCK  (0X01000),
    O_SYNC      (0X02000),
    O_TRUNC     (0X04000),
    O_RDONLY    (0X08000),
    O_WRONLY    (0X10000),
    O_RDWR      (0X20000);
    
    public final int value;
    
    OpenFlags(int value){
        this.value = value;
    }
    
}
