package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.ReadingsRepository
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

class DefaultStoreReadingUseCaseSpec extends Specification {


    def "persist should return the provided reading"() {
        given: "a reading"
        AwairReading reading = new AwairReading()

        and: "a mock repository"
        ReadingsRepository readingsRepositoryMock = Mock(ReadingsRepository)

        and: "a DefaultStoreReadingUseCase is created"
        StoreReadingUseCase useCase = new DefaultStoreReadingUseCase(readingsRepositoryMock)

        when: "calling the persist method"
        def result = useCase.persist(reading)

        then: "the returned result should match the provided reading"
        StepVerifier.create(result)
                .expectNext(reading)
                .expectComplete()
                .verify()

        and: "the mock repository was called once"
        1 * readingsRepositoryMock.save(reading) >> Mono.just(reading)
    }
}
