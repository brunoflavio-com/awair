package com.brunoflavio.awair.infrastructure.scheduler


import com.brunoflavio.awair.domain.usecase.FetchLatestDataFromAwairUseCase
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class FetchDataFromAwairJobSpec extends Specification {

    @MockBean(FetchLatestDataFromAwairUseCase)
    FetchLatestDataFromAwairUseCase mockService = Mock(FetchLatestDataFromAwairUseCase)

    @Inject
    FetchDataFromAwairJob job

    def "process should call fetchData on the service"() {
        when: "the process method is called"
        job.process()

        then: "fetchData method on the service should be called"
        1 * mockService.fetchData()
    }
}
