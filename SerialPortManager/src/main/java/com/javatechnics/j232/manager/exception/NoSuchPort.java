/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javatechnics.j232.manager.exception;

/**
 * This exception is thrown when a specified port does not physically exist.
 * 
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class NoSuchPort extends Exception {

    /**
     * Creates a new instance of <code>NoSuchPort</code> without detail message.
     */
    public NoSuchPort() {
    }

    /**
     * Constructs an instance of <code>NoSuchPort</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoSuchPort(String msg) {
        super(msg);
    }
}
