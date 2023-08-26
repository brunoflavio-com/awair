package com.brunoflavio.awair.infrastructure.repository.mapper

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.entity.ReadingEntity
import groovy.transform.CompileStatic

@CompileStatic
class AwairReadingToEntityReadingMapper {
    static ReadingEntity mapToReadingEntity(AwairReading awairReading) {
        new ReadingEntity(
                timestamp: awairReading.timestamp,
                score: awairReading.score,
                dew_point: awairReading.dew_point,
                temp: awairReading.temp,
                humid: awairReading.humid,
                abs_humid: awairReading.abs_humid,
                co2: awairReading.co2,
                co2_est: awairReading.co2_est,
                co2_est_baseline: awairReading.co2_est_baseline,
                voc: awairReading.voc,
                voc_baseline: awairReading.voc_baseline,
                voc_h2_raw: awairReading.voc_h2_raw,
                voc_ethanol_raw: awairReading.voc_ethanol_raw,
                pm25: awairReading.pm25,
                pm10_est: awairReading.pm10_est
        )
    }
}