package com.brunoflavio.awair.domain.usecase


import com.brunoflavio.awair.domain.model.AwairReading
import com.brunoflavio.awair.infrastructure.gateway.AwairClient
import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import org.reactivestreams.Publisher

@Singleton
@CompileStatic
class DefaultFetchLatestDataFromAwairUseCase implements FetchLatestDataFromAwairUseCase {
    private AwairClient client

    DefaultFetchLatestDataFromAwairUseCase(AwairClient client) {
        this.client = client
    }

    Publisher<AwairReading> fetchData() {
        client.fetchLatestReading()
    }
}
