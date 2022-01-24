package org.amd.task04;

import org.amd.task04.client.routee.RouteeClient;
import org.amd.task04.client.weather.OpenWeatherMapClient;
import org.amd.task04.schedule.ScheduledTask;
import org.amd.task04.service.NotificationService;
import org.amd.task04.service.TemperatureService;
import org.amd.task04.service.WeatherCheckService;
import org.amd.task04.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Task04 {

    private static final Logger logger = LoggerFactory.getLogger(Task04.class);

    public static void main(String[] args) {
        final TemperatureService temperatureService = new TemperatureServiceImpl(new OpenWeatherMapClient());
        final NotificationService notificationService = new SmsNotificationService(new RouteeClient());
        final WeatherCheckService weatherCheckService = new WeatherCheckServiceImpl(temperatureService, notificationService);

        // id for city = "Thessaloniki": if you use weather API by city name, you will find a list of 3 cities and must choose some
        String cityId = "734077";

        final ScheduledTask task = new ScheduledTask(() -> weatherCheckService.checkWeather(cityId), 10, 10, TimeUnit.MINUTES);
        try {
            task.launch();
        } catch (Exception ex) {
            logger.error("Scheduled task execution error", ex);
        }
    }
}
