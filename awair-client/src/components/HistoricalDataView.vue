<template>
  <div>
    <button @click="fetchHistoricalData">View More</button>
    <div v-if="loading">Loading...</div>
    <div v-if="error" class="error">Error loading data</div>
    <canvas ref="historicalChart"></canvas>
    <div v-if="noData" class="no-data">No historical data available</div>
  </div>
</template>

<script>
import axios from 'axios';
import {CategoryScale, Chart, Legend, LinearScale, LineController, LineElement, PointElement, Tooltip} from 'chart.js';

Chart.register(LineController, LineElement, PointElement, LinearScale, CategoryScale, Tooltip, Legend);

export default {
  data() {
    return {
      loading: false,
      error: false,
      dataLoaded: false,
      noData: false,
      chart: null,
    };
  },
  methods: {
    fetchHistoricalData() {
      this.loading = true;
      this.error = false;
      this.dataLoaded = false;
      this.noData = false;

      const now = new Date();
      const from = new Date(now.getTime() - 24 * 60 * 60 * 1000); // 24 hours ago
      const to = now;

      axios.get(`/awair/interval?from=${from.toISOString()}&to=${to.toISOString()}`)
          .then(response => {
            const data = response.data;
            if (data && data.length > 0) {
              this.$nextTick(() => {
                this.createChart(data);
              });
              this.dataLoaded = true;
            } else {
              this.noData = true;
            }
          })
          .catch(error => {
            console.error("Error fetching data:", error);
            this.error = true;
          })
          .finally(() => {
            this.loading = false;
          });
    },
    createChart(data) {
      const labels = data.map(reading => new Date(reading.timestamp).toLocaleTimeString());
      const scores = data.map(reading => reading.score);

      const ctx = this.$refs.historicalChart.getContext('2d');
      if (this.chart) {
        this.chart.destroy();
      }
      this.chart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: labels,
          datasets: [{
            label: 'Air Quality Score',
            data: scores,
            // Other chart options...
          }]
        },
        // Additional chart configuration...
      });
    }
  }
};
</script>

<style>
.error {
  color: red;
}

.no-data {
  color: grey;
}
</style>
