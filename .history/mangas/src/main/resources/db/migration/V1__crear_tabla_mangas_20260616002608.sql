CREATE TABLE mangas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    demografia VARCHAR(50) NOT NULL,
    inventario INT NOT NULL
);
INSERT INTO mangas (nombre, demografia, inventario) VALUES ('One Piece', 'Shonen', 100);
INSERT INTO mangas (nombre, demografia, inventario) VALUES ('Naruto', 'Sh