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

import java.io.IOException;

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
     * @return TRUE if successful.
     * @throws IOException if an I/O error occurs.
     */
    public boolean open(String path, int flags) throws IOException;
}
