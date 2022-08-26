import { createRouter, createWebHistory } from "vue-router";
import WelcomeView from "../views/WelcomeView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: WelcomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
