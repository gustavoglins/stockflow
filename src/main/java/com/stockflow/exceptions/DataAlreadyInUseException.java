package com.stockflow.exceptions;

public class DataAlreadyInUseException extends RuntimeException {
    public DataAlreadyInUseException(String message) {
        super(message);
    }
}
