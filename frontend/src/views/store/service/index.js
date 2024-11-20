import { makeRequest, makeGraphQLRequest } from "@/service/main";

import UsuarioQuery from "./queries/queryUsuarioLogin";
import EquipamentoQuery from "./queries/queryEquipamentoSala";
import queryEquipamentosPorta from "./queries/queryEquipamentosPorta";
import queryEquipamentoLivreBySala from "./queries/queryEquipamentoLivreBySala";
import querySalas from "./queries/querySalas";

export const fetchUsuarioService = (login, senha) =>
  makeGraphQLRequest(UsuarioQuery(login, senha));

export const fetchEquipamentoService = (salaId) =>
  makeGraphQLRequest(EquipamentoQuery(salaId));

export const fetchEquipamentoPortaService = (salaId) =>
  makeGraphQLRequest(queryEquipamentosPorta(salaId));

export const fetchEquipamentoLivresBySalaService = (salaId) =>
  makeGraphQLRequest(queryEquipamentoLivreBySala(salaId));

export const fetchSalasService = () => makeGraphQLRequest(querySalas());

export const createEquipamentoService = (payload) =>
  makeRequest("POST", "/equipamento", payload);

export const createEquipamentoPortaService = (payload) =>
  makeRequest("POST", "/equipamento_porta", payload);

export const updateEquipamentoPortaService = (payload) =>
  makeRequest("PATCH", "/equipamento_porta", payload);

export const updateEquipamentoBloqueioService = (payload) =>
  makeRequest("PATCH", "/equipamento_porta/desabilitar", payload);

export const deleteEquipamentoPortaService = (id) =>
  makeRequest("DELETE", `/equipamento_porta/${id}`, null);

export const createSalaService = (payload) =>
  makeRequest("POST", "/sala", payload);
