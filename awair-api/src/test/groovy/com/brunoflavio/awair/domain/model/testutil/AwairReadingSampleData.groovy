package com.brunoflavio.awair.domain.model.testutil

import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
class AwairReadingSampleData {
    static AwairReading createSample(boolean timestampNow = false) {
        new AwairReading(
                timestamp: timestampNow ? Instant.now() : Instant.parse("2023-08-01T00:00:00Z"),
                score: 90,
                dew_point: 14.59,
                temp: 23.49,
                humid: 57.43,
                abs_humid: 12.11,
                co2: 400,
                co2_est: 400,
                co2_est_baseline: 37341,
                voc: 90,
                voc_baseline: 38874,
                voc_h2_raw: 27,
                voc_ethanol_raw: 38,
                pm25: 4,
                pm10_est: 5
        )
    }
}
