package com.brunoflavio.awair.infrastructure.repository


import com.brunoflavio.awair.infrastructure.repository.entity.ReadingEntity
import groovy.transform.CompileStatic
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.reactive.ReactiveStreamsPageableRepository

import java.time.Instant

@CompileStatic
@JdbcRepository(dialect = Dialect.H2)
interface H2ReadingJdbcRepository extends ReactiveStreamsPageableRepository<ReadingEntity, Instant> {

}