package com.brunoflavio.awair.infrastructure.controller

import com.brunoflavio.awair.application.AwairDataFetcherService
import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.reactivestreams.Publisher

@Controller("/awair")
@CompileStatic
class AwairController {

    private AwairDataFetcherService service

    AwairController(AwairDataFetcherService service) {
        this.service = service
    }

    @Get("/latest")
    @SingleResult
    Publisher<AwairReading> fetchLatestReading() {
        service.fetchLatestData()
    }
}