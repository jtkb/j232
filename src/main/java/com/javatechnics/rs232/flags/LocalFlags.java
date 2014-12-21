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
package com.javatechnics.rs232.flags;

/**
 * This enum represents the underlying Unix local flags used in the termios
 * structure, see termios.h. The values found here make no attempt to match those
 * found in native header files in case these change. Instead the native JNI 
 * function transforms these values (which are known and under our control) to
 * those found in the native header files.
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum LocalFlags {
    ISIG	(0000001),
    ICANON	(0000002),
    XCASE	(0000004),
    ECHO	(0000010),
    ECHOE	(0000020),
    ECHOK	(0000040),
    ECHONL	(0000100),
    NOFLSH	(0000200),
    TOSTOP	(0000400),
    ECHOCTL     (0001000),
    ECHOPRT     (0002000),
    ECHOKE	(0004000),
    FLUSHO      (0010000),
    PENDIN	(0040000),
    IEXTEN      (0100000);
    public final int value;
    
    LocalFlags(int value){
        this.value = value;
    }
}