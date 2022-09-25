<template>
  <header>
    <nav>
      <div class="logo-div">
        <router-link to="/"
          ><img
            class="logo"
            src="../assets/BiblioLogo1.png"
            alt="logo bibliothèque de Pontault-Combault"
        /></router-link>
      </div>
      <div class="NavItems">
        <ul v-show="!mobile" class="navList">
          <li>
            <router-link to="/welcome">Parcourir la bibliothèque</router-link>
          </li>
          <li>
            <router-link
              v-if="this.$store.state.auth.status.loggedIn"
              to="/prets"
              >Prêts</router-link
            >
          </li>
          <li>
            <router-link
              v-if="this.$store.state.auth.status.loggedIn && this.$store.state.auth.user.roles[0] == 'ADMIN'"
              to="/dashboard"
              >dashboard</router-link
            >
          </li>
          <li class="flexLeft-li">
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
      <div class="icon-div">
        <img
          src="../assets/bars-solid.svg"
          class="icon"
          v-on:click="toggleMobileNav"
          v-show="mobile"
          :class="{ 'icon-active': MobileNav }"
        />
      </div>
    </nav>
    <transition name="mobile-nav">
      <ul v-show="MobileNav" class="dropdown-nav">
        <li class="dropdown-li">
          <router-link to="/welcome">Parcourir la bibliothèque</router-link>
        </li>
        <li class="dropdown-li">
          <router-link v-if="this.$store.state.auth.status.loggedIn" to="/prets"
            >Prêts</router-link
          >
        </li>
        <li class="dropdown-li">
          <router-link
            v-if="!this.$store.state.auth.status.loggedIn"
            to="/connexion"
            >Se connecter</router-link
          >
        </li>
        <li class="dropdown-li">
          <router-link
            v-if="this.$store.state.auth.status.loggedIn"
            to="infos-user"
            >Informations du compte</router-link
          >
        </li>
        <li class="dropdown-li">
          <router-link
            class="button"
            v-if="this.$store.state.auth.status.loggedIn"
            v-on:click="logout"
            to="/"
          >
            Se déconnecter</router-link
          >
        </li>
        <li class="dropdown-li">
          <router-link
            v-if="!this.$store.state.auth.status.loggedIn"
            to="/inscription"
            >Créer un compte</router-link
          >
        </li>
      </ul>
    </transition>
  </header>
</template>

<script>
export default {
  name: "NavigationNav",
  data() {
    return {
      mobile: null,
      MobileNav: null,
      windowWidth: null,
    };
  },
  created() {
    window.addEventListener("resize", this.checkScreen);
    this.checkScreen();
  },
  methods: {
    toggleMobileNav() {
      this.MobileNav = !this.MobileNav;
    },
    checkScreen() {
      this.windowWidth = window.innerWidth;
      if (this.windowWidth <= 992) {
        this.mobile = true;
        return;
      }
      this.mobile = false;
      this.MobileNav = false;
      return;
    },
    logout() {
      this.$store.dispatch("auth/logout");
    },
  },
};
</script>
<style scoped>
.icon {
  width: 40px;
  height: 40px;
  cursor: pointer;
}

.icon-div {
  transition: 0.8s ease all;
}

.icon-active {
  transform: rotate(180deg);
}

.dropdown-nav {
  display: flex;
  flex-direction: column;
  position: fixed;
  width: 100%;
  background-color: #1b77bd;
  left: 0;
  list-style: none;
  margin-left: 0;
  padding-left: 0;
}

.dropdown-li {
  padding: 10px;
  margin: 15px;
}

li > a {
  text-decoration: none;
  color: #fff;
  font-weight: bold;
  color: #fff;
}

.flexLeft-li {
  flex: 5 1 0;
}

.logo-div {
  flex: 1 1 0;
}

.NavItems {
  flex: 7 1 0;
}

@media only screen and (max-width: 992px) {
  .logo-div {
    flex: 1 1 0;
  }

  .icon-div {
    flex: 7 1 0;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
