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
