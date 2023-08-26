package com.brunoflavio.awair.domain.usecase

import com.brunoflavio.awair.domain.model.AwairReading
import groovy.transform.CompileStatic
import org.reactivestreams.Publisher


@CompileStatic
interface FetchLatestDataFromAwairUseCase {
    Publisher<AwairReading> fetchData()
}