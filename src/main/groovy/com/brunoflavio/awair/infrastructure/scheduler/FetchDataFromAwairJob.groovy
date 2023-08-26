package com.brunoflavio.awair.infrastructure.scheduler

import com.brunoflavio.awair.application.AwairDataFetcherService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import reactor.core.publisher.Mono

@CompileStatic
@Slf4j
@Singleton
class FetchDataFromAwairJob {

    private AwairDataFetcherService service


    FetchDataFromAwairJob(AwairDataFetcherService service) {
        this.service = service
    }

    @Scheduled(fixedRate = "1m")
    void process() {
        log.info('Requesting data from Awair service (periodic job)')
        Mono.from(service.fetchLatestData()).block()
    }
}
