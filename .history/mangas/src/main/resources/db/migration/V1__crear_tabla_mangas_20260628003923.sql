
CREATE TABLE demografia (
    id_demografia INT AUTO_INCREMENT PRIMARY KEY,
    nombreDemografia VARCHAR(60) NOT NULL
);

CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    stock INT NOT NULL,
    bodega VARCHAR(50)
);


CREATE TABLE MANGA (
    id_manga INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    precio INT NOT NULL,
    sinopsis VARCHAR(60) NOT NULL,
    
   
    id_autor INT,
    id_genero INT,
    id_origen INT,

    id_demografia INT,
    id_inventario INT,
    
    CONSTRAINT fk_manga_demografia FOREIGN KEY (id_demografia) REFERENCES demografia(id_demografia),
    CONSTRAINT fk_manga_inventario FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario)
);


INSERT INTO demografia (nombre) VALUES ('Shonen'), ('Seinen'), ('Shojo');

INSERT INTO inventario (stock, bodega) VALUES (50, 'Bodega Central'), (20, 'Vitrina Principal');

INSERT INTO MANGA (nombre, precio, sinopsis, id_autor, id_genero, id_demografia, id_inventario, id_origen) 
VALUES ('Naruto Vol 1', 15000, 'Un ninja rubio que busca ser Hokage', 1, 1, 1, 1, 1);