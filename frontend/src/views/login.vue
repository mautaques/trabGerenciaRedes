<template>
  <div class="flex justify-center items-center w-full h-full">
    <div style="border: 1px solid black; width: 300px; height: 350px">
      <div class="p-10 flex flex-col justify-between h-full items-center">
        <div class="flex flex-col">
          <div class="w-full mb-5">
            <p>Login</p>
            <input
              class="w-full"
              type="email"
              style="border: 2px solid black; border-radius: 4px !important"
              v-model="login"
            />
          </div>
          <div class="w-full">
            <p>Senha</p>
            <input
              class="w-full"
              type="password"
              style="border: 2px solid black; border-radius: 4px !important"
              v-model="senha"
            />
          </div>
        </div>

        <button
          class="w-1/2"
          style="border: 1px solid black"
          @click="fazerLogin"
        >
          Entrar
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { makeRequest, makeGraphQLRequest } from "@/service/main";
import { mapActions, mapState } from "vuex";
import MeuModal from "@/components/modal.vue";

export default {
  components: {
    MeuModal,
  },
  data() {
    return {
      payload: {
        login: null,
        senha: null,
      },
      showModal: false,
    };
  },

  name: "Login",

  async created() {
    this.fazerLogin();
  },

  computed: {
    ...mapState("storeModule", ["salaSelected", "user"]),
  },

  methods: {
    ...mapActions("storeModule", ["fetchUsuario", "fetchEquipamentoSala"]),

    async fazerLogin() {
      await this.fetchUsuario({ login: "teste2", senha: "teste2" });

      if (this.salaSelected) {
        await this.fetchEquipamentoSala(this.salaSelected.id);

        this.user?.usuario?.usuarioChefe
          ? this.$router.replace({ name: "cadastro" })
          : this.$router.replace({ name: "associacao" });
      }
    },
  },
};
</script>
