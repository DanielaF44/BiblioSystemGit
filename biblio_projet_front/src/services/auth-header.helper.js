//Pour appeler un service une fois authentifi√©
//il faut passer le token dans le header de la requete
export default function authHeader() {
  let user = JSON.parse(localStorage.getItem("user"));
  if (user && user.accessToken) {
    return { Authorization: "Bearer " + user.accessToken };
  } else {
    return {};
  }
}
