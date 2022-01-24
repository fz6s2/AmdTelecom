package org.amd.task04.client.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * "main" node for weather response model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMain {
    private BigDecimal temp;

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }
}
