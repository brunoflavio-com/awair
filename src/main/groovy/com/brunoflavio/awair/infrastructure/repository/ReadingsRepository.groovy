package com.brunoflavio.awair.infrastructure.repository

import com.brunoflavio.awair.domain.model.AwairReading
import io.micronaut.core.async.annotation.SingleResult
import org.reactivestreams.Publisher

import java.time.Instant

interface ReadingsRepository {

    @SingleResult
    Publisher<AwairReading> save(AwairReading awairReading)

    Publisher<List<AwairReading>> fetchByInterval(Instant from, Instant to)
}