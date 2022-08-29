<template>
  <section class="Connexion">
    <form>
      <div class="entree">
        <label for="email">E-mail</label>
        <input type="text" v-model="user.username"/>
      </div>
      <div class="entree">
        <label for="MotDePasse">Mot de passe</label>
        <input type="text" v-model="user.password"/>
      </div>
      <button v-on:click="handleLogin(user)">Se connecter</button>
    </form>
  </section>
</template>
<script>
export default {
  name: "ConnexionView",
  data() {
    return {
      user: { username: null, password: null },
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      //this.$router.push("/welcome");
    }
  },
  methods: {
    handleLogin(user) {
      console.log(user);
      this.loading = true;
      this.$store.dispatch("auth/login", user).then(
        () => {
          //this.$router.push("/welcome");
        },
        (error) => {
         /* this.loading = false;
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();*/
            console.log(error.toString());
        }
      );
    },
  }
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
