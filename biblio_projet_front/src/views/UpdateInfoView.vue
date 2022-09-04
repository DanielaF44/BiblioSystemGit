<template>
  <section class="UpdateInfo">
    <form>
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
        <input type="password" />
      </div>
      <button
        class="button buttonIns"
        type="submit"
        v-on:click="handleUpdate(user)"
      >
        Valider la modification du compte
      </button>
    </form>
  </section>
</template>
<script>
export default {
  name: "UpdateInfoView",
  data() {
    return {
      user: { nom: null, prenom: null, email: null, password: null },
      error: null,
    };
  },
  methods: {
    handleUpdate(user) {
      //this.message = "";
      //this.successful = false;
      //this.loading = true;
      this.$store.dispatch("auth/profile", user).then(() => {
       // this.$router.push({ path: "/welcome" });
      });
      //.catch((err) => {
      // this.error = err.response.data.error;
      //});
      //console.log(error.toString());
    },
  },
};
</script>
<style scoped>
.UpdateInfo {
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
  width: 400px;
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
