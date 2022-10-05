<script setup>
import { formatDate } from "./commonFunction.js";
</script>

<template>
  <div class="livreCard">
    <h4>{{ livre.titre }}</h4>
    <div class="container">
      <img :src="require('@/assets/' + livre.cover)" v-bind:alt="livre.cover" />
      <ul>
        <li>
          <span>Genre/s: </span>
          <p class="label" v-for="genre in livre.genres" :key="genre.nom">
            {{ genre.nom }}
          </p>
        </li>
        <li>
          <span>Date de publication: </span>
          <p class="label">{{ formatDate(livre.datePublication) }}</p>
        </li>
      </ul>
      <ul>
        <li>
          <span>par: </span>
          <p class="label" v-for="auteur in livre.auteurs" :key="auteur.nom">
            {{ auteur.prenom }} {{ auteur.nom }}
          </p>
        </li>
        <li>
          <span>Isbn: </span>
          <p class="label">{{ livre.isbn }}</p>
        </li>
        <li>
          <span>Editeur: </span>
          <p class="label">{{ livre.editeur.nom }}</p>
        </li>
      </ul>
    </div>
    <div>
      <h5>Présentation:</h5>
      <p>{{ livre.description }}</p>
    </div>
    <DispoInfo v-bind:dispo="dispos" />
  </div>
</template>

<script>
import DispoInfo from "@/components/DispoInfo.vue";
import BiblioServiceFront from "@/services/BiblioServiceFront";
// import de axios qui permet de gérer les requêtes entrant/sortant de l'API
export default {
  name: "LivreCard",
  Components: {
    DispoInfo,
  },
  props: {
    livre: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      dispos: null,
    };
  },
  created() {
    this.showDispo(this.livre.isbn);
  },
  methods: {
    showDispo(sIsbn) {
      //recupère des livres à partir du back end quand le composant est crée
      BiblioServiceFront.getDispos(sIsbn)
        .then((response) => {
          console.log(this.dispos);
          this.dispos = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.livreCard {
  padding: 20px;
  width: 95%;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
  padding-right: 5%;
}

h4 {
  color: #1b77bd;
  font-weight: bold;
  padding: 15px;
}

.label {
  display: inline-block;
  padding: 15px;
}

ul {
  list-style: none;
  text-align: left;
  padding-left: 50px;
}

span {
  font-weight: bold;
  padding: 15px;
}

.container {
  display: flex;
  justify-content: flex-start;
  margin: 20px;
  align-items: center;
}

p {
  padding: 15px;
}

img {
  width: 150px;
  height: 200px;
}

@media only screen and (max-width: 992px) {
  .container {
    flex-direction: column;
  }
}
</style>
