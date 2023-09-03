package com.brunoflavio.awair.infrastructure.repository.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

import java.time.Instant

@Serdeable
@Entity
@Table(name = "readings")
class ReadingEntity {
    @Id
    @NotNull
    Instant timestamp
    @Column
    Integer score
    @Column
    BigDecimal dew_point
    @Column
    BigDecimal temp
    @Column
    BigDecimal humid
    @Column
    BigDecimal abs_humid
    @Column
    Integer co2
    @Column
    Integer co2_est
    @Column
    Integer co2_est_baseline
    @Column
    Integer voc
    @Column
    Integer voc_baseline
    @Column
    Integer voc_h2_raw
    @Column
    Integer voc_ethanol_raw
    @Column
    Integer pm25
    @Column
    Integer pm10_est
}