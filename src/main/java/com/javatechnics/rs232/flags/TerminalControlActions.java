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
