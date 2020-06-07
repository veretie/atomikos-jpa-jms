package com.mits4u.transactionsdemo.service.error;

public class SimulatedRuntimeException extends RuntimeException {
    public SimulatedRuntimeException(String message) {
        super(message);
    }
}
