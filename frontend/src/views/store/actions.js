import types from "./types";
import {
  fetchUsuarioService,
  fetchEquipamentoService,
  createEquipamentoService,
  fetchEquipamentoPortaService,
  fetchEquipamentoLivresBySalaService,
  createEquipamentoPortaService,
  updateEquipamentoPortaService,
  fetchSalasService,
} from "./service";

const { GENERIC_MUTATION } = types;

const setUser = ({ commit }, value) =>
  commit(GENERIC_MUTATION, {
    property: "user",
    value,
  });

const fetchUsuario = async ({ commit, dispatch, state }, { login, senha }) => {
  const { usuarioByLoginAndSenha } = await fetchUsuarioService(login, senha);
  dispatch("setSalaSelected", usuarioByLoginAndSenha?.equipamento?.sala);
  await dispatch("fetchSalas");
  const { salas } = state;
  if (!usuarioByLoginAndSenha?.equipamento) {
    dispatch("setSalaSelected", salas[0]);
    commit(GENERIC_MUTATION, {
      property: "isAdmin",
      value: true,
    });
  }
  dispatch("setUser", usuarioByLoginAndSenha);
};

const fetchEquipamentoSala = async ({ commit, state }, salaId) => {
  const { salaSelected } = state;

  if (salaSelected || salaId) {
    const { equipamentosBySala } = await fetchEquipamentoService(
      salaId == null ? salaSelected?.id : salaId
    );

    commit(GENERIC_MUTATION, {
      property: "equipamentos",
      value: equipamentosBySala,
    });
  }
};

const setSalaSelected = ({ commit, dispatch }, value) => {
  commit(GENERIC_MUTATION, {
    property: "salaSelected",
    value,
  });

  dispatch("fetchEquipamentoSala");
  dispatch("fetchEquipamentoLivresBySala");
};

const fetchEquipamentoPorta = async ({ commit, state }) => {
  const { salaSelected } = state;
  if (salaSelected) {
    const { equipamentosPortas } = await fetchEquipamentoPortaService(
      salaSelected?.id
    );

    commit(GENERIC_MUTATION, {
      property: "equipamentosPortas",
      value: equipamentosPortas,
    });
  }
};

const fetchSalas = async ({ commit }) => {
  const { salas } = await fetchSalasService();

  commit(GENERIC_MUTATION, {
    property: "salas",
    value: salas,
  });
};

const fetchEquipamentoLivresBySala = async ({ commit, state }) => {
  const { salaSelected } = state;
  if (salaSelected) {
    const { equipamentosBySalaLivres } =
      await fetchEquipamentoLivresBySalaService(salaSelected?.id);

    commit(GENERIC_MUTATION, {
      property: "equipamentoslivres",
      value: equipamentosBySalaLivres,
    });
  }
};

const createEquipamento = async ({ dispatch }, payload) => {
  await createEquipamentoService(payload);

  dispatch("fetchEquipamentoSala", null);
};

const createOrUpdateEquipamentoporta = async ({ dispatch }, payload) => {
  if (payload.id) {
    await updateEquipamentoPortaService(payload);
  } else {
    await createEquipamentoPortaService(payload);
  }

  dispatch("fetchEquipamentoPorta", null);
};

export default {
  setUser,
  fetchUsuario,
  fetchEquipamentoSala,
  fetchEquipamentoLivresBySala,
  fetchEquipamentoPorta,
  createEquipamento,
  createOrUpdateEquipamentoporta,
  fetchSalas,
  setSalaSelected,
};
