package com.mits4u.transactionsdemo.service.error;

public class SimulatedCheckedException extends Exception {
    public SimulatedCheckedException(String message) {
        super(message);
    }
}
