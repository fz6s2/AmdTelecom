package org.amd.task04.exception;

/**
 * Exception for API errors
 */
public class ApiClientException extends Exception {
    public ApiClientException(Throwable e) {
        super(e);
    }

    public ApiClientException(String message) {
        super(message);
    }
}
