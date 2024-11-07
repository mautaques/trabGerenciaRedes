CREATE TABLE equipamento (
    id INT PRIMARY KEY NOT NULL,
    id_tipo_equipamento INT NOT NULL,
    id_sala INT NOT NULL,
    mac_adress VARCHAR(50),
    equipamento_autorizada TINYINT(1),
    
    FOREIGN KEY (id_tipo_equipamento) REFERENCES tipo_equipamento(id),
    FOREIGN KEY (id_sala) REFERENCES sala(id)
);