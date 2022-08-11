<template>
  <div class="rechercheLivre">
    <form>
      <label for="titre">Titre</label>
      <input type="text" v-model="rechercheTitre" />
      <label for="auteur">Auteur</label>
      <input type="text" v-model="rechercheAuteur" />
      <label for="genre">Genre</label>
      <select v-model="rechercheGenre">
        <option v-for="genre in genres" :key="genre.nom" :value="genre.nom">
          {{ genre.nom }}
        </option>
      </select>
      <label for="langue">Langue</label>
      <select v-model="rechercheLangue">
        <option v-for="langue in langues" :key="langue.nom" :value="langue.nom">
          {{ langue.nom }}
        </option>
      </select>
      <a href="#" v-on:click="rechercher">Rechercher</a>
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
        //add default empty entry
        let emptyval = { nom: "" };
        let result = [emptyval].concat(response.data);

        this.genres = result;
        //this.genres = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    BiblioServiceFront.getLangues()
      .then((response) => {
        //add default empty entry
        let emptyval = { nom: "" };
        let result = [emptyval].concat(response.data);

        this.langues = result;
        // this.langues = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },

  methods: {
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
</style>
