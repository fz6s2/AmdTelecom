package org.amd.task04.client.routee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amd.task04.config.ApplicationConfig;
import org.amd.task04.exception.ApiClientException;
import org.amd.task04.client.routee.model.SmsRequest;
import org.amd.task04.client.routee.model.SmsResponse;
import org.amd.task04.client.routee.model.TokenResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

/**
 * Http client for sending requests through Routee API
 */
public class RouteeClient {
    private static final String TOKEN_URL = ApplicationConfig.getStrProperty("client.routee.token-url");
    private static final String SMS_URL = ApplicationConfig.getStrProperty("client.routee.sms-url");
    // TODO To provide more security solution for credentials (env, java args, ..)
    private static final String APPLICATION_ID = ApplicationConfig.getStrProperty("client.routee.application-id");
    private static final String APPLICATION_SECRET = ApplicationConfig.getStrProperty("client.routee.application-secret");
    private static final String BASE64_TOKEN = "Basic " +
        new String(Base64.getEncoder().encode((APPLICATION_ID + ":" + APPLICATION_SECRET).getBytes()));
    private static final int timeoutSec = ApplicationConfig.getIntProperty("client.routee.timeout-sec", 10);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Sends SMS notification
     * @param from Sender
     * @param to Recipient
     * @param text Message
     * @param token Auth token
     * @return Response of sending confirmation
     * @throws ApiClientException API error
     */
    public SmsResponse sendSms(String from, String to, String text, String token) throws ApiClientException {
        SmsRequest request = new SmsRequest(text, to, from);

        try {
            return sendSmsRequest(request, token);
        } catch (Exception e) {
            throw new ApiClientException(e);
        }
    }

    /**
     * Receives an auth token
     * @return Token model
     * @throws ApiClientException API error
     */
    public TokenResponse getToken() throws ApiClientException {
        try {
            return sendTokenRequest();
        } catch (Exception e) {
            throw new ApiClientException(e);
        }
    }

    private TokenResponse sendTokenRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(TOKEN_URL))
            .timeout(Duration.ofSeconds(timeoutSec))
            .header("authorization", BASE64_TOKEN)
            .header("content-type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() > 300) {
            throw new IOException("Wrong http status: " + response.statusCode());
        }

        return objectMapper.readValue(response.body(), TokenResponse.class);
    }

    private SmsResponse sendSmsRequest(SmsRequest smsRequest, String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String body = objectMapper.writeValueAsString(smsRequest);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(SMS_URL))
            .timeout(Duration.ofSeconds(timeoutSec))
            .header("authorization", "Bearer " + token)
            .header("content-type", "application/json")
            //.header("Expect", "")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() > 300) {
            throw new IOException("Wrong http status: " + response.statusCode());
        }

        return objectMapper.readValue(response.body(), SmsResponse.class);
    }
}
