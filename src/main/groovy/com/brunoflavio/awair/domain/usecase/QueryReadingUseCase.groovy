package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import org.reactivestreams.Publisher

import java.time.Instant


@CompileStatic
interface QueryReadingUseCase {
    @SingleResult
    Publisher<List<AwairReading>> queryReadingsByInterval(Instant from, Instant to)
}
