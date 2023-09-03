package com.brunoflavio.awair.infrastructure.repository

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.model.testutil.AwairReadingSampleData
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.time.Instant

@MicronautTest
class H2ReadingsRepositorySpec extends Specification {

    @Inject
    ReadingsRepository readingsRepository

    void "test saving and fetching data"() {
        given: "an awair reading"
        AwairReading awairReading = AwairReadingSampleData.createSample(true)

        when:
        AwairReading savedReading = Mono.from(readingsRepository.save(awairReading)).block()

        then:
        savedReading == awairReading

        when:
        AwairReading fetchedReading = Mono.from(readingsRepository.findByTimestamp(savedReading.timestamp)).block()

        then:
        fetchedReading == savedReading
    }

    void "test fetching by interval"() {
        given: "a January reading"
        AwairReading janReading = AwairReadingSampleData.createSample()
        janReading.timestamp = Instant.parse("2023-01-01T00:00:00Z")
        Mono.from(readingsRepository.save(janReading)).block()

        and: "a February reading"
        AwairReading febReading = AwairReadingSampleData.createSample()
        febReading.timestamp = Instant.parse("2023-02-01T00:00:00Z")
        Mono.from(readingsRepository.save(febReading)).block()

        and: "a March reading"
        AwairReading marReading = AwairReadingSampleData.createSample()
        marReading.timestamp = Instant.parse("2023-03-01T00:00:00Z")
        Mono.from(readingsRepository.save(marReading)).block()

        when: "querying for Jan-Feb readings"
        List<AwairReading> jan_feb_readings = Flux.from(readingsRepository.findByTimestampInterval(
                Instant.parse("2023-01-01T00:00:00Z"),
                Instant.parse("2023-02-28T23:59:59.999999Z")
        )).blockLast()

        then: "jan and feb readings should have been retrieved"
        jan_feb_readings.contains(janReading)
        jan_feb_readings.contains(febReading)

        and: "not the mar reading"
        !jan_feb_readings.contains(marReading)
    }


}
