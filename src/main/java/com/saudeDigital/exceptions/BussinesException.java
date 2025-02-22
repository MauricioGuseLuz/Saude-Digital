package com.saudeDigital.exceptions;

public class BussinesException extends RuntimeException {

    private static final int errorCode = 400;

    private final long serialVersionUID = 1L;
    public BussinesException(String message) {
        super(message);
    }
    public int getErrorCode() {
        return errorCode;
    }
}
