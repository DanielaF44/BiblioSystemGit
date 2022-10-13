<template>
  <BarChart class="chart" />
</template>

<script>
import BarChart from "@/components/BarChart.vue";
import { useHead } from "@vueuse/head";

export default {
  name: "StatView",
  //ajout balises meta à l'entête de la page html
  setup() {
    useHead({
      title: "Statistiques",
      meta: [
        {
          name: "description",
          content: "Statistiques sur les prêts, les livres et les auteurs",
        },
      ],
    });
  },
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

@media only screen and (max-width: 768px) {
  * {
    flex-direction: column;
  }

  .chart {
    width: 100%;
    margin: 400px;
  }
}
</style>
