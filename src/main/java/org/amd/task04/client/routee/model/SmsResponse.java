package org.amd.task04.client.routee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SMS Routee API response model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsResponse {
    private String trackingId;
    private String status;
    private String createdAt;
    private String from;
    private String to;
    private String body;

    public String getTrackingId() {
        return trackingId;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }
}
