package com.brunoflavio.awair.infrastructure.controller

import com.brunoflavio.awair.application.AwairDataFetcherService
import com.brunoflavio.awair.domain.model.AwairReading
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

@MicronautTest
class AwairControllerSpec extends Specification {

    @Inject
    EmbeddedServer server

    @Inject
    @Client("/")
    HttpClient httpClient

    @MockBean(AwairDataFetcherService)
    AwairDataFetcherService awairDataFetcherService = Mock(AwairDataFetcherService)

    def "FetchLatestReading should return 200 OK"() {
        given: "a predefined reading to be returned by the mock service"
        def expectedReading = new AwairReading()
        awairDataFetcherService.fetchLatestData() >> Mono.just(expectedReading)


        when: "making a GET request to the /latest endpoint"
        HttpRequest request = HttpRequest.GET('/awair/latest')
        def response = httpClient.toBlocking().exchange(request, AwairReading)

        then: "the response status code should be 200 OK"
        response.status == HttpStatus.OK
    }

    def "FetchLatestReading should contact the retrieval service"() {
        given: "a predefined reading to be returned by the mock service"
        def expectedReading = new AwairReading()
        awairDataFetcherService.fetchLatestData() >> Mono.just(expectedReading)

        and: "a AwairController"
        def controller = new AwairController(awairDataFetcherService)

        when: "fetchLatestReading is called"
        def resultAsync = controller.fetchLatestReading()

        then: "the service should have been called"
        StepVerifier.create(resultAsync)
                .expectNextCount(1)
                .verifyComplete()
    }
}
