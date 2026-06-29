Proyecto: Tienda Manga - Módulo Usuarios e Integraciones

Descripción
Este proyecto es una API REST desarrollada como evaluación. Su objetivo es gestionar la integración de usuarios y el consumo de información de mangas desde servicios externos, implementando patrones de arquitectura de software.

Tecnologías Utilizadas
 Java
 Spring Boot 4.1.0
 MySQL
 JPA / Hibernate
 Maven

## Características Principales
* **Arquitectura:** Implementación de los patrones Controller, Service y DTO para el mapeo y desacoplamiento de los datos externos.
Gestión de Datos:
  Creación de controladores y servicios enfocados en la transferencia de información de usuarios del sistema.
  Implementación de DTOs especializados (`UsuarioExternoDTO` y `MangaExternoDTO`) para asegurar la correcta recepción de datos desde APIs o microservicios externos.
Persistencia y Conectividad: Configuración del entorno para el intercambio de datos estructurados y persistencia relacional en el ecosistema de la tienda.

Configuración y Ejecución
Requisitos: Tener instalado Java y MySQL.
Base de Datos:* Asegúrate de tener configurada la base de datos correspondiente en tu servidor MySQL según las propiedades del entorno.
Ejecución: Puedes ejecutar el proyecto utilizando Maven desde la terminal dentro de la carpeta `autores`:
  ```cmd
  mvnw spring-boot:run
