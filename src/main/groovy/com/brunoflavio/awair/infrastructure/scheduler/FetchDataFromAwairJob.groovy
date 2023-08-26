package com.brunoflavio.awair.infrastructure.scheduler


import com.brunoflavio.awair.domain.usecase.FetchLatestDataFromAwairUseCase
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton


@CompileStatic
@Slf4j
@Singleton
class FetchDataFromAwairJob {

    private FetchLatestDataFromAwairUseCase service


    FetchDataFromAwairJob(FetchLatestDataFromAwairUseCase service) {
        this.service = service
    }

    @Scheduled(fixedRate = "1m")
    void process() {
        log.info('Requesting data from Awair service (periodic job)')
        service.fetchData()
    }
}
