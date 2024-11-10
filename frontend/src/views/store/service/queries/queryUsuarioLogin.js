export default (login, senha) => `
  {
    usuarioByLoginAndSenha(login: "${login}", senha: "${senha}") {
      usuario {
        id
        nome
        login
        usuarioChefe
      }
      equipamento {
        id
        sala {
          id
          numeroSala
        }
        equipamentoAutorizada
      }
    }
  }
`;
