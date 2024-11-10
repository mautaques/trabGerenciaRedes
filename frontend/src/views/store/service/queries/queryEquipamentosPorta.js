export default (equipamentoId) => `
  {
    equipamentosPortas(equipamentoId: ${equipamentoId}) {
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
    }
  }
`;
