<template>
  <section class="Inscription">
    <form @submit="checkForm">
      <div class="errors" v-if="errors.length">
        <b>Please correct the following error(s):</b>
        <ul class="errors">
          <li v-for="error in errors" :key="error">{{ error }}</li>
        </ul>
      </div>
      <div class="entree">
        <label for="nom">Nom</label>
        <input v-model="user.nom" type="text" placeholder="Doe" />
      </div>
      <div class="entree">
        <label for="prenom">Prénom</label>
        <input v-model="user.prenom" type="text" placeholder="John" />
      </div>
      <div class="entree">
        <label for="email">E-mail</label>
        <input
          v-model="user.email"
          type="text"
          placeholder="john.doe@email.com"
        />
      </div>
      <div class="entree">
        <label for="mdp">Mot de passe</label>
        <input v-model="user.password" type="password" />
      </div>
      <div class="entree">
        <label for="mdp">Confirmation du mot de passe</label>
        <input v-model="passwordConf" type="password" />
      </div>
      <button
        class="button buttonIns"
        type="submit"
        v-on:click.prevent="handleRegister(user, passwordConf)"
      >
        Valider la création d'un compte
      </button>
    </form>
  </section>
</template>
<script>
export default {
  name: "ConnexionView",
  data() {
    return {
      user: { nom: null, prenom: null, email: null, password: null },
      passwordConf: null,
      errors: [],
    };
  },
  methods: {
    handleRegister(user, passwordConf) {
      this.errors = [];
      if (!user.nom || user.nom.length < 2) {
        this.errors.push("Nom invalide");
      }

      if (!user.prenom || user.prenom.length < 2) {
        this.errors.push("Prenom invalide");
      }

      if (!user.email || user.email.includes("@", ".")) {
        this.errors.push("Email invalide");
      }

      const passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
      if (!user.password || !user.password.match(passw)) {
        this.errors.push("Mot de passe invalide");
      }

      if (user.password != passwordConf) {
        this.errors.push("Les deux mots de passe doivent être identiques");
      }

      if (this.errors.length > 0) {
        return;
      }
      this.$store.dispatch("auth/register", user);
    },
  },
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

.buttonIns {
  width: 350px;
}

.errors {
  list-style: none;
  color: red;
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
