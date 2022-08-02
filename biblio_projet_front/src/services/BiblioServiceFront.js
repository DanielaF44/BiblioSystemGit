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

  getResults(sGenre, sTitre) {
    let sUrl = "/livresbycriteria?";
    //input verification
    if (sTitre && sTitre.length > 0 && sTitre != "null") {
      sUrl = sUrl + "&titre=" + sTitre;
    }
    if (sGenre && sGenre.length > 0 && sGenre != "null") {
      sUrl = sUrl + "&genre=" + sGenre;
    }
    return apiClient.get(sUrl);
  },
};
