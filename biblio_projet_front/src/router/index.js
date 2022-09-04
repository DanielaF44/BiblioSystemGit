import { createRouter, createWebHistory } from "vue-router";
import WelcomeView from "../views/WelcomeView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: WelcomeView,
  },
  {
    path: "/welcome",
    name: "welcome",
    component: () => import("../views/HomeView.vue"),
  },
  {
    path: "/prets",
    name: "prets",
    component: () => import("../views/PretView.vue"),
  },
  {
    path: "/connexion",
    name: "connexion",
    component: () => import("../views/ConnexionView.vue"),
  },
  {
    path: "/inscription",
    name: "inscription",
    component: () => import("../views/InscriptionView.vue"),
  },
  {
    path: "/infos-user",
    name: "infos-user",
    component: () => import("../views/InfoUserView.vue"),
  },
  {
    path: "/update-user",
    name: "update-user",
    component: () => import("../views/UpdateInfoView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
