<script setup>
import { formatDate } from "./commonFunction.js";
</script>

<template>
  <div class="pretCard">
    <h4>{{ pret.exemplaireId.isbn.titre }}</h4>
    <p>
      Date de publication:
      {{ formatDate(pret.exemplaireId.isbn.datePublication) }}
    </p>
    <p>Date de début: {{ formatDate(pret.dateDebut) }}</p>
    <p>Date de fin: {{ formatDate(pret.dateFin) }}</p>
    <button type="button" v-if="pret.renouvele" disabled>
      Impossible de prolonger la période de prêt une seconde fois
    </button>
    <button type="button" v-else v-on:click="prolonger(pret.pret_id)">
      Prolonger la periode de prêt
    </button>
  </div>
</template>

<script>
/*import BiblioServiceFront from "@/services/BiblioServiceFront";*/
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.pretCard {
  padding: 20px;
  width: 95%;
  cursor: pointer;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
}

h4 {
  color: #1b77bd;
  font-weight: bold;
}
</style>
