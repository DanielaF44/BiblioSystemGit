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
import { useHead } from "@vueuse/head";

export default {
  name: "PretView",
  //ajout balises meta à l'entête de la page html
  setup() {
    useHead({
      title: "Prets en cours",
      meta: [
        {
          name: "description",
          content: "Liste des prêts en cours de l'utilisateur",
        },
      ],
    });
  },
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
    //appelle la methode de biblioservicefront qui permet de prolonger un prêt dans le back end de l'application
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
