CREATE TABLE equipamento_porta (
    id INT PRIMARY KEY NOT NULL auto_increment,
    equipamento_origem_id INT NOT NULL,
    equipamento_destino_id INT NOT NULL,
    sala_id INT NOT NULL,
    bloqueio DATETIME,
    fim_bloqueio DATETIME,

    FOREIGN KEY (equipamento_origem_id) REFERENCES equipamento(id),
    FOREIGN KEY (equipamento_destino_id) REFERENCES equipamento(id),
    FOREIGN KEY (sala_id) REFERENCES sala(id)
);