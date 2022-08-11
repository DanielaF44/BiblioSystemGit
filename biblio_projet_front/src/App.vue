<template>
  <!-- Barre de navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
      <router-link to="/welcome"><img
          class="logo"
          src="./assets/logo-pontault-combault-rvb.jpg"
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
            <router-link to="/">Parcourir la bibliothèque</router-link>
          </li>
          <!--<li class="nav-item ms-col-3">
            <router-link to="/welcome">Bienvenue</router-link>
          </li>-->
          <li class="nav-item ms-col-6">
            <ul class="flexLeft">
              <li class="innerNav">
                <router-link to="/about">About</router-link>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <router-view />
</template>

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
  width: 80px;
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
</style>
