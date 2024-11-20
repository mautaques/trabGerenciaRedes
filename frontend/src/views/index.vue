<template>
  <div class="w-full h-full">
    <div class="flex" style="font-size: 18px; background: #e6e6fa">
      <div class="w-full flex-between flex items-center p-5 gap-4">
        <div
          v-if="isAdmin"
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
        <button
          v-if="isAdmin"
          class="p-2"
          style="border: 1px solid black; width: fit-content"
          @click="showModal = true"
        >
          Adicionar nova sala
        </button>
      </div>
      <div class="flex items-center p-4">
        <meu-select
          label="Sala atual"
          v-model="selectSala"
          :disabled="!isAdmin"
          :options="options"
        />
      </div>
    </div>

    <meu-modal
      :title="'Cadastrar sala'"
      :isVisible="showModal"
      @close="showModal = false"
      @salvar="createSala"
    >
      <div class="flex gap-4">
        <div style="width: 40px">
          <meu-input label="Sala" v-model="numeroSala"></meu-input>
        </div>
      </div>
    </meu-modal>

    <RouterView />
  </div>
</template>

<script>
import { makeRequest, makeGraphQLRequest } from "@/service/main";
import { createSalaService } from "@/views/store/service";
import { RouterView } from "vue-router";
import { mapActions, mapState } from "vuex";
import MeuSelect from "@/components/select.vue";
import MeuModal from "@/components/modal.vue";
import MeuInput from "@/components/input.vue";
export default {
  components: {
    MeuSelect,
    MeuModal,
    MeuInput,
  },

  data() {
    return {
      allSalas: [],
      selectSala: null,
      showModal: false,
      numeroSala: null,
    };
  },

  name: "Login",

  async created() {
    if (!this.user) {
      this.$router.replace({ name: "login" });
    }

    this.allSalas = this.salas
      .map((item) => ({
        value: item.id,
        text: item?.numeroSala?.toString(),
      }))
      .sort((a, b) => parseInt(a.text) - parseInt(b.text));

    this.selectSala = this.salaSelected?.id;
  },

  watch: {
    selectSala() {
      const sala = this.salas.find((item) => item.id === this.selectSala);

      this.setSalaSelected(sala);
    },
  },

  computed: {
    ...mapState("storeModule", ["salas", "user", "salaSelected", "isAdmin"]),

    options() {
      return this.allSalas;
    },
  },

  methods: {
    ...mapActions("storeModule", ["fetchSalas", "setSalaSelected"]),

    async createSala() {
      await createSalaService({ numeroSala: this.numeroSala });

      await this.fetchSalas();
      this.numeroSala = null;
      this.showModal = false;
      this.allSalas = this.salas.map((item) => ({
        value: item.id,
        text: item?.numeroSala?.toString(),
      }));
    },
  },
};
</script>
