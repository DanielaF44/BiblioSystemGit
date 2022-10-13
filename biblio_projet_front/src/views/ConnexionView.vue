<template>
  <div class="height">
    <section class="Connexion">
      <form>
        <div class="entree">
          <label class="label" for="email">E-mail</label>
          <div class="input-div">
            <input
              type="text"
              v-model="user.username"
              placeholder="john.doe@email.com"
            />
          </div>
        </div>
        <div class="entree">
          <label class="label" for="MotDePasse">Mot de passe</label>
          <div class="input-div">
            <input type="password" v-model="user.password" />
          </div>
        </div>
        <button class="button" v-on:click.prevent="handleLogin(user)">
          Se connecter
        </button>
      </form>
    </section>
  </div>
</template>
<script>
import { useHead } from "@vueuse/head";

export default {
  name: "ConnexionView",
  //ajout balises meta à l'entête de la page html
  setup() {
    useHead({
      title: "Se connecter",
      meta: [
        {
          name: "description",
          content: "page de connexion",
        },
      ],
    });
  },
  data() {
    return {
      user: { username: null, password: null },
      error: null,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  methods: {
    handleLogin(user) {
      //this.loading = true;
      this.$store
        .dispatch("auth/login", user)
        .then(() => {
          this.$router.push({ path: "/welcome" });
        })
        .catch(() => {
          this.$toast.show("Identifiants incorrects", {
            type: "error",
            duration: 2500,
          });
          this.user.username = null;
          this.user.password = null;
        });
    },
  },
};
</script>
<style scoped>
.Connexion {
  padding: 20px;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
  padding-right: 5%;
  background-color: #4b9cda;
  margin-left: 20px;
  margin-right: 20px;
}

.label {
  padding: 20px;
  font-weight: bold;
  width: 400px;
  text-align: end;
  flex: 5 1 0;
}

input {
  border: none;
  border-image: none;
  border-radius: 5px;
  height: 35px;
  margin-left: 20px;
  min-width: 250px;
}

.input-div {
  flex: 5 1 0;
  text-align: left;
}

form > button {
  width: 250px;
}

.entree {
  margin: 15px;
  display: flex;
  justify-content: center;
}

.height {
  min-height: 100vh;
}
@media only screen and (max-width: 768px) {
  input {
    min-width: 80%;
  }

  .entree {
    flex-direction: column;
  }

  .label {
    text-align: center;
  }

  label {
    text-align: center;
  }

  .buttonIns {
    width: 250px;
    height: 80px;
  }
}
</style>
