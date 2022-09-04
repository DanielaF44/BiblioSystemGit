import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";
class AuthService {
  login(user) {
    console.log(user);
    return axios
      .post(API_URL + "signin", {
        username: user.username,
        password: user.password,
      })
      .then((response) => {
        if (response.data.accessToken) {
          console.log(response.data);
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem("user");
    console.log(localStorage);
  }
  register(user) {
    console.log(user);
    return axios.post(API_URL + "signup", {
      nom: user.nom,
      prenom: user.prenom,
      email: user.email,
      password: user.password,
    });
  }
  update(user){
    return axios.post(API_URL + "profile", {
        nom: user.nom,
        prenom: user.prenom,
        email: user.email,
        password: user.password,
    }).then((response) => {
      if (response) {
        console.log(response);
        let userToUpdate = JSON.parse(localStorage.getItem("user"));
        userToUpdate.email = user.email;
        userToUpdate.nom = user.nom;
        userToUpdate.prenom = user.prenom;
        localStorage.setItem("user", JSON.stringify(userToUpdate));
      }
    });
  }
}
export default new AuthService();
