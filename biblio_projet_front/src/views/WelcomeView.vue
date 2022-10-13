<template>
  <div class="container">
    <div class="titres">
      <h1>Bienvenue sur "Biblio"</h1>
      <h2>Le site de la bibliothèque de Pontault-Combault</h2>
    </div>
  </div>
</template>

<script>
import { useHead } from "@vueuse/head";

export default {
  //ajout balises meta à l'entête de la page html
  setup() {
    useHead({
      title: "Bienvenue",
      meta: [
        {
          name: "description",
          content: "page de bienvenue",
        },
      ],
    });
  },
};
</script>

<style scoped>
.titres {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  height: 300px;
  width: 60%;
  margin: auto;
  background-color: rgba(255, 255, 255, 70%);
  padding: 30px;
}

.container {
  width: 100%;
  background: radial-gradient(
    circle,
    rgba(27, 119, 189, 1) 5%,
    rgba(162, 220, 228, 0.6730042358740371) 41%,
    rgba(162, 220, 228, 0) 92%
  );
  background-size: 100%;
  height: 100vh;
  padding-top: 10%;
}

template {
  margin: 0 !important;
}
</style>
