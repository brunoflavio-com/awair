package com.brunoflavio.awair.infrastructure.repository.mapper

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.entity.ReadingEntity
import groovy.transform.CompileStatic

@CompileStatic
class ReadingEntityToAwairReadingMapper {

    static AwairReading mapToAwairReading(ReadingEntity readingEntity) {
        new AwairReading(
                timestamp: readingEntity.timestamp,
                score: readingEntity.score,
                dew_point: readingEntity.dew_point,
                temp: readingEntity.temp,
                humid: readingEntity.humid,
                abs_humid: readingEntity.abs_humid,
                co2: readingEntity.co2,
                co2_est: readingEntity.co2_est,
                co2_est_baseline: readingEntity.co2_est_baseline,
                voc: readingEntity.voc,
                voc_baseline: readingEntity.voc_baseline,
                voc_h2_raw: readingEntity.voc_h2_raw,
                voc_ethanol_raw: readingEntity.voc_ethanol_raw,
                pm25: readingEntity.pm25,
                pm10_est: readingEntity.pm10_est
        )
    }
}
