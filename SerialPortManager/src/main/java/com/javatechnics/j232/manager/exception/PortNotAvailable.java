/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javatechnics.j232.manager.exception;

/**
 *
 * @author Kerry Billingham <contact@AvionicEngineers.com>
 */
public class PortNotAvailable extends Exception{

    public PortNotAvailable() {
    }

    public PortNotAvailable(String message) {
        super(message);
    }
    
}
