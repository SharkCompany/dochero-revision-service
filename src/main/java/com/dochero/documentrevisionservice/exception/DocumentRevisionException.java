package com.dochero.documentrevisionservice.exception;

public class DocumentRevisionException extends RuntimeException {
    public DocumentRevisionException(String message) {
        super(message);
    }

    public DocumentRevisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentRevisionException(Throwable cause) {
        super(cause);
    }

    public DocumentRevisionException() {
    }
}
