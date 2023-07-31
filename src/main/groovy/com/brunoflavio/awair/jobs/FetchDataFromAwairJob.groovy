package com.brunoflavio.awair.jobs

import com.brunoflavio.awair.services.AwairDataFetcherService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import jakarta.inject.Singleton
import io.micronaut.scheduling.annotation.Scheduled

@Singleton
@Slf4j
@CompileStatic
class FetchDataFromAwairJob {

    private AwairDataFetcherService service


    FetchDataFromAwairJob(AwairDataFetcherService service) {
        this.service = service
    }
    @Scheduled(fixedRate = "1m")
    void process() {
        log.info('Requesting data from Awair service (periodic job)')
        service.fetchData()
    }
}
