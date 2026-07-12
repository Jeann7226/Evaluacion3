<<<<<<< HEAD
Proyecto: Tienda Manga - Módulo Autores

Descripción
Este proyecto es una API REST desarrollada como evaluación. Su objetivo es gestionar la información de autores, géneros temáticos y países de origen de los mangas, implementando patrones de arquitectura de software.
=======
Proyecto: Tienda Manga - Repositorio Principal

Descripción
Este proyecto es un ecosistema de APIs REST desarrollado como evaluación integral. Su objetivo es gestionar de manera descentralizada el inventario de mangas, los datos de autores y la integración de usuarios externos, implementando una arquitectura distribuida basada en microservicios y patrones de diseño avanzados.
>>>>>>> main

Tecnologías Utilizadas
 Java
 Spring Boot 4.1.0
<<<<<<< HEAD
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
=======
 Spring Cloud Eureka (Service Discovery)
 MySQL
 JPA / Hibernate
 Flyway Migrations
 Maven

Características Principales
El sistema se divide en los siguientes componentes y responsabilidades:

Módulo Core & Inventario (Jean): Gestión de las entidades "manga", "demografía" e "inventario", incluyendo lógica de negocio y consultas personalizadas.
Módulo Autores & Persistencia: Administración de "autores", "géneros temáticos" y "países de origen" utilizando Flyway para la migración automatizada de datos base.
Módulo Usuarios e Integraciones: Consumo y mapeo de datos mediante DTOs especializados (`UsuarioExternoDTO`) para conectar con servicios de terceros.
Servidor de Descubrimiento (Eureka): Centralización y registro de los microservicios para permitir la comunicación interna del ecosistema.

Configuración y Ejecución
Requisitos: Tener instalado Java, Maven y un servidor local de MySQL.
Bases de Datos: Crear manualmente la base de datos `db_mangas`.
 La base de datos `db_tienda_manga` se creará de forma automática al iniciar el módulo de autores.
Ejecución del Ecosistema: Levantar los servicios desde sus respectivas carpetas en la terminal utilizando Maven:
>>>>>>> main
  ```cmd
  mvnw spring-boot:run
