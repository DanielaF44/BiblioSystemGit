<template>
  <section class="info-user">
    <ul>
      <li>
        <p class="label">Nom:</p>
        <p>{{ currentUser.nom }}</p>
      </li>
      <li>
        <p class="label">Prénom:</p>
        <p>{{ currentUser.prenom }}</p>
      </li>
      <li>
        <p class="label">E-mail:</p>
        <p>{{ currentUser.email }}</p>
      </li>
    </ul>
    <button class="button">
      <router-link to="update-user"> Modifier le compte</router-link>
    </button>
  </section>
</template>
<script>
export default {
  name: "InfoUserView",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
};
</script>
<style scoped>
.info-user {
  padding: 20px;
  width: 95%;
  box-shadow: 2px 2px 20px #343434;
  border-radius: 15px;
  margin-bottom: 18px;
  padding-right: 5%;
  background-color: #4b9cda;
  margin-left: 20px;
}

.label {
  padding: 20px;
  font-weight: bold;
  width: 200px;
  text-align: end;
}

p {
  display: inline-block;
  text-align: start;
  width: 200px;
}

ul {
  list-style: none;
  padding-left: 50px;
}
</style>
