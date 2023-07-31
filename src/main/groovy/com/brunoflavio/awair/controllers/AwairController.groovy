package com.brunoflavio.awair.controllers

import com.brunoflavio.awair.clients.AwairClient
import com.brunoflavio.awair.domain.AwairReading
import com.brunoflavio.awair.services.AwairDataFetcherService
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
        service.fetchData()
    }
}