CREATE TABLE autores(
    idAutor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL
);

CREATE TABLE generos(
    idGenero INT AUTO_INCREMENT PRIMARY KEY,
    generoTematico VARCHAR(100) NOT NULL
);

CREATE TABLE origenes(
    idOrigen INT AUTO_INCREMENT PRIMARY KEY,
    paisOrigen VARCHAR(100) NOT NULL
);

INSERT INTO autores (nombre, apellido) VALUES ('Hirohiko', 'Araki');
INSERT INTO generos (generoTematico) VALUES ('Seinen');
INSERT INTO origenes (paisOrigen) VALUES ('Japon');