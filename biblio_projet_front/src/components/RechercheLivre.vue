<template>
  <div class="rechercheLivre">
    <form role="search">
      <label for="titre">Titre</label>
      <input type="text" v-model="rechercheTitre" role="searchbox" />
      <label for="auteur">Auteur</label>
      <input type="text" v-model="rechercheAuteur" role="searchbox" />
      <label for="genre">Genre</label>
      <select v-model="rechercheGenre" role="searchbox">
        <option v-for="genre in genres" :key="genre.nom" :value="genre.nom">
          {{ genre.nom }}
        </option>
      </select>
      <label for="langue">Langue</label>
      <select v-model="rechercheLangue" role="searchbox">
        <option v-for="langue in langues" :key="langue.nom" :value="langue.nom">
          {{ langue.nom }}
        </option>
      </select>
      <button class="button" type="button" v-on:click="rechercher">
        Rechercher
      </button>
    </form>
  </div>
</template>
<script>
import BiblioServiceFront from "@/services/BiblioServiceFront";
export default {
  name: "rechercheLivre",
  props: {
    genre: {
      type: Object,
      required: false,
    },
  },

  data() {
    return {
      genres: null,
      rechercheGenre: null,
      rechercheTitre: null,
      rechercheAuteur: null,
      langues: null,
      rechercheLangue: null,
    };
  },
  created() {
    //recupère les genres à partir du back end quand le composant est crée
    BiblioServiceFront.getGenres()
      .then((response) => {
        //ajout entrée vide par défaut
        let emptyval = { nom: "" };
        let result = [emptyval].concat(response.data);

        this.genres = result;
      })
      .catch((error) => {
        console.log(error);
      });
    BiblioServiceFront.getLangues()
      .then((response) => {
        //ajout entrée vide par défaut
        let emptyval = { nom: "" };
        let result = [emptyval].concat(response.data);

        this.langues = result;
      })
      .catch((error) => {
        console.log(error);
      });
  },

  methods: {
    //methode déclanchée en cliquant sur le bouton Rechercher qui va transmettre les données saisie à la methode getLivreparCritere du composant parent Homeview
    rechercher() {
      this.$emit(
        "getLivresparCritere",
        this.rechercheGenre,
        this.rechercheTitre,
        this.rechercheAuteur,
        this.rechercheLangue
      );
    },
  },
};
</script>
<style scoped>
.rechercheLivre {
  background-color: #4b9cda;
  margin-bottom: 30px;
  padding: 30px 0 30px 0;
  width: 100%;
}

label {
  padding: 20px;
  font-weight: bold;
}

input {
  border: none;
  border-image: none;
  border-radius: 5px;
  height: 35px;
}

select {
  border: none;
  border-image: none;
  border-radius: 5px;
  height: 35px;
}

form > button {
  border-radius: 15px;
  margin: 30px;
}

@media only screen and (max-width: 984px) {
  input,
  select,
  label {
    display: block;
  }

  input,
  select {
    margin: auto;
  }
}
</style>
