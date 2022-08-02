<template>
  <div class="livreCard">
    <h4>{{ livre.titre }}</h4>
    <p>{{ livre.datePublication }}</p>
    <p>{{ livre.isbn }}</p>
    <p>{{ livre.editeur }}</p>
  </div>
</template>

<script>
export default {
  name: "livreCard",
  props: {
    livre: {
      type: Object,
      required: true,
    },
  },
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
}

h4 {
  color: #1b77bd;
  font-weight: bold;
}
</style>
