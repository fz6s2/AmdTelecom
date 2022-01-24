package org.amd.task04.exception;

/**
 * Common exception for business logic errors
 */
public class BusinessException extends Exception {
    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}
