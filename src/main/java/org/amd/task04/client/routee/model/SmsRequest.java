package org.amd.task04.client.routee.model;

/**
 * SMS Routee API request model
 */
public class SmsRequest {
    private final String body;
    private final String to;
    private final String from;

    public SmsRequest(String body, String to, String from) {
        this.body = body;
        this.to = to;
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}
