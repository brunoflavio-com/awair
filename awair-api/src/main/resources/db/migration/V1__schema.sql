CREATE TABLE readings
(
    timestamp TIMESTAMP(9) NOT NULL PRIMARY KEY,
    score            INT,
    dew_point        DECIMAL(10, 2),
    temp             DECIMAL(10, 2),
    humid            DECIMAL(10, 2),
    abs_humid        DECIMAL(10, 2),
    co2              INT,
    co2_est          INT,
    co2_est_baseline INT,
    voc              INT,
    voc_baseline     INT,
    voc_h2_raw       INT,
    voc_ethanol_raw  INT,
    pm25             INT,
    pm10_est         INT
);