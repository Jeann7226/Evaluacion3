CREATE TABLE usuario (
   id_usuario INT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR(60) NOT NULL,
   correo VARCHAR(150) NOT NULL,
   contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE pago (
   id_pago INT AUTO_INCREMENT PRIMARY KEY,
   id_usuario INT NOT NULL,
   monto INT(8) NOT NULL,
   metodo_pago VARCHAR(60) NOT NULL,
   fecha_transaccion DATETIME NOT NULL,

   FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE resena (
   id_resena INT AUTO_INCREMENT PRIMARY KEY,
   id_usuario INT NOT NULL,
   calificacion INT(1) NOT NULL,
   comentario VARCHAR(255) NOT NULL,

   FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE carrito (
   id_carrito INT AUTO_INCREMENT PRIMARY KEY,
   id_usuario INT NOT NULL,
   cantidad INT NOT NULL,
   fecha_agregado DATETIME NOT NULL,

   FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

INSERT INTO usuario (nombre, correo, contrasena) VALUES ('Juan Pérez', 'juan@gmail.com', 'clave123');
INSERT INTO pago (id_usuario, monto, metodo_pago, fecha_transaccion) VALUES (1, 50000, 'BancoEstado', '2024-06-01 10:00:00');
INSERT INTO resena (id_usuario, calificacion, comentario) VALUES (1, 5, 'Excelente servicio');
INSERT INTO carrito (id_usuario, cantidad, fecha_agregado) VALUES (1, 3, '2024-06-01 10:00:00');
