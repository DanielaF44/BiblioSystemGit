<template>
  <div class="titres">
    <h1>Bienvenue sur "Biblio"</h1>
    <h2>Le site de la bibliothèque de Pontault-Combault</h2>
  </div>
  <img
    class="photo-biblio"
    src="../assets/biblio-photo.jpg"
    alt="livres bibliothèque Pontault Combault"
  />
</template>
<style scoped>
.photo-biblio {
  position: relative;
  z-index: 1;
  bottom: 200px;
  width: 100%;
}

.titres {
  position: relative;
  top: 150px;
  z-index: 2;
  width: 60%;
  margin: auto;
  background-color: rgba(255, 255, 255, 70%);
  padding: 30px;
}
</style>
