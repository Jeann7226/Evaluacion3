# Evaluacion3
Proyecto: Tienda Manga
Descripción
Este proyecto es una API REST desarrollada como evaluación. Su objetivo es gestionar el inventario y la información de mangas, implementando patrones de arquitectura de software.

Tecnologías Utilizadas
Java 21

Spring Boot 4.1.0

MySQL

JPA / Hibernate

Maven

Características Principales
Arquitectura: Implementación de los patrones Repository y Service para separar la lógica de negocio del acceso a datos.

Gestión de Datos:

La parte de Jean fue la creación de repositorios y servicios para entidades como "manga", "demografía" e "inventario".


Implementación de consultas personalizadas (custom queries) en los repositorios.

Persistencia: Uso de scripts SQL para la creación inicial de tablas y configuración de base de datos relacional.

Configuración y Ejecución
Requisitos: Tener instalado Java 21 y MySQL.

Base de Datos: Crea manualmente la base de datos db_mangas en tu servidor MySQL.

Ejecución:
Puedes ejecutar el proyecto utilizando Maven:

Documentación de API
Una vez ejecutada la aplicación, puedes acceder a la documentación interactiva en:
http://localhost:8081/doc/swagger-ui/index.html
http://localhost:8081/api/v1/mangas
http://localhost:8080/api/v1/mangas
http://localhost:8761/Eureka
