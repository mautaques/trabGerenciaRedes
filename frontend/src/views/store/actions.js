import types from "./types";
import {
  fetchUsuarioService,
  fetchEquipamentoService,
  createEquipamentoService,
  fetchEquipamentoPortaService,
  fetchEquipamentoLivresBySalaService,
  createEquipamentoPortaService,
  updateEquipamentoPortaService,
} from "./service";

const { GENERIC_MUTATION } = types;

const setUser = ({ commit }, value) =>
  commit(GENERIC_MUTATION, {
    property: "user",
    value,
  });

const fetchUsuario = async ({ dispatch }, { login, senha }) => {
  const { usuarioByLoginAndSenha } = await fetchUsuarioService(login, senha);

  dispatch("setUser", usuarioByLoginAndSenha);
};

const fetchEquipamentoSala = async ({ commit, state }, salaId) => {
  const { user } = state;

  const { equipamentosBySala } = await fetchEquipamentoService(
    salaId == null ? user?.equipamento?.sala?.id : salaId
  );

  commit(GENERIC_MUTATION, {
    property: "equipamentos",
    value: equipamentosBySala,
  });
};

const fetchEquipamentoPorta = async ({ commit, state }) => {
  const { user } = state;
  const { equipamentosPortas } = await fetchEquipamentoPortaService(
    user.equipamento.id
  );

  commit(GENERIC_MUTATION, {
    property: "equipamentosPortas",
    value: equipamentosPortas,
  });
};

const fetchEquipamentoLivresBySala = async ({ commit, state }) => {
  const { user } = state;
  const { equipamentosBySalaLivres } =
    await fetchEquipamentoLivresBySalaService(user?.equipamento?.sala?.id);

  commit(GENERIC_MUTATION, {
    property: "equipamentoslivres",
    value: equipamentosBySalaLivres,
  });
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
};
