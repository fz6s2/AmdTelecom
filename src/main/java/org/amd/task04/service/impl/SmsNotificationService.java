package org.amd.task04.service.impl;

import org.amd.task04.exception.BusinessException;
import org.amd.task04.exception.ApiClientException;
import org.amd.task04.client.routee.RouteeClient;
import org.amd.task04.client.routee.model.SmsResponse;
import org.amd.task04.client.routee.model.TokenResponse;
import org.amd.task04.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sending SMS notifications using Routee API
 */
public class SmsNotificationService implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationService.class);

    private final RouteeClient routeeClient;

    public SmsNotificationService(RouteeClient routeeClient) {
        this.routeeClient = routeeClient;
    }

    /** Sends SMS notification
     *
     * @param from Sender
     * @param to Recipient
     * @param message Message
     * @throws BusinessException Notification error
     */
    @Override
    public void send(String from, String to, String message) throws BusinessException{
        try {
            sendSms(from, to, message, getToken());
        } catch (ApiClientException ex) {
            throw new BusinessException(
                String.format("Error while sending SMS to %s from %s with text %s", to, from, message),
                ex);
        }
    }

    private String getToken() throws ApiClientException {
        logger.debug("Start sending token request");

        TokenResponse tokenResponse = routeeClient.getToken();

        if (tokenResponse == null) {
            throw new ApiClientException("Routee client token response is null");
        }

        logger.debug("Token request was sent successfully");

        return tokenResponse.getAccessToken();
    }

    private void sendSms(String from, String to, String message, String token) throws ApiClientException {
        logger.debug("Sending SMS request to {} from {} text {}", to, from, message);

        if (from == null || to == null || message == null || token == null) {
            throw new IllegalArgumentException("There is a null parameter among from/to/message/token for Routee client");
        }

        SmsResponse response = routeeClient.sendSms(from, to, message, token);

        logger.debug("SMS request sent successfully, track id {}", response.getTrackingId());
    }
}
