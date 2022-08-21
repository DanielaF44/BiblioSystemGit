//Ce fichier nous permet d'utiliser une seule instance de axios qui va gérer les requêtes API de plusieurs vue et composants

import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  getLivres() {
    return apiClient.get("/livresbycriteria");
  },

  getGenres() {
    return apiClient.get("/genres");
  },

  getLangues() {
    return apiClient.get("/langues");
  },

  getResults(sGenre, sTitre, sAuteur, sLangue) {
    let sUrl = "/livresbycriteria?";
    //input verification
    if (sTitre && sTitre.length > 0 && sTitre != "null") {
      sUrl = sUrl + "&titre=" + sTitre;
    }
    if (sGenre && sGenre.length > 0 && sGenre != "null") {
      sUrl = sUrl + "&genre=" + sGenre;
    }
    if (sAuteur && sAuteur.length > 0 && sAuteur != "null") {
      sUrl = sUrl + "&auteur=" + sAuteur;
    }
    if (sLangue && sLangue.length > 0 && sLangue != "null") {
      sUrl = sUrl + "&langue=" + sLangue;
    }

    return apiClient.get(sUrl);
  },

  getPrets() {
    // TODO : use the real user id when we have it
    let sUrl = "/prets?utilisateurId=1";
    return apiClient.get(sUrl);
  },
};
