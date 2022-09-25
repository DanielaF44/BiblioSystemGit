<template>
  <!-- le chargement du chart se fait directement alors qu'on fait un appel asynchrone au service
  il faut donc conditioner l'affichage avec un if afin de ne charger le chart qu'une fois le
  résultat du service reçu -->
  <Bar v-if="loaded" :chart-data="chartData" :styles="myStyles" />
</template>

<script>
import BiblioServiceFront from "@/services/BiblioServiceFront";
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "BarChart",
  components: { Bar },
  data() {
    return {
      loaded: false,
      chartData: null,
      livresPlusPretesData: null,
    };
  },
  async mounted() {
    //recupère les livres les plus pretes
    BiblioServiceFront.getLivresPretes(1)
      .then((response) => {
        this.livresPlusPretesData = response.data;
        //on recupere les valeurs de l'axe des x => titres
        const x = this.livresPlusPretesData.map((aData) => aData.titre);
        //on recupere les valeurs de l'axe des y => nombre de prets
        const y = this.livresPlusPretesData.map((aData) => aData.nbPret);

        this.chartData = {
          labels: x,
          datasets: [
            {
              label: "Nombre de prêts",
              backgroundColor: "#f87979",
              data: y,
            },
          ],
        };

        this.loaded = true;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  computed: {
    myStyles() {
      return {
        position: "relative",
      };
    },
  },
};
</script>
