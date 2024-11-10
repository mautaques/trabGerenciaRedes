CREATE TABLE equipamento (
    id INT PRIMARY KEY NOT NULL  auto_increment,
    tipo_equipamento_id INT NOT NULL,
    sala_id INT NOT NULL,
    mac_adress VARCHAR(50),
    equipamento_autorizada TINYINT(1),
    
    FOREIGN KEY (tipo_equipamento_id) REFERENCES tipo_equipamento(id),
    FOREIGN KEY (sala_id) REFERENCES sala(id)
);