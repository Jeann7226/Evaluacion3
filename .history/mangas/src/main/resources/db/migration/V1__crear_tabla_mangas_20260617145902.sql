
CREATE TABLE demografia (
    id_demografia INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
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
    CONSTRAINT fk_manga_demografia FOREIGN KEY (id_demografia) REFERENCES demografia(id_demografia)
);


INSERT INTO demografia (nombre) VALUES ('Shonen'), ('Seinen'), ('Shojo');

INSERT INTO MANGA (nombre, precio, sinopsis, id_autor, id_genero, id_demografia, id_origen) 
VALUES ('Naruto Vol 1', 15000, 'Un ninja rubio que busca ser Hokage', 1, 1, 1, 1);