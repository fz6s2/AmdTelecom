package org.amd.task04.client.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Open Weather Map API response model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private WeatherMain main;

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }
}
