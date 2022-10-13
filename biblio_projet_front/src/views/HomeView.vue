<template>
  <div class="home">
    
    <RechercheLivre @getLivresparCritere="getLivresparCritere" />
    <div class="errors" v-if="errors.length">
      <b>Please correct the following error(s):</b>
      <ul class="errors">
        <li v-for="error in errors" :key="error">{{ error }}</li>
      </ul>
    </div>
    <LivreCard v-for="livre in livres" :key="livre.isbn" :livre="livre" />
  </div>
</template>

<script>
// @ is an alias to /src
import LivreCard from "@/components/LivreCard.vue";
import BiblioServiceFront from "@/services/BiblioServiceFront";
// import de axios qui permet de gérer les requêtes entrant/sortant de l'API

import { useHead } from "@vueuse/head";

import RechercheLivre from "../components/RechercheLivre.vue";

export default {
  name: "HomeView",
  setup() {
    useHead({
      title: "Parcourir la bibliothèque",
      meta: [
        {
          name: "description",
          content: "rechercher des livres dans la bibliothèque",
        },
      ],
    });
  },
  components: {
    LivreCard,
    RechercheLivre,
  },
  data() {
    return {
      livres: null,
      errors: [],
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
    //methode qui reçoit en paramètres les données saisies à travers le composant enfant recherchelivre. Effectue des vérifications et envoie des erreurs si les données saisies ne sont pas valides
    getLivresparCritere(sGenre, sTitre, sAuteur, sLangue) {
      this.errors = [];

      if (sTitre) {
        if (sTitre.length > 100 || sTitre.includes("<", ">", "=")) {
          this.errors.push("Titre invalide");
        }
      }

      if (sAuteur) {
        if (sAuteur.length > 40 || sAuteur.includes("<", ">", "=")) {
          this.errors.push("Auteur invalide");
        }
      }

      //methode qui transmet les données saisies au service qui communique avec le back end. Les résultats provenant du back end sont ensuite transmis aux data
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
