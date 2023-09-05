package com.brunoflavio.awair.infrastructure.repository

import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.repository.entity.ReadingEntity
import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

import java.time.Instant

import static com.brunoflavio.awair.infrastructure.repository.mapper.AwairReadingToEntityReadingMapper.mapToReadingEntity
import static com.brunoflavio.awair.infrastructure.repository.mapper.ReadingEntityToAwairReadingMapper.mapToAwairReading

@CompileStatic
@Singleton
class H2ReadingsRepository implements ReadingsRepository {

    H2ReadingJdbcRepository repository

    H2ReadingsRepository(H2ReadingJdbcRepository h2ReadingJdbcRepository) {
        this.repository = h2ReadingJdbcRepository
    }

    @Override
    Publisher<AwairReading> save(AwairReading awairReading) {

        ReadingEntity readingEntity = mapToReadingEntity(awairReading)
        Publisher<ReadingEntity> persistedEntityAsync = repository.save(readingEntity)

        Mono.from(persistedEntityAsync)
                .flatMap {
                    Mono.just(mapToAwairReading(it))
                } as Publisher<AwairReading>
    }

    @Override
    Publisher<List<AwairReading>> findByTimestampInterval(Instant from, Instant to) {
        Flux.from(repository.findByTimestampInterval(from, to))
                .map { mapToAwairReading(it) }
                .collectList()
    }

    @Override
    Publisher<AwairReading> findByTimestamp(Instant timestamp) {
        Mono.from(repository.findById(timestamp))
                .flatMap {
                    Mono.just(mapToAwairReading(it))
                }
    }
}
