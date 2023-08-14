package com.brunoflavio.awair.application

import com.brunoflavio.awair.domain.model.AwairReading
import io.micronaut.core.async.annotation.SingleResult
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

@Singleton
class DefaultAwairDataFetcherService {
    @SingleResult
    Publisher<AwairReading> fetchData() {
        Mono.from(client.fetchLatestReading()).flatMap { repository.save(it) } as Publisher<AwairReading>
    }
}
