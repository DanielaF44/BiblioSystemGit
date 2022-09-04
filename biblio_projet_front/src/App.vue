<template>
  <!-- Barre de navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
      <router-link to="/"
        ><img
          class="logo"
          src="./assets/BiblioLogo1.png"
          alt="logo bibliothèque de Pontault-Combault"
      /></router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#bs-example-navbar-collapse-1"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div
        class="collapse navbar-collapse col-11"
        id="bs-example-navbar-collapse-1"
      >
        <ul class="navbar-nav container-fluid">
          <li class="nav-item ms-col-3">
            <router-link to="/welcome">Parcourir la bibliothèque</router-link>
          </li>
          <li class="nav-item ms-col-3">
            <router-link
              v-if="this.$store.state.auth.status.loggedIn"
              to="/prets"
              >Prêts</router-link
            >
          </li>
          <li class="nav-item ms-col-6">
            <ul class="flexLeft">
              <li class="innerNav">
                <router-link
                  v-if="!this.$store.state.auth.status.loggedIn"
                  to="/connexion"
                  >Se connecter</router-link
                >
              </li>
              <li class="innerNav">
                <router-link
                  v-if="this.$store.state.auth.status.loggedIn"
                  to="infos-user"
                  >Informations du compte</router-link
                >
              </li>
              <li class="innerNav">
                <router-link
                  class="button"
                  v-if="this.$store.state.auth.status.loggedIn"
                  v-on:click="logout"
                  to="/"
                >
                  Se déconnecter</router-link
                >
              </li>
              <li class="innerNav">
                <router-link
                  v-if="!this.$store.state.auth.status.loggedIn"
                  to="/inscription"
                  >Créer un compte</router-link
                >
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <router-view />
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
    },
  },
};
</script>

<style>
#app {
  font-family: Montserrat, Arial, Helvetica, sans-serif, Helvetica, Arial,
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  margin-bottom: 30px;
  text-align: left;
  background-color: #1b77bd;
}

nav a {
  font-weight: bold;
  color: #fff;
  text-decoration: none;
  margin: 20px;
}

nav a.router-link-exact {
  border: solid #fff 1px;
  padding: 20px;
  margin: 20px;
}

.logo {
  width: 90px;
}

ul a.router-link-exact-active {
  background-color: #4b9cda;
  padding: 15px;
  border-radius: 15px;
}

.innerNav {
  list-style: none;
}

.flexLeft {
  display: flex;
  justify-content: flex-end;
}

.button {
  background-color: #a2dce4;
  border: none;
  border-radius: 15px;
  height: 50px;
  font-size: 20px;
  font-weight: bold;
  color: #1b77bd;
  padding: 10px 20px 12px 20px;
  margin: 30px;
}

form > button {
  border-radius: 15px;
  margin: 30px;
}
</style>
