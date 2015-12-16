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
#ifndef _SERIAL_H
#define _SERIAL_H

#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <termios.h>
#include <sys/ioctl.h>
#include <unistd.h>
#include <string.h>
#include <jni.h>
#include "jni/com_javatechnics_rs232_port_Serial.h"
#ifdef DEBUG
#include <syslog.h>
#endif
#include <syslog.h>
/*
 Java Class Strings
 */
#define CONTROL_CHARACTERS_CLASS_STRING "com/javatechnics/rs232/struct/ControlCharacters"
#define CONTROL_FLAGS_CLASS_STRING "com/javatechnics/rs232//flags/ControlFlags"
#define TERMIOS_CLASS_STRING "com/javatechnics/rs232/struct/TermIOS"
#define TERMINAL_CONTROL_ACTIONS_CLASS_STRING "com/javatechnics/rs232/flags/TerminalControlActions"

/*
 Have included these defines here because they are defined in
 asm-generic/termios.h only. Netbeans complaisn that it cannot find them
 */
#ifndef TIOCM_OUT1
#define TIOCM_OUT1 0x2000
#endif
#ifndef TIOCM_OUT2
#define TIOCM_OUT2 0x4000
#endif
#ifndef TIOCM_LOOP
#define TIOCM_LOOP 0x8000
#endif

const int java_open_flags[] = {0x00001, 0x00002, 0x00004, 0x00008, \
                        0x00010, 0X00020, 0X00040, 0X00080, \
                        0X00100, 0X00200, 0X00400, 0X00800, \
                        0X01000, 0X02000, 0X04000, 0X08000, \
                        0X10000, 0X20000};

const int open_flags[] = { O_APPEND,    O_ASYNC,    O_CLOEXEC,  O_CREAT, \
                           O_DIRECT,    O_DIRECTORY,O_DSYNC,   O_EXCL,
                           O_LARGEFILE, O_NOATIME,  O_NOCTTY,   O_NOFOLLOW, \
                           O_NONBLOCK,  O_SYNC,     O_TRUNC,    O_RDONLY, \
                           O_WRONLY,    O_RDWR };
const int number_open_flags = sizeof(open_flags) / sizeof(open_flags[0]);

const int java_control_flags[] = {   0010017,    0000000,    0000001,    0000002, \
                                0000003,    0000004,    0000005,     0000006, \
                                0000007,    0000010,    0000011,    0000012, \
                                0000013,    0000014,    0000015,    0000016, \
                                0000017,    0010001,    0010002,    0010003, \
                                0010004,    0010005,    0010006,    0010007, \
                                0010010,    0010011,    0010012,    0010013, \
                                0010014,    0010015,    0010016,    0010017, \
                                0000016,    0000017,    0000060,    0000000, \
                                0000020,    0000040,    0000060,    0000100, \
                                0000200,    0000400,    0001000,    0002000, \
                                0004000,    020000000000};
const int control_flags[] = \
                        {CBAUD,     B0,         B50,        B75,        \
                        B110,       B134,       B150,       B200,       \
                        B300,       B600,       B1200,      B1800,      \
                        B2400,      B4800,      B9600,      B19200,     \
                        B38400,     B57600,     B115200,    B230400,    \
                        B460800,    B500000,    B576000,    B921600,    \
                        B1000000,   B1152000,  B1500000,   B2000000,    \
                        B2500000,   B3000000,   B3500000,   B4000000,   \
                        EXTA,       EXTB,       CSIZE,      CS5,        \
                        CS6,        CS7,        CS8,        CSTOPB,     \
                        CREAD,      PARENB,     PARODD,     HUPCL,      \
                        CLOCAL,     CRTSCTS};



const int number_control_flags = sizeof(control_flags) / sizeof(control_flags[0]);

const int java_local_flags[] = {0000001,     0000002,   0000004,     0000010, \
                                0000020,    0000040,    0000100,    0000200, \
                                0000400,    0001000,    0002000,    0004000, \
                                0010000,    0040000,    0100000};

const int local_flags[] = \
                        {ISIG,      ICANON,     XCASE,       ECHO, \
                        ECHOE,      ECHOK,      ECHONL,     NOFLSH, \
                        TOSTOP,     ECHOCTL,    ECHOPRT,    ECHOKE, \
                        FLUSHO,     PENDIN,     IEXTEN};

const int number_local_flags = sizeof(local_flags) / sizeof(local_flags[0]);

const int java_input_flags[] = \
                        {   0000001,    0000002,    0000004,    0000010, \
                            0000020,    0000040,    0000100,    0000200, \
                            0000400,    0001000,    0002000,    0004000, \
                            0010000,    0020000,    0040000};

const int input_flags[] = \
                        { IGNBRK,   BRKINT,     IGNPAR,     PARMRK, \
                        INPCK,      ISTRIP,     INLCR,      IGNCR, \
                        ICRNL,      IUCLC,      IXON,       IXANY, \
                        IXOFF,      IMAXBEL,    IUTF8};

const int number_input_flags = sizeof(input_flags) / sizeof(input_flags[0]);

