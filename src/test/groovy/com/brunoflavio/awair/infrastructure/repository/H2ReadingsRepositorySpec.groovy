package com.brunoflavio.awair.infrastructure.repository

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.model.testutil.AwairReadingSampleData
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import reactor.core.publisher.Mono
import spock.lang.Specification

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


}
