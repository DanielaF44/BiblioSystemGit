<script setup>
import { formatDate } from "./commonFunction.js";
</script>

<template>
  <div class="livreCard">
    <h4>{{ livre.titre }}</h4>
    <div class="container">
      <img src="https://picsum.photos/150/200" />
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
  </div>
</template>

<script>
// import de axios qui permet de gérer les requêtes entrant/sortant de l'API
export default {
  name: "livreCard",
  props: {
    livre: {
      type: Object,
      required: true,
    },
  },
  /*methods: {
    showDispo(sIsbn) {
      //recupère des livres à partir du back end quand le composant est crée
      BiblioServiceFront.getDispos(sIsbn)
        .then((response) => {
          this.prets = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },*/
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.livreCard {
  padding: 20px;
  width: 95%;
  cursor: pointer;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
  padding-right: 5%;
}

h4 {
  color: #1b77bd;
  font-weight: bold;
}

.label {
  display: inline-block;
}

ul {
  list-style: none;
  text-align: left;
  padding-left: 50px;
}

span {
  font-weight: bold;
}

.container {
  display: flex;
  justify-content: flex-start;
  margin: 20px;
  align-items: center;
}

@media only screen and (max-width: 992px) {
  .container {
    flex-direction: column;
  }
}
</style>