const int java_output_flags[] = \
                        {0000001,   0000002,    0000004,    0000010, \
                        0000020,    0000040,    0000100,    0000200, \
                        0000400,    0000000,    0000400,    0003000, \
                        0000000,    0001000,    0002000,    0003000, \
                        0014000,    0000000,    0004000,    0010000, \
                        0014000,    0020000,    0000000,    0020000, \
                        0100000,    0000000,    0100000,    0040000, \
                        0000000,    0040000,    0014000};

const int output_flags[] = \
                        {OPOST,     OLCUC,      ONLCR,      OCRNL, \
                        ONOCR,      ONLRET,	OFILL,      OFDEL, \
                        NLDLY,      NL0,        NL1,        CRDLY,
                        CR0,        CR1,        CR2,        CR3, \
                        TABDLY,     TAB0,       TAB1,       TAB2, \
                        TAB3,       BSDLY,      BS0,        BS1, \
                        FFDLY,      FF0,        FF1,        VTDLY, \
                        VT0,        VT1,        XTABS};

const int number_output_flags = sizeof(output_flags) / sizeof(output_flags[0]);

const int java_control_character_flags[] = {
                            0,      1,      2,      3,      4,
                            5,      6,      7,      8,
                            9,      10,     11,     12,
                            13,     14,     15,     16};

const int control_character_flags[] = \
                        {   VINTR,      VQUIT,  VERASE, VKILL,
                            VEOF,       VTIME,  VMIN,   VSWTC,
                            VSTART,     VSTOP,   VSUSP,   VEOL,
                            VREPRINT,   VDISCARD,  VWERASE,    VLNEXT,
                            VEOL2};

const int number_control_character_flags = sizeof(control_character_flags) / \
                            sizeof(control_character_flags[0]);

const int java_terminal_settings_flags[] = \
                        {   1,      2,      4};

const int terminal_settings_flags[] = \
                        {   TCSANOW,    TCSADRAIN,      TCSAFLUSH};

const int number_terminal_settings_flags = sizeof(terminal_settings_flags) / \
                            sizeof(terminal_settings_flags[0]);

const char* java_termios_fields[] = {"c_iflag", "c_oflag", "c_cflag", "c_lflag",
                                    "c_cc"};
const char* java_termios_field_descriptors[] = { "I", "I", "I", "I", "[B"};

#define JAVA_TERMIOS_FIELD_COUNT 5

const int java_modem_control_requests[] = {0x5401,
    0x5402,
    0x5403,
    0x5404,
    0x5405,
    0x5406,
    0x5407,
    0x5408,
    0x5409,
    0x540A,
    0x540B,
    0x5415,
    0x5418,
    0x5419,
    0x541A,
    0x541B
    };

const int modem_control_requests[] = {
TCGETS,
    TCSETS,
    TCSETSW,
    TCSETSF,
    TCGETA,
    TCSETA,
    TCSETAW,
    TCSETAF,
    TCSBRK,
    TCXONC,
    TCFLSH,
    TIOCMGET,
    TIOCMSET,
    TIOCGSOFTCAR,
    TIOCSSOFTCAR,
    FIONREAD};

const int number_modem_control_requests = 
                sizeof(modem_control_requests) / sizeof(modem_control_requests[0]);

const int modem_control_flags[] = {
//Modem Control lines
    TIOCM_LE,
    TIOCM_DTR,
    TIOCM_RTS,
    TIOCM_ST,
    TIOCM_SR,
    TIOCM_CTS,
    TIOCM_CAR,
    TIOCM_RNG,
    TIOCM_DSR,
    TIOCM_CD,
    TIOCM_RI,
#ifdef TIOCM_OUT1
    TIOCM_OUT1,
    #endif
#ifdef TIOCM_OUT2
    TIOCM_OUT2, 
#endif
#ifdef TIOCM_LOOP
    TIOCM_LOOP,
#endif
    };

const int java_modem_control_flags[] = {
//Modem Control lines
    0x001,
    0x002,
    0x004,
    0x008,
    0x010,
    0x020,
    0x040,
    0x080,
    0x100,
    0x040,
    0x080,
#ifdef TIOCM_OUT1
    0x2000,
#endif
#ifdef TIOCM_OUT2
    0x4000,
#endif
#ifdef TIOCM_LOOP
    0x8000,
#endif
};



const int number_modem_control_flags = 
                sizeof(modem_control_flags) / sizeof(modem_control_flags[0]);

const int java_flush_queue_selector[] = {
    0,
    1,
    2};

const int flush_queue_selector[] = {
    TCIFLUSH,
    TCOFLUSH,
    TCIOFLUSH};

const int number_flush_queue_selectors = sizeof(flush_queue_selector) /  \
                                            sizeof(flush_queue_selector[0]);

int get_real_flags(const int java_flags[], const int native_flags[], \
                            const int selected_flags, const int size);

int get_java_flags(const int java_flags[], const int native_flags[], \
                            const int selected_flags, const int size);

int get_field_ids(JNIEnv* env, jclass cls, const char* const field_names[], \
                                            const char* const field_name_descriptors[],
                                            jfieldID field_ids[], \
                                            const int field_count);

int get_native_value(const int const java_values[], const int const native_flags[],
                            const int java_value, const int size);
#endif
