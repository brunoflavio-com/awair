package com.brunoflavio.awair.infrastructure.controller

import com.brunoflavio.awair.application.AwairDataFetcherService
import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.model.testutil.AwairReadingSampleData
import com.brunoflavio.awair.domain.usecase.QueryReadingUseCase
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.serde.ObjectMapper
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

import java.time.Instant

@MicronautTest
class AwairControllerSpec extends Specification {

    @Inject
    EmbeddedServer server

    @Inject
    @Client("/awair")
    HttpClient httpClient

    @Inject
    ObjectMapper objectMapper

    @MockBean(AwairDataFetcherService)
    AwairDataFetcherService awairDataFetcherService = Mock(AwairDataFetcherService)

    @MockBean(QueryReadingUseCase)
    QueryReadingUseCase queryReadingUseCase = Mock(QueryReadingUseCase)

    def "FetchLatestReading should return 200 OK"() {
        given: "a predefined reading to be returned by the mock service"
        def expectedReading = new AwairReading()
        awairDataFetcherService.fetchLatestData() >> Mono.just(expectedReading)


        when: "making a GET request to the /latest endpoint"
        HttpRequest request = HttpRequest.GET('/latest')
        def response = httpClient.toBlocking().exchange(request, AwairReading)

        then: "the response status code should be 200 OK"
        response.status == HttpStatus.OK
    }

    def "FetchLatestReading should contact the retrieval service"() {
        given: "a predefined reading to be returned by the mock service"
        def expectedReading = new AwairReading()
        awairDataFetcherService.fetchLatestData() >> Mono.just(expectedReading)

        and: "a AwairController"
        def controller = new AwairController(awairDataFetcherService, queryReadingUseCase)

        when: "fetchLatestReading is called"
        def resultAsync = controller.fetchLatestReading()

        then: "the service should have been called"
        StepVerifier.create(resultAsync)
                .expectNextCount(1)
                .verifyComplete()
    }

    def "test fetchByTimestampInterval endpoint Jan to Dec"() {
        given: "a list of readings"
        List<AwairReading> readings = [AwairReadingSampleData.createSample(), AwairReadingSampleData.createSample()]

        and: "two dates"
        Instant from = Instant.parse("2023-01-01T00:00:00Z")
        Instant to = Instant.parse("2023-12-31T23:59:59Z")

        and: "a mock use case"
        queryReadingUseCase.queryReadingsByInterval(from, to) >> Flux.fromIterable(readings)

        when: "GET request is made to /awair/interval"
        def response = httpClient.toBlocking().exchange(
                "/interval?from=${from}&to=${to}",
                String
        )

        then: "response status is OK"
        response.status == HttpStatus.OK

        and: "response can be parsed to List<AwairReading>"
        List<AwairReading> returnedReadings = objectMapper.readValue(response.body.get(), AwairReading[])

        and: "response body matches the expected readings"
        returnedReadings == readings
    }
}
