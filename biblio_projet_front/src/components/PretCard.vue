<template>
  <div class="pretCard">
    <h4>{{ pret.exemplaireId.isbn.titre }}</h4>
    <p>Date de publication: {{ pret.exemplaireId.isbn.datePublication }}</p>
    <p>Date de début: {{ pret.dateDebut }}</p>
    <p>Date de fin: {{ pret.dateFin }}</p>
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
