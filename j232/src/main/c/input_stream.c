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

#include "input_stream.h"

JNIEXPORT jint JNICALL 
Java_com_javatechnics_rs232_stream_SerialPortInputStream_readNative (JNIEnv * env,
                                                                    jobject obj,
                                                                    jint fileDescriptor,
                                                                    jbyteArray buffer,
                                                                    jint offset,
                                                                    jint length){
    unsigned char n_buffer[length-offset];
    syslog(LOG_USER | LOG_DEBUG, "Java Buffer Length : %d Offset: %d FileDescriptor: %d", length, offset, fileDescriptor);
    int result = read(fileDescriptor, n_buffer, length - offset);
    syslog(LOG_USER | LOG_DEBUG, "Read %d bytes.", result);
    if (result == -1){
        throw_ioexception(env, errno);
    } else {
        (*env)->SetByteArrayRegion(env, buffer, offset, result, n_buffer);
    }
    return result;
    //return 0;
}
