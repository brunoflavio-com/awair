package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic
import org.reactivestreams.Publisher

@Singleton
@CompileStatic
class DefaultStoreReadingUseCase implements StoreReadingUseCase {
    @Override
    Publisher<AwairReading> persist(AwairReading reading) {
        null
    }
}
