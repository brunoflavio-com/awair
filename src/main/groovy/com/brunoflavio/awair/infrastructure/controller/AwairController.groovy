package com.brunoflavio.awair.infrastructure.controller

import com.brunoflavio.awair.application.AwairDataFetcherService
import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.usecase.QueryReadingUseCase
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.reactivestreams.Publisher

import java.time.Instant

@CompileStatic
@Controller("/awair")
class AwairController {

    private AwairDataFetcherService awairDataFetcherService
    private QueryReadingUseCase queryReadingUseCase

    AwairController(
            AwairDataFetcherService awairDataFetcherService,
            QueryReadingUseCase queryReadingUseCase
    ) {
        this.awairDataFetcherService = awairDataFetcherService
        this.queryReadingUseCase = queryReadingUseCase
    }

    @Get("/latest")
    @SingleResult
    Publisher<AwairReading> fetchLatestReading() {
        awairDataFetcherService.fetchLatestData()
    }

    @Get("/interval")
    Publisher<List<AwairReading>> fetchByTimestampInterval(String from, String to) {
        Instant fromInstant = Instant.parse(from)
        Instant toInstant = Instant.parse(to)
        queryReadingUseCase.queryReadingsByInterval(fromInstant, toInstant)
    }
}