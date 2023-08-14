package com.brunoflavio.awair.application

import com.brunoflavio.awair.domain.model.AwairReading
import io.micronaut.core.async.annotation.SingleResult
import org.reactivestreams.Publisher

interface AwairDataFetcherService {

    @SingleResult
    Publisher<AwairReading> fetchData()

}