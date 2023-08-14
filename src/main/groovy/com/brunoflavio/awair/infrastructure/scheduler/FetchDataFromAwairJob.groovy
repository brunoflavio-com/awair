package com.brunoflavio.awair.infrastructure.scheduler

import com.brunoflavio.awair.domain.usecase.DefaultFetchLatestDataFromAwairUseCase
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton

@Singleton
@Slf4j
@CompileStatic
class FetchDataFromAwairJob {

    private DefaultFetchLatestDataFromAwairUseCase service


    FetchDataFromAwairJob(DefaultFetchLatestDataFromAwairUseCase service) {
        this.service = service
    }

    @Scheduled(fixedRate = "1m")
    void process() {
        log.info('Requesting data from Awair service (periodic job)')
        service.fetchData()
    }
}
