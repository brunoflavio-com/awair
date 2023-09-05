package com.brunoflavio.awair.infrastructure.gateway


import com.brunoflavio.awair.domain.model.AwairReading
import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Requires
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.reactivestreams.Publisher
import reactor.test.StepVerifier
import spock.lang.Specification

import java.time.Instant

@MicronautTest
class AwairClientSpec extends Specification {

    void "tests the dynamic client against a mock device"() {
        given:
        String url = createMockDeviceServerAndReturnUrl()
        EmbeddedServer embeddedServer = instantiateApplicationWithAwairUrlPointingToUrl(url)

        when:
        AwairClient awairClient = embeddedServer.getApplicationContext()
                .createBean(AwairClient, embeddedServer.getURL())

        Publisher<AwairReading> data = awairClient.fetchLatestReading()

        then:
        StepVerifier.create(data)
                .expectNextMatches { reading ->
                    assert reading.timestamp == Instant.parse(MockAwairDeviceLatestData.MOCK_TIMESTAMP)
                    assert reading.score == MockAwairDeviceLatestData.MOCK_SCORE
                    assert reading.dew_point == MockAwairDeviceLatestData.MOCK_DEW_POINT
                    assert reading.temp == MockAwairDeviceLatestData.MOCK_TEMP
                    assert reading.humid == MockAwairDeviceLatestData.MOCK_HUMID
                    assert reading.abs_humid == MockAwairDeviceLatestData.MOCK_ABS_HUMID
                    assert reading.co2 == MockAwairDeviceLatestData.MOCK_CO2
                    assert reading.co2_est == MockAwairDeviceLatestData.MOCK_CO2_EST
                    assert reading.co2_est_baseline == MockAwairDeviceLatestData.MOCK_CO2_EST_BASELINE
                    assert reading.voc == MockAwairDeviceLatestData.MOCK_VOC
                    assert reading.voc_baseline == MockAwairDeviceLatestData.MOCK_VOC_BASELINE
                    assert reading.voc_h2_raw == MockAwairDeviceLatestData.MOCK_VOC_H2_RAW
                    assert reading.voc_ethanol_raw == MockAwairDeviceLatestData.MOCK_VOC_ETHANOL_RAW
                    assert reading.pm25 == MockAwairDeviceLatestData.MOCK_PM25
                    assert reading.pm10_est == MockAwairDeviceLatestData.MOCK_PM10_EST
                    true
                }
                .expectComplete()
                .verify()
    }

    private EmbeddedServer instantiateApplicationWithAwairUrlPointingToUrl(String url) {
        ApplicationContext.run(EmbeddedServer,
                Collections.singletonMap("micronaut.http.services.awair.url", url))
    }

    private String createMockDeviceServerAndReturnUrl() {
        EmbeddedServer mockAwairServer = ApplicationContext.run(EmbeddedServer,
                Map.of("spec.name", "AwairClientSpec"))

        "http://localhost:$mockAwairServer.port"
    }


    @Requires(property = "spec.name", value = "AwairClientSpec")
    @Controller('/air-data')
    static class MockAwairDeviceLatestData {

        static String MOCK_TIMESTAMP = "2023-07-31T22:03:54.602Z"
        static int MOCK_SCORE = 67
        static BigDecimal MOCK_DEW_POINT = 19.88
        static BigDecimal MOCK_TEMP = 24.6
        static BigDecimal MOCK_HUMID = 75.13
        static BigDecimal MOCK_ABS_HUMID = 16.85
        static int MOCK_CO2 = 1447
        static int MOCK_CO2_EST = 1563
        static int MOCK_CO2_EST_BASELINE = 37230
        static int MOCK_VOC = 605
        static int MOCK_VOC_BASELINE = 38477
        static int MOCK_VOC_H2_RAW = 25
        static int MOCK_VOC_ETHANOL_RAW = 36
        static int MOCK_PM25 = 8
        static int MOCK_PM10_EST = 9


        @SuppressWarnings('GrMethodMayBeStatic')
        @Produces("application/json")
        @Get("latest")
        Optional<String> latest() {
            return Optional.of("""
            {
                "timestamp": "$MOCK_TIMESTAMP",
                "score": $MOCK_SCORE,
                "dew_point": $MOCK_DEW_POINT,
                "temp": $MOCK_TEMP,
                "humid": $MOCK_HUMID,
                "abs_humid": $MOCK_ABS_HUMID,
                "co2": $MOCK_CO2,
                "co2_est": $MOCK_CO2_EST,
                "co2_est_baseline": $MOCK_CO2_EST_BASELINE,
                "voc": $MOCK_VOC,
                "voc_baseline": $MOCK_VOC_BASELINE,
                "voc_h2_raw": $MOCK_VOC_H2_RAW,
                "voc_ethanol_raw": $MOCK_VOC_ETHANOL_RAW,
                "pm25": $MOCK_PM25,
                "pm10_est": $MOCK_PM10_EST
            }
            """ as String)
        }
    }
}
