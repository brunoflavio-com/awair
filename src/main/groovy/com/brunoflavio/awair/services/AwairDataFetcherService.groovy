package com.brunoflavio.awair.services

import com.brunoflavio.awair.clients.AwairClient
import com.brunoflavio.awair.domain.AwairReading
import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import org.reactivestreams.Publisher

@Singleton
@CompileStatic
class AwairDataFetcherService {
    private AwairClient client

    AwairDataFetcherService(AwairClient client ) {
        this.client = client
    }

    Publisher<AwairReading> fetchData() {
        client.fetchLatestReading()
    }
}
