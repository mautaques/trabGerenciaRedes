export default (salaId) => `
  {
    equipamentosBySala(salaId: ${salaId}) {
        id
        tipoEquipamento {
            id
            nomeEquipamento
        }
        sala {
            id
            numeroSala
        }
    }
  }
`;
