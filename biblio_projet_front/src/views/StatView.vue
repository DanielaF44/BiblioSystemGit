<template>
  <BarChart class="chart" />
</template>

<script>
import BarChart from "@/components/BarChart.vue";

export default {
  name: "StatView",
  components: {
    BarChart,
  },
};
</script>
<style scoped>
* {
  display: flex;
  flex-direction: row;
}
.chart {
  width: 50%;
  margin: 0;
}
</style>
