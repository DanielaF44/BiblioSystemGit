<template>
  <div class="home">
    <RechercheLivre @getLivresparCritere="getLivresparCritere" />
    <LivreCard v-for="livre in livres" :key="livre.isbn" :livre="livre" />
  </div>
</template>

<script>
// @ is an alias to /src
import LivreCard from "@/components/LivreCard.vue";
import BiblioServiceFront from "@/services/BiblioServiceFront";
// import de axios qui permet de gérer les requêtes entrant/sortant de l'API

import RechercheLivre from "../components/RechercheLivre.vue";

export default {
  name: "HomeView",
  components: {
    LivreCard,
    RechercheLivre,
  },
  data() {
    return {
      livres: null,
    };
  },
  created() {
    //recupère des livres à partir du back end quand le composant est crée
    BiblioServiceFront.getLivres()
      .then((response) => {
        this.livres = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    getLivresparCritere(sGenre, sTitre, sAuteur, sLangue) {
      BiblioServiceFront.getResults(sGenre, sTitre, sAuteur, sLangue)
        .then((response) => {
          this.livres = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
.home {
  padding: 0 5% 0 5%;
}
</style>
