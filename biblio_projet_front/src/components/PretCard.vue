<script setup>
import { formatDate } from "./commonFunction.js";
</script>

<template>
  <div class="pretCard">
    <h4>{{ pret.exemplaireId.isbn.titre }}</h4>
    <div class="container">
      <img
        :src="require('@/assets/' + pret.exemplaireId.isbn.cover)"
        v-bind:alt="pret.exemplaireId.isbn.cover"
      />
      <ul>
        <li>
          <span>Genre/s: </span>
          <p
            class="label"
            v-for="genre in pret.exemplaireId.isbn.genres"
            :key="genre.nom"
          >
            {{ genre.nom }}
          </p>
        </li>
        <li>
          <span>Date de publication: </span>
          <p class="label">
            {{ formatDate(pret.exemplaireId.isbn.datePublication) }}
          </p>
        </li>
      </ul>
      <ul>
        <li>
          <span>par: </span>
          <p
            class="label"
            v-for="auteur in pret.exemplaireId.isbn.auteurs"
            :key="auteur.nom"
          >
            {{ auteur.prenom }} {{ auteur.nom }}
          </p>
        </li>
        <li>
          <span>Isbn: </span>
          <p class="label">{{ pret.exemplaireId.isbn.isbn }}</p>
        </li>
        <li>
          <span>Editeur: </span>
          <p class="label">{{ pret.exemplaireId.isbn.editeur.nom }}</p>
        </li>
      </ul>
    </div>
    <div class="prolonge">
      <p class="dates">
        Date de début: {{ formatDate(pret.dateDebut) }} - Date de fin:
        {{ formatDate(pret.dateFin) }}
      </p>
      <div>
        <button class="disabled" type="button" v-if="pret.renouvele" disabled>
          Impossible de prolonger la période de prêt une seconde fois
        </button>
        <button
          type="button"
          v-else
          v-on:click.prevent="prolonger(pret.pret_id)"
        >
          Prolonger la periode de prêt
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "pretCard",
  props: {
    pret: {
      type: Object,
      required: true,
    },
  },
  methods: {
    prolonger(sId) {
      this.$emit("getPret", sId);
    },
  },
};
</script>

<style scoped>
.pretCard {
  padding: 20px;
  width: 90%;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
  margin-left: 20px;
  padding-right: 5%;
}

h4 {
  color: #1b77bd;
  font-weight: bold;
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
}

img {
  width: 150px;
  height: 200px;
}

.container {
  display: flex;
  justify-content: flex-start;
  margin: 20px;
  align-items: center;
}
.prolonge {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.dates {
  margin-right: 30px;
  font-weight: bold;
  padding-top: 10px;
}

div > button {
  border-radius: 15px;
  padding: 15px;
}

.disabled {
  background-color: rgb(201, 200, 200);
  color: darkgrey;
}

@media only screen and (max-width: 992px) {
  .container {
    flex-direction: column;
  }
}
</style>
