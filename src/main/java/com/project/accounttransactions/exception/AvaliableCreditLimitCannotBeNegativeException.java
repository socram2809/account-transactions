package com.project.accounttransactions.exception;

public class AvaliableCreditLimitCannotBeNegativeException extends RuntimeException {

    public AvaliableCreditLimitCannotBeNegativeException(String message) {
        super(message);
    }
}
