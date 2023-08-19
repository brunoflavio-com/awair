package com.brunoflavio.awair.domain.usecase


import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.ReadingsRepository
import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import org.reactivestreams.Publisher

@Singleton
@CompileStatic
class DefaultStoreReadingUseCase implements StoreReadingUseCase {

    private final ReadingsRepository readingsRepository

    DefaultStoreReadingUseCase(ReadingsRepository readingsRepository) {
        this.readingsRepository = readingsRepository
    }

    @Override
    Publisher<AwairReading> persist(AwairReading reading) {
        readingsRepository.save(reading)
    }
}
