<template>
  <div class="home">
    <LivreCard v-for="livre in livres" :key="livre.isbn" :livre="livre" />
  </div>
</template>

<script>
// @ is an alias to /src
import LivreCard from "@/components/LivreCard.vue";
// import de axios qui permet de gérer les requêtes entrant/sortant de l'API
import axios from "axios";

export default {
  name: "HomeView",
  components: {
    LivreCard,
  },
  data() {
    return {
      livres: null,
    };
  },
  created() {
    //recupère des livres à partir du back end quand le composant est crée
    axios
      .get("http://localhost:8080/livres/")
      .then((response) => {
        this.livres = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
<style scoped>
.home{
  padding: 0 5% 0 5%;
}
</style>
