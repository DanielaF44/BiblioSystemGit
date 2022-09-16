<template>
  <BarChart />
</template>

<script>
import BarChart from "@/components/BarChart.vue";

export default {
  name: "StatView",
  components: {
    BarChart,
  },
  data() {
    return {
      prets: null,
    };
  },
};
</script>
