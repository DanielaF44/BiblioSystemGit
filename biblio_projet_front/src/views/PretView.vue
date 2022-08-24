<template>
  <div class="prets">
    <PretCard
      v-for="pret in prets"
      :key="pret.pret_id"
      :pret="pret"
      @getPret="getPret"
    />
  </div>
</template>

<script>
// @ is an alias to /src
import PretCard from "@/components/PretCard.vue";
import BiblioServiceFront from "@/services/BiblioServiceFront";
// import de axios qui permet de gérer les requêtes entrant/sortant de l'API

export default {
  name: "PretView",
  components: {
    PretCard,
  },
  data() {
    return {
      prets: null,
    };
  },
  created() {
    //recupère des livres à partir du back end quand le composant est crée
    BiblioServiceFront.getPrets()
      .then((response) => {
        this.prets = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    getPret(sId) {
      BiblioServiceFront.postPrets(sId);
      location.reload();
    },
  },
};
</script>
<style scoped>
.home {
  padding: 0 5% 0 5%;
}
</style>
