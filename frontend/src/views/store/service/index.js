import { makeRequest, makeGraphQLRequest } from "@/service/main";

import UsuarioQuery from "./queries/queryUsuarioLogin";
import EquipamentoQuery from "./queries/queryEquipamentoSala";
import queryEquipamentosPorta from "./queries/queryEquipamentosPorta";
import queryEquipamentoLivreBySala from "./queries/queryEquipamentoLivreBySala";

export const fetchUsuarioService = (login, senha) =>
  makeGraphQLRequest(UsuarioQuery(login, senha));

export const fetchEquipamentoService = (salaId) =>
  makeGraphQLRequest(EquipamentoQuery(salaId));

export const fetchEquipamentoPortaService = (equipamentoId) =>
  makeGraphQLRequest(queryEquipamentosPorta(equipamentoId));

export const fetchEquipamentoLivresBySalaService = (salaId) =>
  makeGraphQLRequest(queryEquipamentoLivreBySala(salaId));

export const createEquipamentoService = (payload) =>
  makeRequest("POST", "/equipamento", payload);

export const createEquipamentoPortaService = (payload) =>
  makeRequest("POST", "/equipamento_porta", payload);

export const updateEquipamentoPortaService = (payload) =>
  makeRequest("PATCH", "/equipamento_porta", payload);

export const deleteEquipamentoPortaService = (id) =>
  makeRequest("DELETE", `/equipamento_porta/${id}`, null);
