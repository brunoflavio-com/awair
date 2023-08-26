package com.brunoflavio.awair.application

import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import org.reactivestreams.Publisher

@CompileStatic
interface AwairDataFetcherService {

    @SingleResult
    Publisher<AwairReading> fetchLatestData()

}