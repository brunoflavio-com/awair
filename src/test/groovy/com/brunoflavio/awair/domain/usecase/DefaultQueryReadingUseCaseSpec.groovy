package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.ReadingsRepository
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import spock.lang.Specification

import java.time.Instant

class DefaultQueryReadingUseCaseSpec extends Specification {

    def "query by interval should return the provided readings"() {
        given: "a list of readings"
        List<AwairReading> readings = [new AwairReading(), new AwairReading()]

        and: "a mock repository"
        ReadingsRepository readingsRepositoryMock = Mock(ReadingsRepository)

        and: "a DefaultQueryReadingUseCase is created"
        QueryReadingUseCase useCase = new DefaultQueryReadingUseCase(readingsRepositoryMock)

        when: "calling the queryReadingsByInterval method"
        def result = useCase.queryReadingsByInterval(Instant.MIN, Instant.MAX)

        then: "the returned result should match the provided reading"
        StepVerifier.create(result)
                .expectNextSequence(readings as Iterable<? extends List<AwairReading>>)
                .expectComplete()
                .verify()

        and: "the mock repository was called once"
        1 * readingsRepositoryMock.findByTimestampInterval(Instant.MIN, Instant.MAX) >> Flux.fromIterable(readings)
    }
}
