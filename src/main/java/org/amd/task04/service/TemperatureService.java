package org.amd.task04.service;

import org.amd.task04.exception.BusinessException;

import java.math.BigDecimal;

/**
 * Temperature fetching service interface
 */
public interface TemperatureService {
    BigDecimal getCurrentTemperature(String cityId) throws BusinessException;
}
