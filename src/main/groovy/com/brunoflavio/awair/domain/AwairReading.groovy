package com.brunoflavio.awair.domain

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

import java.time.Instant

/*
{
  "timestamp": "2023-07-28T18:16:36.387Z",
  "score": 77,
  "dew_point": 18.89,
  "temp": 24.76,
  "humid": 69.9,
  "abs_humid": 15.84,
  "co2": 673,
  "co2_est": 796,
  "co2_est_baseline": 36921,
  "voc": 513,
  "voc_baseline": 38385,
  "voc_h2_raw": 26,
  "voc_ethanol_raw": 36,
  "pm25": 13,
  "pm10_est": 14
}
 */

@Serdeable
@CompileStatic
class AwairReading {
    Instant timestamp
    Integer score
    BigDecimal dew_point
    BigDecimal temp
    BigDecimal humid
    BigDecimal abs_humid
    Integer co2
    Integer co2_est
    Integer co2_est_baseline
    Integer voc
    Integer voc_baseline
    Integer voc_h2_raw
    Integer voc_ethanol_raw
    Integer pm25
    Integer pm10_est
}