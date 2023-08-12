package com.brunoflavio.awair.domain.model

import io.micronaut.serde.ObjectMapper
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

import java.time.Instant

@MicronautTest
class AwairReadingSpec extends Specification {

    @Inject
    ObjectMapper objectMapper

    def "test AwairReading deserialization from existing json"() {
        given:
        Instant timestamp = Instant.parse("2023-07-28T18:16:36.387Z")
        Integer score = 77
        BigDecimal dewPoint = new BigDecimal("18.89")
        BigDecimal temp = new BigDecimal("24.76")
        BigDecimal humid = new BigDecimal("69.9")
        BigDecimal absHumid = new BigDecimal("15.84")
        Integer co2 = 673
        Integer co2Est = 796
        Integer co2EstBaseline = 36921
        Integer voc = 513
        Integer vocBaseline = 38385
        Integer vocH2Raw = 26
        Integer vocEthanolRaw = 36
        Integer pm25 = 13
        Integer pm10Est = 14

        String json = """
            {
                "timestamp": "$timestamp",
                "score": $score,
                "dew_point": $dewPoint,
                "temp": $temp,
                "humid": $humid,
                "abs_humid": $absHumid,
                "co2": $co2,
                "co2_est": $co2Est,
                "co2_est_baseline": $co2EstBaseline,
                "voc": $voc,
                "voc_baseline": $vocBaseline,
                "voc_h2_raw": $vocH2Raw,
                "voc_ethanol_raw": $vocEthanolRaw,
                "pm25": $pm25,
                "pm10_est": $pm10Est
            }
            """ as String

        when:
        AwairReading deserialized = objectMapper.readValue(json, AwairReading)

        then:
        deserialized.timestamp == timestamp
        deserialized.score == score
        deserialized.dew_point == dewPoint
        deserialized.temp == temp
        deserialized.humid == humid
        deserialized.abs_humid == absHumid
        deserialized.co2 == co2
        deserialized.co2_est == co2Est
        deserialized.co2_est_baseline == co2EstBaseline
        deserialized.voc == voc
        deserialized.voc_baseline == vocBaseline
        deserialized.voc_h2_raw == vocH2Raw
        deserialized.voc_ethanol_raw == vocEthanolRaw
        deserialized.pm25 == pm25
        deserialized.pm10_est == pm10Est
    }

    def "test AwairReading serialization and deserialization"() {
        given:
        Instant timestamp = Instant.parse("2023-07-28T18:16:36.387Z")
        Integer score = 77
        BigDecimal dewPoint = new BigDecimal("18.89")
        BigDecimal temp = new BigDecimal("24.76")
        BigDecimal humid = new BigDecimal("69.9")
        BigDecimal absHumid = new BigDecimal("15.84")
        Integer co2 = 673
        Integer co2Est = 796
        Integer co2EstBaseline = 36921
        Integer voc = 513
        Integer vocBaseline = 38385
        Integer vocH2Raw = 26
        Integer vocEthanolRaw = 36
        Integer pm25 = 13
        Integer pm10Est = 14

        def original = new AwairReading(
                timestamp: timestamp,
                score: score,
                dew_point: dewPoint,
                temp: temp,
                humid: humid,
                abs_humid: absHumid,
                co2: co2,
                co2_est: co2Est,
                co2_est_baseline: co2EstBaseline,
                voc: voc,
                voc_baseline: vocBaseline,
                voc_h2_raw: vocH2Raw,
                voc_ethanol_raw: vocEthanolRaw,
                pm25: pm25,
                pm10_est: pm10Est
        )

        when:
        def json = objectMapper.writeValueAsString(original)
        def deserialized = objectMapper.readValue(json, AwairReading)

        then:
        deserialized instanceof AwairReading
        deserialized == original
    }
}
