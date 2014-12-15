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
public enum ControlCharacters {
VINTR       (0),
VQUIT       (1),
VERASE      (2),
VKILL       (3),
VEOF        (4),
VTIME       (5),
VMIN        (6),
VSWTC       (7),
VSTART      (8),
VSTOP       (9),
VSUSP       (10),
VEOL        (11),
VREPRINT    (12),
VDISCARD    (13),
VWERASE     (14),
VLNEXT      (15),
VEOL2       (16);

    public final int value;

    private ControlCharacters(int value) {
        this.value = value;
    }

    
    
}
