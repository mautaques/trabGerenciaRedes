<template>
  <div class="flex flex-col gap-8 p-10">
    <div class="flex w-full gap-4" v-for="e in equipamentos">
      <div style="width: 90px">
        <meu-input
          label="Equipamento"
          :disabled="true"
          v-model="e.id"
        ></meu-input>
      </div>
      <div style="width: 40px">
        <meu-input
          label="Sala"
          :disabled="true"
          v-model="e.sala.numeroSala"
        ></meu-input>
      </div>
      <div style="width: fit-content">
        <meu-input
          label="Tipo de equipamento"
          :disabled="true"
          v-model="e.tipoEquipamento.nomeEquipamento"
        ></meu-input>
      </div>
    </div>
    <button
      class="p-2"
      style="border: 1px solid black; width: fit-content"
      @click="showModal = true"
    >
      Adiconar novo equipamento
    </button>

    <meu-modal
      :title="'Cadastrar componente'"
      :isVisible="showModal"
      @close="showModal = false"
      @salvar="create"
    >
      <div class="flex gap-4">
        <div style="width: 40px">
          <meu-input
            label="Sala"
            :disabled="true"
            v-model="salaSelected.numeroSala"
          ></meu-input>
        </div>
        <meu-select
          label="Tipo de equipamento"
          :options="options"
          v-model="selectedOption"
        />
      </div>
    </meu-modal>
  </div>
</template>

<script>
import { makeRequest, makeGraphQLRequest } from "@/service/main";
import { mapActions, mapState } from "vuex";
import { cloneDeep } from "lodash";
import MeuInput from "@/components/input.vue";
import MeuSelect from "@/components/select.vue";
import MeuModal from "@/components/modal.vue";

export default {
  data() {
    return {
      equipamentosAtual: null,
      showModal: false,
      options: [
        { value: "1", text: "Computador" },
        { value: "2", text: "Switch" },
      ],
      selectedOption: null,
    };
  },

  name: "Login",

  components: {
    MeuInput,
    MeuSelect,
    MeuModal,
  },

  async created() {
    this.equipamentosAtual = cloneDeep(this.equipamentos);
  },

  computed: {
    ...mapState("storeModule", ["equipamentos", "salaSelected"]),
  },

  methods: {
    ...mapActions("storeModule", ["createEquipamento"]),
    create() {
      if (this.selectedOption) {
        this.createEquipamento({
          salaId: this.salaSelected.id,
          tipoEquipamentoId: this.selectedOption,
        });

        this.showModal = false;
      }
    },
  },
};
</script>
