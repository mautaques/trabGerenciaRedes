CREATE TABLE equipamento_porta (
    id INT PRIMARY KEY NOT NULL,
    id_equipamento INT NOT NULL,
    id_sala INT NOT NULL,
    bloqueio DATE,
    fim_bloqueio DATE,

    FOREIGN KEY (id_equipamento) REFERENCES equipamento(id),
    FOREIGN KEY (id_sala) REFERENCES sala(id)
);