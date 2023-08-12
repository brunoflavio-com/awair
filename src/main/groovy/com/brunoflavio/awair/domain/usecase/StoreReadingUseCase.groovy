package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import io.micronaut.core.async.annotation.SingleResult
import org.reactivestreams.Publisher

interface StoreReadingUseCase {
    @SingleResult
    Publisher<AwairReading> persist(AwairReading reading)
}
