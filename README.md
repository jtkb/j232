# j232
This repository is for j232, a Java library that facilitates the use of serial ports (RS232) in a Java virtual machine on the Linux platform.  It has a complimentary repository, libj232, that handles the required native functions.

Currently it is a work-in-progress and is partially functional. The associated native library which can be found here:  
https://github.com/jtkb/libj232

For a detailed description of how j232 is intended to function, see the wiki:  
https://github.com/jtkb/j232/wiki  

Overview
--------
`Because of Java's platform-independence, serial interfacing is difficult. Serial interfacing requires a standardized API with platform-specific implementations, which is difficult for Java.` 

(From: [Serial programming/Serial Java Wiki book](http://en.wikibooks.org/wiki/Serial_Programming/Serial_Java#Using_Java_for_Serial_Communication))

In short there is no serial port support as standard in the Java SE. Sun did publish the Java Communications API or javax.comm and once provided implementations for several platforms but sadly Linux was not one of them. It is possible to buy Java packages e.g. serialio.com or use free ones e.g. rxtx.org and each option has its advantages and disadvantages such as cost, non-compliance with the javax.comm API or even bugs in the code. If you have need for serial port access in your Java code (as I do/did), and you're confused about exactly what serial-port Java packages exist especially for Linux (as I was) then you're in the same position as I was and going nowhere fast!

At the time of this writing for Linux the only feasible free option (in my opinion) was the rxtx package. I have to admit I have not used this package at all but when I carried out some research I found it very difficult to track down where it was located on the internet. Packages existed in my Linux distro's repositories but I wasn't sure if rxtx was/is being maintained. Also from what information I could find on rxtx it was a little too complicated for my liking and tried to do too much for the developer. For example, to open a serial port and write some character data to it, I can do the following :

```C
#include <stdio.h>
#include <fcntl.h>

int main(char *argv[], int argc){
	int fd = open("/dev/ttyS0", O_WRONLY | O_NOCTTY);
	if (fd != -1){
		write(fd, "Some text to the serial port.\n", 31);
		close(fd);
	} else {
		printf("Could not open serial port.");
		return -1;
	}
	return 0;
}
```
It occured to me that writing a Java package and its complimentary native library would be relatively easy in Linux. Simply wrap the native function calls with JNI functions which can then be called from Java classes. Don't even attempt to try and do anything complicated in the C or Java code. Aim to provide the same workflow to the Java developer as a C developer encounters when programming serial ports. Further simplicity can be gained if support is limited to Linux, *nix or POSIX platforms plus resources and time are better concentrated ultimately to provide a reliable library.

Why Another Java Serial Port Library?
-------------------------------------
 1. I needed to access serial ports in Java on a Linux platform.
 2. RxTx being the only viable option to me seemed too complicated and I had some concerns over its reliability. I was also not sure if it was being maintained.
 3. I wanted to understand more about serialport/RS232 programming.
 4. I wanted to improve my C programming.
 5. I wanted to improve my knowledge of the Java Native Interface, JNI.
 6. I wanted to improve my unit-testing knowledge and skills.
 7. I wanted something simple.

Goals
-----
  1. Provide a reliable and simple Java package and native library to allow the use of serial ports on the Linux platform from Java applications and attempt to allow porting to other *nix and POSIX platforms.
  2. Provide good documentation in code as well as user guides and/or wikis.
  3. Employ unit testing.

Components
----------

As a consequence that Java does not provide native support to access serial ports, a Java application-facing package and a native-code library are required.
  1. j232 - This is the Java application-facing project. Application calls to classes within this package ultimately call through to the native library beneath it, libj232.
  2. libj232 - this is the native library project.

Why No Windows Support?
-----------------------
  1. I do not own a Windows machine so I cannot develop on it or for it.
  2. As a consequence of (1) I therefore cannot test anything I write.
  3. Reducing the number of platforms to support increases the reliability on the platforms that are. I can devot more time to resolving issues and bugs on fewer platforms.
  4. I have no intention of obtaining a Windows machine because Linux offers all that I need plus more.


This and the j232 library are released under the LGPL 2.1 licence and you should consult the LICENSE.txt file included in the top-level directory to determine whether you wish to use this and j232.

Requirements
------------
For the Java package:
  1. JDK - the minimum version is yet to be determined.
  2. Netbeans - while any IDE can (or cannot) be used the j232 library was built using the Netbeans IDE.
  3. Maven (optional) if not using Netbeans for example.

For the JNI library:
 1. GNU C compiler - the minimum version is yet to be determined
 2. GNU Linker - the minimum version is yet to be determined
 3. GNU Make utility

References
----------
* http://en.wikibooks.org/wiki/Serial_Programming
  * http://en.wikibooks.org/wiki/Serial_Programming/Serial_Java
  * http://en.wikibooks.org/wiki/Serial_Programming/Serial_Linux
* http://www.oracle.com/technetwork/java/index-jsp-141752.html
  * http://download.oracle.com/docs/cd/E17802_01/products/products/javacomm/reference/api/javax/comm/package-summary.html
* http://rxtx.qbang.org/wiki/index.php/Main_Page
* https://serialio.com/products/applications/custom-prior-software-versions/java-serial-port
