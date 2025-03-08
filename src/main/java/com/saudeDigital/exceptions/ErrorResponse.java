package com.saudeDigital.exceptions;

public class ErrorResponse {

    private String message;
    private int errorCode;

    public ErrorResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public int setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return errorCode;
    }
    public String setMessage(String message) {this.message = message;
        return message;}
}
