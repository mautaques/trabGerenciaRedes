CREATE TABLE usuario (
    id INT PRIMARY KEY NOT NULL,
    nome VARCHAR(200) NOT NULL,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    usuario_chefe TINYINT(1)
);