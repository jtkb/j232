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
 *
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum ModemControlFlags {
    TIOCM_LE	(0x001),
    TIOCM_DTR	(0x002),
    TIOCM_RTS	(0x004),
    TIOCM_ST	(0x008),
    TIOCM_SR	(0x010),
    TIOCM_CTS	(0x020),
    TIOCM_CAR	(0x040),
    TIOCM_RNG	(0x080),
    TIOCM_DSR	(0x100),
    TIOCM_CD	(TIOCM_CAR.value),
    TIOCM_RI	(TIOCM_RNG.value);
    
    public final int value;

    private ModemControlFlags(int value) {
        this.value = value;
    }
    
    
    
}
