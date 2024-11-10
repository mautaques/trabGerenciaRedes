import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/login.vue";
import index from "../views/index.vue";
import cadastro from "@/views/cadastro.vue";
import associacao from "@/views/associacao.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: HomeView,
    },
    {
      path: "/home",
      name: "home",
      component: index,
      children: [
        {
          path: "cadastro",
          name: "cadastro",
          component: cadastro,
        },
        {
          path: "associacao",
          name: "associacao",
          component: associacao,
        },
      ],
    },
  ],
});

export default router;
