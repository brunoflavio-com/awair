package com.brunoflavio.awair.application

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.usecase.FetchLatestDataFromAwairUseCase
import com.brunoflavio.awair.domain.usecase.StoreReadingUseCase
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

@MicronautTest
class DefaultAwairDataFetcherServiceSpec extends Specification {

    @MockBean(FetchLatestDataFromAwairUseCase)
    FetchLatestDataFromAwairUseCase fetchLatestDataFromAwairUseCase = Mock(FetchLatestDataFromAwairUseCase)

    @MockBean(StoreReadingUseCase)
    StoreReadingUseCase storeReadingUseCase = Mock(StoreReadingUseCase)

    @Inject
    DefaultAwairDataFetcherService dataFetcherService

    def "fetchLatestData should fetch and store data"() {
        given: "a mock reading"
        def mockReading = new AwairReading()

        and: "fetchLatestDataFromAwairUseCase returns the mock reading and is called once"
        1 * fetchLatestDataFromAwairUseCase.fetchData() >> Mono.just(mockReading)

        and: "storeReadingUseCase returns the mock reading and is called once"
        1 * storeReadingUseCase.persist(_) >> Mono.just(mockReading)

        when: "fetchLatestData is called"
        def resultAsync = dataFetcherService.fetchLatestData()

        then: "the returned result should match the mock reading"
        StepVerifier.create(resultAsync)
                .expectNext(mockReading)
                .verifyComplete()
    }
}
