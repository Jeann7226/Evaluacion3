
CREATE TABLE autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE genero (
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE demografia (
    id_demografia INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE origen (
    id_origen INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE MANGA (
    id_manga INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    precio INT NOT NULL,
    sinopsis VARCHAR(60) NOT NULL,
    
    -- Columnas para las llaves foráneas
    id_autor INT,
    id_genero INT,
    id_demografia INT,
    id_origen INT,
    
    CONSTRAINT fk_manga_autor FOREIGN KEY (id_autor) REFERENCES autor(id_autor),
    CONSTRAINT fk_manga_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero),
    CONSTRAINT fk_manga_demografia FOREIGN KEY (id_demografia) REFERENCES demografia(id_demografia),
    CONSTRAINT fk_manga_origen FOREIGN KEY (id_origen) REFERENCES origen(id_origen)
);

INSERT INTO autor (nombre) VALUES ('Masashi Kishimoto');
INSERT INTO genero (nombre) VALUES ('Accion y Aventura');
INSERT INTO demografia (nombre) VALUES ('Shonen');
INSERT INTO origen (nombre) VALUES ('Japon');

INSERT INTO MANGA (nombre, precio, sinopsis, id_autor, id_genero, id_demografia, id_origen) 
VALUES ('Naruto Vol 1', 15000, 'Un ninja rubio que busca ser Hokage', 1, 1, 1, 1);