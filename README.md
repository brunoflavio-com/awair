# Simple app to gather data from an Awair device local API.

## My setup:

I've got an Awair Element to measure the air quality in the house. Turns out it provides an endpoint that provides the latest data (seems to update every minute).

It provides the Awair air quality score, Temperature, Relative Humidity, Carbon Dioxide (ppm), Total Volatile Organic Compounds (ppb) and Particulate matter less than 2.5 microns in diameter (µg/m³), along with some additional parameters in JSON:

```json
{
  "timestamp": "2023-07-31T11:34:27.741Z",
  "score": 75,
  "dew_point": 17.23,
  "temp": 24.72,
  "humid": 63.11,
  "abs_humid": 14.27,
  "co2": 637,
  "co2_est": 737,
  "co2_est_baseline": 37216,
  "voc": 287,
  "voc_baseline": 38329,
  "voc_h2_raw": 26,
  "voc_ethanol_raw": 37,
  "pm25": 39,
  "pm10_est": 41
}
```

## Configuration:

### Awair hostname:

Set the Awair device hostname in the application.properties file or pass them as environment variables, i.e.
`micronaut.http.services.awair.url=http://awair-elem-123abc.local`


## Useful links:
- [Awair Local API feature documentation](https://support.getawair.com/hc/en-us/articles/360049221014-Awair-Element-Local-API-Feature)
- [Micronaut http client guide](https://guides.micronaut.io/latest/micronaut-http-client-gradle-groovy.html)
- [Schedule periodic tasks inside your Micronaut applications guide](https://guides.micronaut.io/latest/micronaut-scheduled-gradle-groovy.html)


--- 

# Built with micronaut:

## Micronaut 4.0.1 Documentation

- [User Guide](https://docs.micronaut.io/4.0.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.0.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.0.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#nettyHttpClient)


## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)