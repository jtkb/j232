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

/**
 * This enum represents the underlying Unix local flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum InputFlags {
    IGNBRK	(0000001),
    BRKINT	(0000002),
    IGNPAR	(0000004),
    PARMRK	(0000010),
    INPCK	(0000020),
    ISTRIP	(0000040),
    INLCR	(0000100),
    IGNCR	(0000200),
    ICRNL	(0000400),
    IUCLC	(0001000),
    IXON	(0002000),
    IXANY	(0004000),
    IXOFF	(0010000),
    IMAXBEL	(0020000),
    IUTF8	(0040000);
    
    public final int value;
    
    InputFlags(int value){
        this.value = value;
    }
    
}
