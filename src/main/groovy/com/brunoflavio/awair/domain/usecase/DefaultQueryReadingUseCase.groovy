package com.brunoflavio.awair.domain.usecase


import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.ReadingsRepository
import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import org.reactivestreams.Publisher

import java.time.Instant


@CompileStatic
@Singleton
class DefaultQueryReadingUseCase implements QueryReadingUseCase {

    private final ReadingsRepository readingsRepository

    DefaultQueryReadingUseCase(ReadingsRepository readingsRepository) {
        this.readingsRepository = readingsRepository
    }

    @Override
    Publisher<List<AwairReading>> queryReadingsByInterval(Instant from, Instant to) {
        readingsRepository.findByTimestampInterval(from, to)
    }
}
