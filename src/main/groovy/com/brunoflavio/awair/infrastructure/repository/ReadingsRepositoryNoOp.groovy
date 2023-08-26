package com.brunoflavio.awair.infrastructure.repository

import com.brunoflavio.awair.domain.model.AwairReading
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.time.Instant

@Singleton
class ReadingsRepositoryNoOp implements ReadingsRepository {
    @Override
    Publisher<AwairReading> save(AwairReading awairReading) {
        Mono.just(awairReading)
    }

    @Override
    Publisher<List<AwairReading>> fetchByInterval(Instant from, Instant to) {
        Flux.just([])
    }
}
