<template>
  <div class="awair-card">
    <h2>Awair Score: {{ score }}</h2>
    <p>Timestamp: {{ timestamp }}</p>
    <p>Humidity: {{ humid }}%</p>
    <p>Temperature: {{ temp }}°C</p>
    <p>CO2: {{ co2 }} ppm</p>
    <p>Dew Point: {{ dew_point }}</p>
    <p>Absolute Humidity: {{ abs_humid }}</p>
    <p>Estimated CO2: {{ co2_est }}</p>
    <p>Baseline Estimated CO2: {{ co2_est_baseline }}</p>
    <p>VOC: {{ voc }}</p>
    <p>Baseline VOC: {{ voc_baseline }}</p>
    <p>Raw H2 VOC: {{ voc_h2_raw }}</p>
    <p>Raw Ethanol VOC: {{ voc_ethanol_raw }}</p>
    <p>PM2.5: {{ pm25 }}</p>
    <p>Estimated PM10: {{ pm10_est }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      timestamp: null,
      score: null,
      dew_point: null,
      temp: null,
      humid: null,
      abs_humid: null,
      co2: null,
      co2_est: null,
      co2_est_baseline: null,
      voc: null,
      voc_baseline: null,
      voc_h2_raw: null,
      voc_ethanol_raw: null,
      pm25: null,
      pm10_est: null,
    };
  },
  mounted() {
    axios.get('http://localhost:8080/awair/latest')
        .then(response => {
          this.timestamp = response.data.timestamp;
          this.score = response.data.score;
          this.dew_point = response.data.dew_point;
          this.temp = response.data.temp;
          this.humid = response.data.humid;
          this.abs_humid = response.data.abs_humid;
          this.co2 = response.data.co2;
          this.co2_est = response.data.co2_est;
          this.co2_est_baseline = response.data.co2_est_baseline;
          this.voc = response.data.voc;
          this.voc_baseline = response.data.voc_baseline;
          this.voc_h2_raw = response.data.voc_h2_raw;
          this.voc_ethanol_raw = response.data.voc_ethanol_raw;
          this.pm25 = response.data.pm25;
          this.pm10_est = response.data.pm10_est;
        })
        .catch(error => {
          console.error("Error fetching data:", error);
        });
  }
}
</script>

<style scoped>
.awair-card {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>
