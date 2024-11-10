<template>
  <div class="w-full h-full">
    <div
      class="w-full flex p-5 gap-4"
      style="font-size: 18px; background: #e6e6fa"
    >
      <div
        class="cursor-pointer p-2"
        style="background: #b19cd9"
        @click="$router.replace({ name: 'cadastro' })"
      >
        Equipamentos
      </div>
      <div
        class="cursor-pointer p-2"
        style="background: #b19cd9"
        @click="$router.replace({ name: 'associacao' })"
      >
        Associação
      </div>
    </div>
    <RouterView />
  </div>
</template>

<script>
import { makeRequest, makeGraphQLRequest } from "@/service/main";
import { RouterView } from "vue-router";
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      payload: {
        login: null,
        senha: null,
      },
    };
  },

  name: "Login",

  async created() {
    if (!this.user) {
      this.$router.replace({ name: "login" });
    }
  },
  computed: {
    ...mapState("storeModule", ["equipamentos", "user"]),
  },

  methods: {},
};
</script>
