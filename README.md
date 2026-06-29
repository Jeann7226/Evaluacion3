Proyecto: Tienda Manga - Módulo Autores

Descripción
Este proyecto es una API REST desarrollada como evaluación. Su objetivo es gestionar la información de autores, géneros temáticos y países de origen de los mangas, implementando patrones de arquitectura de software.

Tecnologías Utilizadas
 Java
 Spring Boot 4.1.0
 MySQL
 JPA / Hibernate
 Flyway
 Maven

Características Principales
Arquitectura: Implementación de los patrones Controller, Service, Repository y DTO para separar la lógica de negocio del acceso a datos.
* **Gestión de Datos:**
  * Creación de controladores, servicios y repositorios para las entidades "Autor", "Genero" y "Origen".
  * Implementación de consultas personalizadas (custom queries) con mapeo directo a objetos DTO en los repositorios.
* **Persistencia:** Uso de migraciones automatizadas con Flyway para la creación inicial de tablas (`autores`, `generos`, `origenes`) e inserción de datos base en la base de datos relacional.

Configuración y Ejecución
* **Requisitos:** Tener instalado Java y MySQL.
* **Base de Datos:** La base de datos `db_tienda_manga` se creará automáticamente en tu servidor MySQL al iniciar la aplicación.
Ejecución: Puedes ejecutar el proyecto utilizando Maven desde la terminal dentro de la carpeta `autores`:
  ```cmd
  mvnw spring-boot:run
