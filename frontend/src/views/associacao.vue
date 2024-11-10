<template>
  <div class="flex flex-col gap-8 p-10">
    <div class="flex items-end w-full gap-4" v-for="e in equipamentosPortas">
      <div style="width: 40px">
        <meu-input
          label="Sala"
          :disabled="true"
          v-model="e.sala.numeroSala"
        ></meu-input>
      </div>
      <div style="width: fit-content">
        <meu-input
          label="Equipamento de origem"
          :disabled="true"
          v-model="e.equipamentoOrigem.id"
        ></meu-input>
      </div>
      <div style="width: fit-content">
        <meu-input
          label="Equipamento de destino"
          :disabled="true"
          v-model="e.equipamentoDestino.id"
        ></meu-input>
      </div>
      <div>
        <meu-date
          label="Data limite do bloqueio"
          v-model="e.fimBloqueio"
          :disabled="true"
        />
      </div>
      <button
        class="p-2"
        style="border: 1px solid black; width: fit-content; height: 40px"
        @click="edit(e)"
      >
        Ediar
      </button>
      <button
        class="p-2"
        style="border: 1px solid black; width: fit-content; height: 40px"
        @click="excluir(e)"
      >
        Excluir
      </button>
    </div>
    <button
      class="p-2"
      style="border: 1px solid black; width: fit-content"
      @click="openModal"
    >
      Associar novo equipamento
    </button>

    <meu-modal
      :title="'Associar Equipamentos'"
      :isVisible="abrirModal"
      @close="clearVariaveis"
      @salvar="create"
    >
      <div class="flex gap-4">
        <div style="width: fit-content">
          <meu-select
            label="Equipamento de origem"
            v-model="selectOrigem"
            :disabled="selectOrigem === user.equipamento.id"
            :options="optionsSwitches"
          />
        </div>
        <meu-select
          label="EquipamentoDestino"
          v-model="selectDestino"
          :options="optionsComputadores"
        />
        <meu-date
          label="Escolha a Data e Hora"
          v-model="dataHora"
          :disabled="this.tipoDestino === 2 || destinoAtual"
        />
      </div>
    </meu-modal>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import MeuInput from "@/components/input.vue";
import MeuSelect from "@/components/select.vue";
import MeuModal from "@/components/modal.vue";
import MeuDate from "@/components/dateTime.vue";
import { cloneDeep } from "lodash";
import { deleteEquipamentoPortaService } from "./store/service";

export default {
  data() {
    return {
      id: null,
      abrirModal: false,
      componenteOrigem: null,
      componenteDestino: null,
      optionsComputadores: [],
      optionsSwitches: [],
      dataHora: null,
      selectOrigem: null,
      selectDestino: null,
      tipoDestino: null,
    };
  },

  components: {
    MeuInput,
    MeuSelect,
    MeuModal,
    MeuDate,
  },

  name: "Login",

  async created() {
    await this.fetchEquipamentoPorta();
  },

  computed: {
    ...mapState("storeModule", [
      "equipamentosPortas",
      "user",
      "equipamentoslivres",
    ]),

    destinoAtual() {
      return this.optionsComputadores.find(
        (item) =>
          item?.value === this.selectDestino && item?.tipoEquipamento === 2
      );
    },
  },

  methods: {
    ...mapActions("storeModule", [
      "fetchEquipamentoPorta",
      "fetchEquipamentoLivresBySala",
      "createOrUpdateEquipamentoporta",
    ]),

    async openModal() {
      await this.fetchEquipamentoLivresBySala();

      const computadores = cloneDeep(this.equipamentoslivres.computadores).map(
        (item) => ({
          value: item.id,
          text: `${item.id} - ${item.tipoEquipamento.nomeEquipamento}`,
          tipoEquipamento: item.tipoEquipamento.id,
        })
      );

      const switchs = cloneDeep(this.equipamentoslivres.switchs).map(
        (item) => ({
          value: item.id,
          text: `${item.id} - ${item.tipoEquipamento.nomeEquipamento}`,
          tipoEquipamento: 2,
        })
      );

      this.optionsComputadores = [...computadores, ...switchs];
      this.optionsSwitches = [...switchs];

      this.abrirModal = true;
    },

    create() {
      if (
        this.selectOrigem &&
        this.selectDestino &&
        this.selectDestino != this.selectOrigem
      ) {
        const payload = {
          id: this.id,
          equipamentoOrigemId: this.selectOrigem,
          equipamentoDestinoId: this.selectDestino,
          dataBloqueio: this.dataHora,
        };

        this.createOrUpdateEquipamentoporta(payload);

        this.clearVariaveis();
      }
    },

    edit(e) {
      this.id = e.id;
      this.selectDestino = e.equipamentoDestino.id;
      this.selectOrigem = e.equipamentoOrigem.id;
      this.dataHora = e.fimBloqueio;
      this.tipoDestino = e.equipamentoDestino.tipoEquipamento.id;
      this.openModal();
    },

    async excluir(e) {
      await deleteEquipamentoPortaService(e.id);

      await this.fetchEquipamentoPorta();
    },

    clearVariaveis() {
      this.selectDestino = null;
      this.selectOrigem = null;
      this.dataHora = null;
      this.id = null;
      this.tipoDestino = null;
      this.abrirModal = false;
    },
  },
};
</script>
