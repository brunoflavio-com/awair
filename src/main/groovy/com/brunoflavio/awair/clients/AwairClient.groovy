package com.brunoflavio.awair.clients

import com.brunoflavio.awair.domain.AwairReading
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import org.reactivestreams.Publisher

@CompileStatic
@Client(id="awair")
interface AwairClient {

    @Get('/air-data/latest')
    @SingleResult
    Publisher<AwairReading> fetchLatestReading()
}

