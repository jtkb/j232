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
package com.javatechnics.rs232;

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
