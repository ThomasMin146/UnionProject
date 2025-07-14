package com.union.insurance.exception;

public class DuplicateContractNumberException extends RuntimeException {

    public DuplicateContractNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
