package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.gateway.AwairClient
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification
import spock.lang.Subject

class DefaultFetchLatestDataFromAwairUseCaseSpec extends Specification {

    @Subject
    DefaultFetchLatestDataFromAwairUseCase fetchLatestDataUseCase

    def mockAwairClient = Mock(AwairClient)

    def setup() {
        fetchLatestDataUseCase = new DefaultFetchLatestDataFromAwairUseCase(mockAwairClient)
    }

    def "Fetching latest data from Awair should return a valid reading"() {
        given: "a predefined reading to be returned by the mock AwairClient"
        def expectedReading = new AwairReading()

        and: "mocking the AwairClient to return the predefined reading"
        mockAwairClient.fetchLatestReading() >> Mono.just(expectedReading)

        when: "fetching the latest data from Awair"
        def resultMono = fetchLatestDataUseCase.fetchData()

        then: "the result should be a valid AwairReading"
        StepVerifier.create(resultMono)
                .expectNext(expectedReading)
                .verifyComplete()
    }
}
