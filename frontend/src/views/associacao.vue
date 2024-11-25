<template>
  <div class="flex flex-col gap-8 p-10">
    <div v-if="isAdmin">
      <div class="flex items-end w-full gap-4" v-for="e in equipamentosPortas">
        <div style="width: 40px">
          <meu-input
            label="Sala"
            :disabled="true"
            v-model="e.sala.numeroSala"
          ></meu-input>
        </div>
        <div style="width: 100px">
          <meu-input
            label="Equipamento de origem"
            :disabled="true"
            v-model="e.equipamentoOrigem.id"
          ></meu-input>
        </div>
        <div style="width: 100px">
          <meu-input
            label="Equipamento de destino"
            :disabled="true"
            v-model="e.equipamentoDestino.id"
          ></meu-input>
        </div>
        <div style="width: 50px">
          <meu-input
            label="Porta"
            :disabled="true"
            v-model="e.numeroPorta"
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
          Editar
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
        class="p-2 mt-5"
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
              :disabled="tipoOrigem === 1"
              :options="optionsSwitches"
            />
          </div>
          <meu-select
            label="EquipamentoDestino"
            v-model="selectDestino"
            :options="optionsComputadores"
          />
          <div style="width: 40px">
            <meu-input label="Porta" v-model="numeroPorta"></meu-input>
          </div>
        </div>
      </meu-modal>
    </div>
    <div v-else>
      <div
        class="flex items-end w-full gap-4"
        v-for="(e, index) in equipamentos"
      >
        <div style="width: 50px">
          <meu-input
            label="Porta"
            :disabled="true"
            v-model="e.numeroPorta"
          ></meu-input>
        </div>
        <div>
          <meu-date
            label="Data de termino do bloqueio"
            v-model="e.fimBloqueio"
            :disabled="true"
          />
        </div>

        <input
          class="mb-3"
          type="checkbox"
          :disabled="e.equipamentoDestino?.tipoEquipamento?.id === 2"
          :checked="e.check"
          @change="handleChange(index)"
        />
      </div>
      <div class="flex gap-4">
        <button
          class="p-2 mt-5"
          style="border: 1px solid black; width: fit-content"
          @click="openModalPortas"
        >
          Editar
        </button>
        <button
          class="p-2 mt-5"
          style="border: 1px solid black; width: fit-content"
          @click="deleteBloqueio"
        >
          Deletar bloqueio
        </button>
      </div>

      <meu-modal
        :title="'Desabilitar portas selecionadas'"
        :isVisible="modalPortas"
        @close="clearVariaveis"
        @salvar="createBloqueio"
      >
        <div class="flex gap-4">
          <meu-date label="Data limite do bloqueio" v-model="fimBloqueio" />
        </div>
      </meu-modal>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import MeuInput from "@/components/input.vue";
import MeuSelect from "@/components/select.vue";
import MeuModal from "@/components/modal.vue";
import MeuDate from "@/components/dateTime.vue";
import { cloneDeep } from "lodash";
import {
  deleteEquipamentoPortaService,
  updateEquipamentoBloqueioService,
  deteleBloqueioService,
} from "./store/service";

export default {
  data() {
    return {
      id: null,
      abrirModal: false,
      modalPortas: false,
      componenteOrigem: null,
      componenteDestino: null,
      optionsComputadores: [],
      optionsSwitches: [],
      dataHora: null,
      numeroPorta: null,
      selectOrigem: null,
      selectDestino: null,
      tipoDestino: null,
      tipoOrigem: null,
      equipamentos: null,
      fimBloqueio: null,
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
    this.initiEquipamentos();
  },

  computed: {
    ...mapState("storeModule", [
      "equipamentosPortas",
      "user",
      "equipamentoslivres",
      "isAdmin",
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
          numeroPorta: this.numeroPorta,
        };

        this.createOrUpdateEquipamentoporta(payload);

        this.clearVariaveis();
      }
    },

    async initiEquipamentos() {
      await this.fetchEquipamentoPorta();
      this.equipamentos = cloneDeep(this.equipamentosPortas);
    },

    openModalPortas() {
      if (this.equipamentos.find((item) => item.check)) {
        this.modalPortas = true;
      }
    },

    edit(e) {
      this.id = e.id;
      this.selectDestino = e.equipamentoDestino.id;
      this.selectOrigem = e.equipamentoOrigem.id;
      this.dataHora = e.fimBloqueio;
      this.tipoDestino = e.equipamentoDestino.tipoEquipamento.id;
      this.tipoOrigem = e.equipamentoOrigem.tipoEquipamento.id;
      this.numeroPorta = e.numeroPorta;
      this.openModal();
    },

    handleChange(i) {
      this.equipamentos[i].check = !this.equipamentos[i]?.check;
    },

    async excluir(e) {
      await deleteEquipamentoPortaService(e.id);

      await this.fetchEquipamentoPorta();
    },

    async createBloqueio() {
      const payload = {
        ids: this.equipamentos
          .filter((item) => item.check)
          .map((item) => item.id),
        dataBloqueio: this.fimBloqueio,
      };

      await updateEquipamentoBloqueioService(payload);
      await this.initiEquipamentos();

      this.modalPortas = false;
    },

    async deleteBloqueio() {
      const payload = {
        ids: this.equipamentos
          .filter((item) => item.check)
          .map((item) => item.id),
      };

      await deteleBloqueioService(payload);
      await this.initiEquipamentos();

      this.modalPortas = false;
    },

    clearVariaveis() {
      this.selectDestino = null;
      this.selectOrigem = null;
      this.dataHora = null;
      this.id = null;
      this.modalPortas = false;
      this.tipoDestino = null;
      this.abrirModal = false;
      this.tipoOrigem = null;
      this.numeroPorta = null;
    },
  },
};
</script>
