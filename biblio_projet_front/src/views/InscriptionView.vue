<template>
  <section class="Inscription">
    <form>
      <div class="entree">
        <label for="nom">Nom</label>
        <input type="text" />
      </div>
      <div class="entree">
        <label for="prenom">Prénom</label>
        <input type="text" />
      </div>
      <div class="entree">
        <label for="email">E-mail</label>
        <input type="text" />
      </div>
      <div class="entree">
        <label for="mdp">Mot de passe</label>
        <input type="text" />
      </div>
      <div class="entree">
        <label for="mdp">Confirmation du mot de passe</label>
        <input type="text" />
      </div>
      <button>Valider la création d'un compte</button>
    </form>
  </section>
</template>
<script>
export default {
  name: "ConnexionView",
};
</script>
<style scoped>
.Inscription {
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
  min-width: 270px;
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
  width: 300px;
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
