//Ce fichier nous permet d'utiliser une seule instance de axios qui va gérer les requêtes API de plusieurs vue et composants

import axios from "axios";
import authHeader from "./auth-header.helper";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

const API_URL = "http://localhost:8080";

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
    let sUrl = "/prets?utilisateurId=2";
    console.log(JSON.parse(localStorage.getItem("user")));
    return axios.get(API_URL + sUrl, { headers: authHeader() });
  },

  postPrets(sId) {
    let sUrl = "/prets?pretId=" + sId;
    return apiClient.post(sUrl);
  },

  getDispos(sIsbn) {
    let sUrl = "/disponibilite?isbn=" + sIsbn;
    return apiClient.get(sUrl);
  },

  //Stats
  getLivresPretes(iOrdreTri) {
    let sUrl = "/dashboard/livrespretes?ordretri=";
    let iparam = 0;
    //input sanitizing
    if (iOrdreTri > 0) {
      iparam = 1;
    }
    sUrl = sUrl + iparam;
    return apiClient.get(sUrl);
  },

  getAuteursLus(iOrdreTri) {
    let sUrl = "/dashboard/auteurslus?ordretri=";
    let iparam = 0;
    //input sanitizing
    if (iOrdreTri > 0) {
      iparam = 1;
    }
    sUrl = sUrl + iparam;
    return apiClient.get(sUrl);
  },
};
