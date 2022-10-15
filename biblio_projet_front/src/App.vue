<template>
  <!-- Barre de navigation-->
  <header>
    <NavigationNav></NavigationNav>
  </header>
  <router-view />
  <footer>
    <router-link class="mentions" to="/mentions">Mentions légales</router-link>
    <p class="copyright">&copy; Pontault-Combault</p>
  </footer>
</template>

<script>
import NavigationNav from "./components/NavigationNav.vue";
export default {
  components: {
    NavigationNav,
  },
};
</script>

<style>
* {
  padding: 0px;
  margin: 0px;
}
#app {
  font-family: Montserrat, Arial, Helvetica, sans-serif, Helvetica, Arial,
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;
  min-height: 100vh;
}

html,
body {
  min-height: 100vh;
}

nav {
  margin-bottom: 30px;
  text-align: left;
  background-color: #1b77bd;
  list-style: none;
  display: flex;
  align-items: center;
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

.navList {
  list-style: none;
  display: flex;
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

.errors {
  list-style: none;
  color: red;
}

header {
  width: 100%;
  background-color: #1b77bd;
  margin: 0;
  padding: 0;
}

footer {
  height: 80px;
  background-color: #1b77bd;
  padding: 40px;
  position: relative;
  bottom: 0;
}

.mentions {
  color: white;
  text-decoration: none;
}

.copyright {
  color: white;
  margin: 20px;
}
</style>
