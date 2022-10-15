import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Toaster from "@meforma/vue-toaster";
import { createHead } from "@vueuse/head";

const head = createHead();

createApp(App)
  .use(store)
  .use(router)
  .use(Toaster, { position: "bottom-right" })
  .use(head)
  .mount("#app");
