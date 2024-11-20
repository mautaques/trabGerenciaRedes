export default (salaId) => `
  {
    equipamentosPortas(salaId: ${salaId}) {
        id
        equipamentoOrigem {
            id
            tipoEquipamento {
                id
                nomeEquipamento
            }
        }
        equipamentoDestino {
            id
            tipoEquipamento {
                id
                nomeEquipamento
            }
        }
        sala {
            id
            numeroSala
        }
        bloqueio
        fimBloqueio
        numeroPorta
    }
  }
`;
