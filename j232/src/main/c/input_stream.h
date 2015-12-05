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

#ifndef INPUT_STREAM_H
#define	INPUT_STREAM_H

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <termios.h>
#include <sys/ioctl.h>
#include <unistd.h>
#include <string.h>
#include <syslog.h>
#include "jni/com_javatechnics_rs232_stream_SerialPortInputStream.h"

extern int throw_ioexception(JNIEnv *env, int error_number);

JNIEXPORT jint JNICALL
Java_com_javatechnics_rs232_stream_SerialPortInputStream_readNative (JNIEnv * env,\
                                                                    jobject obj,\
                                                                    jint fileDescriptor,\
                                                                    jbyteArray buffer,\
                                                                    jint offset,\
                                                                    jint length);
#endif	/* INPUT_STREAM_H */

