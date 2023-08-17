package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import reactor.test.StepVerifier
import spock.lang.Specification

class DefaultStoreReadingUseCaseSpec extends Specification {

    def "persist should return the provided reading"() {
        given: "a reading and the DefaultStoreReadingUseCase"
        def reading = new AwairReading()
        def useCase = new DefaultStoreReadingUseCase()

        when: "calling the persist method"
        def result = useCase.persist(reading)

        then: "the returned result should match the provided reading"
        StepVerifier.create(result)
                .expectNext(reading)
                .expectComplete()
                .verify()
    }
}
