package com.brunoflavio.awair.infrastructure.repository.entity

import com.brunoflavio.awair.domain.model.AwairReading
import spock.lang.Specification

import java.time.Instant

class ReadingEntitySpec extends Specification {

    def "create a ReadingEntity instance"() {
        given: "a sample AwairReading"
        def sampleReading = new AwairReading(
                timestamp: Instant.now(),
                score: 77,
                dew_point: 18.89,
                temp: 24.76,
                humid: 69.9,
                abs_humid: 15.84,
                co2: 673,
                co2_est: 796,
                co2_est_baseline: 36921,
                voc: 513,
                voc_baseline: 38385,
                voc_h2_raw: 26,
                voc_ethanol_raw: 36,
                pm25: 13,
                pm10_est: 14
        )

        when: "an instance is created"
        ReadingEntity entity = new ReadingEntity(
                timestamp: sampleReading.timestamp,
                score: sampleReading.score,
                dew_point: sampleReading.dew_point,
                temp: sampleReading.temp,
                humid: sampleReading.humid,
                abs_humid: sampleReading.abs_humid,
                co2: sampleReading.co2,
                co2_est: sampleReading.co2_est,
                co2_est_baseline: sampleReading.co2_est_baseline,
                voc: sampleReading.voc,
                voc_baseline: sampleReading.voc_baseline,
                voc_h2_raw: sampleReading.voc_h2_raw,
                voc_ethanol_raw: sampleReading.voc_ethanol_raw,
                pm25: sampleReading.pm25,
                pm10_est: sampleReading.pm10_est
        )


        then: "the entity has the expected data"
        entity.timestamp == sampleReading.timestamp
        entity.score == sampleReading.score
        entity.dew_point == sampleReading.dew_point
        entity.temp == sampleReading.temp
        entity.humid == sampleReading.humid
        entity.abs_humid == sampleReading.abs_humid
        entity.co2 == sampleReading.co2
        entity.co2_est == sampleReading.co2_est
        entity.co2_est_baseline == sampleReading.co2_est_baseline
        entity.voc == sampleReading.voc
        entity.voc_baseline == sampleReading.voc_baseline
        entity.voc_h2_raw == sampleReading.voc_h2_raw
        entity.voc_ethanol_raw == sampleReading.voc_ethanol_raw
        entity.pm25 == sampleReading.pm25
        entity.pm10_est == sampleReading.pm10_est
    }
}
