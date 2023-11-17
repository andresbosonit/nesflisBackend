package com.nesflisback.nesflisback.exceptions;

import java.util.Date;

public class UnprocessableEntityException extends RuntimeException {
    private final CustomError error;

    public UnprocessableEntityException(String message) {
        super(message);
        error = new CustomError(new Date(), 422, message);
    }

    public CustomError getError() {
        return error;
    }
}
