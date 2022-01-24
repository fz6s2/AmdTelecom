package org.amd.task04.service.impl;

import org.amd.task04.exception.BusinessException;
import org.amd.task04.exception.ApiClientException;
import org.amd.task04.client.weather.OpenWeatherMapClient;
import org.amd.task04.client.weather.model.WeatherResponse;
import org.amd.task04.service.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Fetching current temperature using Open Weather Map API
 */
public class TemperatureServiceImpl implements TemperatureService {
    private static final Logger logger = LoggerFactory.getLogger(TemperatureServiceImpl.class);

    private final OpenWeatherMapClient weatherClient;

    public TemperatureServiceImpl(OpenWeatherMapClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @Override
    public BigDecimal getCurrentTemperature(String cityId) throws BusinessException {
        try {
            return getWeather(cityId);
        } catch (ApiClientException ex) {
            throw new BusinessException(
                String.format("Error fetching current temperature for city %s", cityId),
                ex);
        }
    }

    private BigDecimal getWeather(String cityId) throws ApiClientException {
        logger.debug("Sending weather request, city id {}", cityId);

        WeatherResponse weatherResponse = weatherClient.getWeather(cityId);
        if (weatherResponse == null) {
            throw new ApiClientException("Open Weather Map Client response is null");
        }

        BigDecimal temperature = weatherResponse.getMain().getTemp();

        logger.debug("Weather request was sent successfully, temerature: {}", temperature);

        return temperature;
    }
}
