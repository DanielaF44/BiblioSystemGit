<template>
  <div class="container">
    <div class="titres">
      <h1>Bienvenue sur "Biblio"</h1>
      <h2>Le site de la bibliothèque de Pontault-Combault</h2>
    </div>

    <!--<img
    class="photo-biblio"
    src="../assets/biblio-photo.jpg"
    alt="livres bibliothèque Pontault Combault"
  />-->
  </div>
</template>
<style scoped>
/*.photo-biblio {
  position: relative;
  z-index: 1;
  bottom: 200px;
  width: 100%;
}*/

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
