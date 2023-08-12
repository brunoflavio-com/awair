package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import org.reactivestreams.Publisher

interface AwairDataFetcherUseCase {
    Publisher<AwairReading> fetchData()
}