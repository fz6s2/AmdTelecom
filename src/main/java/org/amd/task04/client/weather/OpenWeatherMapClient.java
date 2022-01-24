package org.amd.task04.client.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amd.task04.config.ApplicationConfig;
import org.amd.task04.exception.ApiClientException;
import org.amd.task04.client.weather.model.WeatherResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Http client for sending requests through Open Weather Map API
 */
public class OpenWeatherMapClient {
    private static final String WEATHER_URL = ApplicationConfig.getStrProperty("client.weather.weather-url");
    private static final String API_KEY = ApplicationConfig.getStrProperty("client.weather.api-key");
    private static final int timeoutSec = ApplicationConfig.getIntProperty("client.weather.timeout-sec", 10);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Receives weather data by city id
     * @param cityId City id
     * @return Weather model
     * @throws ApiClientException API error
     */
    public WeatherResponse getWeather(String cityId) throws ApiClientException {
        try {
            return sendWeatherRequest(cityId);
        } catch (Exception e) {
            throw new ApiClientException(e);
        }
    }

    private WeatherResponse sendWeatherRequest(String cityId) throws IOException, InterruptedException {
        String uri = WEATHER_URL
            .replace("{cityId}", URLEncoder.encode(cityId, StandardCharsets.UTF_8))
            .replace("{key}", URLEncoder.encode(API_KEY, StandardCharsets.UTF_8));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .timeout(Duration.ofSeconds(timeoutSec))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() > 300) {
            throw new IOException("Wrong http status: " + response.statusCode());
        }

        return objectMapper.readValue(response.body(), WeatherResponse.class);
    }
}
