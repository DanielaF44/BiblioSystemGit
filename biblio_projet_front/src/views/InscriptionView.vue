<template>
  <section class="Inscription">
    <form @submit="checkForm">
      <p>Les champs avec * sont obligatoires.</p>
      <div class="entree">
        <label for="nom">Nom *</label>
        <div class="input-div">
          <input v-model="user.nom" type="text" placeholder="Doe" required />
        </div>
      </div>
      <div class="entree">
        <label for="prenom">Prénom *</label>
        <div class="input-div">
          <input
            v-model="user.prenom"
            type="text"
            placeholder="John"
            required
          />
        </div>
      </div>
      <div class="entree">
        <label for="email">E-mail *</label>
        <div class="input-div">
          <input
            v-model="user.email"
            type="text"
            placeholder="john.doe@email.com"
            required
          />
        </div>
      </div>
      <div class="entree">
        <label for="mdp">Mot de passe *</label>
        <div class="input-div">
          <input v-model="user.password" type="password" required />
        </div>
      </div>
      <div class="entree">
        <label for="mdp">Confirmation du mot de passe *</label>
        <div class="input-div">
          <input v-model="passwordConf" type="password" required />
        </div>
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
import { useHead } from "@vueuse/head";
export default {
  name: "ConnexionView",
  //ajout balises meta à l'entête de la page html
  setup() {
    useHead({
      title: "S'inscrire",
      meta: [
        {
          name: "description",
          content: "S'inscrire au service de la bibliothèque",
        },
      ],
    });
  },
  data() {
    return {
      user: { nom: null, prenom: null, email: null, password: null },
      passwordConf: null,
      errors: [],
    };
  },
  methods: {
    //methode qui transmet au service d'authentification les données saisies pour l'inscription et vérifie la validité de ces entrées
    handleRegister(user, passwordConf) {
      this.errors = [];
      if (!user.nom || user.nom.length < 2) {
        this.errors.push("Nom invalide");
      }

      if (!user.prenom || user.prenom.length < 2) {
        this.errors.push("Prenom invalide");
      }

      if (!user.email || !user.email.includes("@", ".")) {
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
        for (const i of this.errors) {
          this.$toast.show(i, { type: "error", duration: 4000 });
        }
        return;
      }

      this.$store
        .dispatch("auth/register", user)
        .then(() => {
          this.$toast.show(
            "Votre compte a été créé avec succès. <Br> Vous allez être redirigé vers la page de connexion.",
            { type: "success", duration: 4000 }
          );
          setTimeout(() => {
            this.$router.push({ path: "/connexion" });
          }, 4000);
        })
        .catch(() => {
          this.$toast.show(
            "Une erreur est survenue. <Br> Veuillez tenter à nouveau ou nous contacter directement",
            { type: "error", duration: 4000 }
          );
        });
    },
  },
};
</script>
<style scoped>
.Inscription {
  padding: 20px;
  width: 90%;
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

.entree {
  margin: 15px;
  display: flex;
  justify-content: center;
}

.buttonIns {
  width: 350px;
}

@media only screen and (max-width: 768px) {
  input {
    min-width: 80%;
  }

  .entree {
    flex-direction: column;
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
