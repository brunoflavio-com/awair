package com.brunoflavio.awair.application

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.domain.usecase.FetchLatestDataFromAwairUseCase
import com.brunoflavio.awair.domain.usecase.StoreReadingUseCase
import groovy.transform.CompileStatic
import io.micronaut.core.async.annotation.SingleResult
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@CompileStatic
@Singleton
class DefaultAwairDataFetcherService implements AwairDataFetcherService {

    private final StoreReadingUseCase storeReadingUseCase
    private final FetchLatestDataFromAwairUseCase fetchLatestDataFromAwairUseCase

    DefaultAwairDataFetcherService(
            StoreReadingUseCase storeReadingUseCase,
            FetchLatestDataFromAwairUseCase fetchLatestDataFromAwairUseCase
    ) {
        this.storeReadingUseCase = storeReadingUseCase
        this.fetchLatestDataFromAwairUseCase = fetchLatestDataFromAwairUseCase
    }

    @SingleResult
    Publisher<AwairReading> fetchLatestData() {
        def latestReadingAsync = fetchLatestDataFromAwairUseCase.fetchData()
        def storeReadingAsync = { AwairReading reading -> storeReadingUseCase.persist(reading) }

        Mono.from(latestReadingAsync).flatMap { storeReadingAsync(it) } as Publisher<AwairReading>
    }
}
