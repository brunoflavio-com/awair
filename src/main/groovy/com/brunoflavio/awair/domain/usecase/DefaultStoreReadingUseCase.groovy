package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
@CompileStatic
class DefaultStoreReadingUseCase implements StoreReadingUseCase {
    @Override
    Publisher<AwairReading> persist(AwairReading reading) {
        Mono.just(reading)
    }
}
