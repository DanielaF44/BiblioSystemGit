<template>
  <section class="Connexion">
    <form>
      <div class="entree">
        <label for="titre">E-mail</label>
        <input type="text" />
      </div>
      <div class="entree">
        <label for="auteur">Mot de passe</label>
        <input type="text" />
      </div>
      <button>Se connecter</button>
    </form>
  </section>
</template>
<script>
export default {
  name: "ConnexionView",
};
</script>
<style scoped>
.Connexion {
  padding: 20px;
  width: 95%;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
  padding-right: 5%;
  background-color: #4b9cda;
  margin-left: 20px;
}

label {
  padding: 20px;
  font-weight: bold;
  min-width: 200px;
  text-align: end;
}

input {
  border: none;
  border-image: none;
  border-radius: 5px;
  height: 35px;
  margin-left: 20px;
}

form > button {
  width: 250px;
}

@media only screen and (max-width: 576px) {
  input {
    min-width: 80%;
  }

  label {
    text-align: center;
  }
}
</style>
