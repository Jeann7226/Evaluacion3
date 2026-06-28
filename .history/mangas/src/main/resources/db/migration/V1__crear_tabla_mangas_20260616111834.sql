-- =================================================================
-- 1. TABLAS INDEPENDIENTES (No dependen de nadie para existir)
-- =================================================================

CREATE TABLE demografia (
    id_demografia INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    estado VARCHAR(50) NOT NULL
);

-- =================================================================
-- 2. TABLA PRINCIPAL (Manga depende de Demografía e Inventario)
-- =================================================================

CREATE TABLE manga (
    id_manga INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    precio INT NOT NULL,
    sinopsis VARCHAR(60) NOT NULL,
    
    -- Relaciones internas (Tus tablas)
    id_demografia INT,
    id_inventario INT,
    
    -- Relaciones externas (Los IDs que vienen del microservicio de Mateo)
    id_autor INT NOT NULL,
    id_genero INT NOT NULL,
    id_origen INT NOT NULL,
    
    -- Creación de las llaves foráneas para tus tablas
    CONSTRAINT fk_manga_demografia FOREIGN KEY (id_demografia) REFERENCES demografia(id_demografia),
    CONSTRAINT fk_manga_inventario FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario)
);

-- =================================================================
-- 3. DATOS DE PRUEBA (Para que la base de datos no nazca vacía)
-- =================================================================

INSERT INTO demografia (nombre) VALUES ('Shonen'), ('Seinen'), ('Shojo');

INSERT INTO inventario (cantidad, estado) VALUES (50, 'Disponible'), (0, 'Agotado');

INSERT INTO manga (nombre, precio, sinopsis, id_demografia, id_inventario, id_autor, id_genero, id_origen) 
VALUES ('Naruto Vol 1', 15000, 'Un ninja rubio que busca ser Hokage', 1, 1, 1, 1, 1);