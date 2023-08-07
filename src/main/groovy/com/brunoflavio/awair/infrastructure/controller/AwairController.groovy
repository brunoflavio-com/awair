package com.brunoflavio.awair.infrastructure.controller


import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.usecase.AwairDataFetcherUseCase
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Controller("/awair")
@CompileStatic
class AwairController {

    private AwairDataFetcherUseCase service

    AwairController(AwairDataFetcherUseCase service) {
        this.service = service
    }

    @Get("/latest")
    @SingleResult
    Publisher<AwairReading> fetchLatestReading() {
        service.fetchData()
    }
}