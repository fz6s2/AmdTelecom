package org.amd.task04.service.impl;

import org.amd.task04.config.ApplicationConfig;
import org.amd.task04.service.NotificationService;
import org.amd.task04.service.TemperatureService;
import org.amd.task04.service.WeatherCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Checking current weather of the city and sending notifications
 */
public class WeatherCheckServiceImpl implements WeatherCheckService {
    private static final String TEMP_MORE_SAMPLE = "Your name and Temperature more than %dC. %dC";
    private static final String TEMP_LESS_SAMPLE = "Your name and Temperature less than %dC. %dC";
    private static final BigDecimal TEMPERATURE_LIMIT = BigDecimal.valueOf(
        ApplicationConfig.getIntProperty("service.weather.temperature-limit", 20));
    private static final String SMS_NUMBER = ApplicationConfig.getStrProperty("service.weather.sms-number");
    private static final String SMS_SENDER = ApplicationConfig.getStrProperty("service.weather.sms-from", "AMD Telecom");

    private static final Logger logger = LoggerFactory.getLogger(WeatherCheckServiceImpl.class);

    private final TemperatureService temperatureService;
    private final NotificationService notificationService;

    public WeatherCheckServiceImpl(TemperatureService temperatureService, NotificationService notificationService) {
        this.notificationService = notificationService;
        this.temperatureService = temperatureService;
    }

    /**
     * Checks weather of the city and sends notification about it
     * @param cityId City id
     */
    @Override
    public void checkWeather(String cityId) {
        try {
            BigDecimal currentTemperature = temperatureService.getCurrentTemperature(cityId);
            String message = getMessage(currentTemperature);
            notificationService.send(SMS_SENDER, SMS_NUMBER, message);
        } catch (Exception e) {
            logger.error("Error checking weather", e);
        }
    }

    private String getMessage(BigDecimal currentTemperature) {
        if (TEMPERATURE_LIMIT.compareTo(currentTemperature) > 0) {
            return String.format(TEMP_LESS_SAMPLE, TEMPERATURE_LIMIT.intValue(), currentTemperature.intValue());
        }
        return String.format(TEMP_MORE_SAMPLE, TEMPERATURE_LIMIT.intValue(), currentTemperature.intValue());
    }
}
