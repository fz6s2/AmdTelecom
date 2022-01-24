package org.amd.task04.service;

import org.amd.task04.exception.BusinessException;

/**
 * Notification service interface
 */
public interface NotificationService {
    void send(String from, String to, String message) throws BusinessException;
}
