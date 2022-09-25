<template>
  <!--TODO styles-->
  <!-- le chargement du chart se fait directement alors qu'on fait un appel asynchrone au service
  il faut donc conditioner l'affichage avec un if afin de ne charger le chart qu'une fois le
  résultat du service reçu -->
  <Bar
    v-if="livresPlusLoaded"
    :chart-data="livresPlusChartData"
    :styles="livresPlusStyles"
  />
  <Bar
    v-if="livresMoinsLoaded"
    :chart-data="livresMoinsChartData"
    :styles="livresmoinsStyles"
  />

  <Bar
    v-if="auteursPlusLoaded"
    :chart-data="auteursPlusChartData"
    :styles="auteursPlusStyles"
  />

  <Bar
    v-if="auteursMoinsLoaded"
    :chart-data="auteursMoinsChartData"
    :styles="auteursMoinsStyles"
  />
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
      livresPlusLoaded: false,
      livresPlusChartData: null,
      livresPlusPretesData: null,

      livresMoinsLoaded: false,
      livresMoinsChartData: null,
      livresMoinsPretesData: null,

      auteursPlusLoaded: false,
      auteursPlusChartData: null,
      auteursPlusLusData: null,
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
        this.livresPlusChartData = {
          labels: x,
          datasets: [
            {
              label: "Nombre de prêts",
              backgroundColor: "#f87979",
              data: y,
            },
          ],
        };

        this.livresPlusLoaded = true;
      })
      .catch((error) => {
        console.log(error);
      });

    //recupère les livres les moins pretes
    BiblioServiceFront.getLivresPretes(0)
      .then((response) => {
        this.livresMoinsPretesData = response.data;
        //on recupere les valeurs de l'axe des x => titres
        const x = this.livresMoinsPretesData.map((aData) => aData.titre);
        //on recupere les valeurs de l'axe des y => nombre de prets
        const y = this.livresMoinsPretesData.map((aData) => aData.nbPret);
        this.livresMoinsChartData = {
          labels: x,
          datasets: [
            {
              label: "Nombre de prêts",
              backgroundColor: "#f87979",
              data: y,
            },
          ],
        };

        this.livresMoinsLoaded = true;
      })
      .catch((error) => {
        console.log(error);
      });

    //recupère les auteurs les plus lus
    BiblioServiceFront.getAuteursLus(1)
      .then((response) => {
        this.auteursPlusLusData = response.data;
        //on recupere les valeurs de l'axe des x => auteur
        const x = this.auteursPlusLusData.map(
          (aData) => aData.prenom + " " + aData.nom
        );
        //on recupere les valeurs de l'axe des y => nombre de prets
        const y = this.auteursPlusLusData.map((aData) => aData.nbPret);
        this.auteursPlusChartData = {
          labels: x,
          datasets: [
            {
              label: "Nombre de prêts",
              backgroundColor: "#f87979",
              data: y,
            },
          ],
        };

        this.auteursPlusLoaded = true;
      })
      .catch((error) => {
        console.log(error);
      });

    //recupère les auteurs les plus lus
    BiblioServiceFront.getAuteursLus(0)
      .then((response) => {
        this.auteursMoinsLusData = response.data;
        //on recupere les valeurs de l'axe des x => auteur
        const x = this.auteursMoinsLusData.map(
          (aData) => aData.prenom + " " + aData.nom
        );
        //on recupere les valeurs de l'axe des y => nombre de prets
        const y = this.auteursMoinsLusData.map((aData) => aData.nbPret);
        this.auteursMoinsChartData = {
          labels: x,
          datasets: [
            {
              label: "Nombre de prêts",
              backgroundColor: "#f87979",
              data: y,
            },
          ],
        };

        this.auteursMoinsLoaded = true;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
