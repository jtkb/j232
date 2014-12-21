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
 *
 * @author Kerry Billingham <java@avionicengineers.com>
 */
public enum OutputFlags {
OPOST	(0000001),
OLCUC	(0000002),
ONLCR	(0000004),
OCRNL	(0000010),
ONOCR	(0000020),
ONLRET	(0000040),
OFILL	(0000100),
OFDEL	(0000200),
NLDLY	(0000400),
NL0	(0000000),
NL1	(0000400),
CRDLY	(0003000),
CR0	(0000000),
CR1	(0001000),
CR2	(0002000),
CR3	(0003000),
TABDLY	(0014000),
TAB0	(0000000),
TAB1	(0004000),
TAB2	(0010000),
TAB3	(0014000),
BSDLY	(0020000),
BS0	(0000000),
BS1	(0020000),
FFDLY	(0100000),
FF0	(0000000),
FF1	(0100000),
VTDLY	(0040000),
VT0	(0000000),
VT1	(0040000),
XTABS	(0014000);

    public final int value;
    
    OutputFlags(int value){
        this.value = value;
    }
}
