export default (salaId) => `
  {
    equipamentosBySalaLivres(salaId: ${salaId}) {
        computadores {
            id
            tipoEquipamento {
                id
                nomeEquipamento
            }
        }
        switchs {
            id
            tipoEquipamento {
                id
                nomeEquipamento
            }
        }
    }
  }
`;
