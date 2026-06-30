  # Sistema de Comercio Exterior — IMPEXP



  ---

  ##  Tabla de Contenidos

  - [Arquitectura]
  - [Microservicios]
  - [Tecnologías]
  - [Requisitos Previos]
  - [Instalación y Ejecución]
  - [Estructura del Proyecto]

  ---

  ## Arquitectura

  El sistema está compuesto por microservicios independientes que se comunican a través de un **API Gateway**. La autenticación se maneja mediante **JWT (JSON Web Tokens)**.

  ```
  Cliente
    │
    ▼
  ┌─────────────┐
  │   Gateway   │  ← Spring Cloud Gateway (puerto único de entrada)
  └──────┬──────┘
        │
    ┌────┴─────────────────────────────────────────┐
    │                                              │
    ▼              ▼              ▼                ▼
  service-auth  service_usuario  service_camiones  service_cargas
    (JWT)
    
    ▼                   ▼                   ▼
  service_asignacion  service_gestioncargas  service_seguimiento

    ▼
  service_gestionusuarios

    ▼
  login  (frontend/UI de autenticación)
  ```

  ---

  ## Microservicios

  | Módulo | Descripción | Java | Spring Boot | Puerto sugerido |
  |---|---|---|---|---|
  | `gateway` | Punto de entrada único, enrutamiento | 21 | 3.5.14 | 8080 |
  | `auth_impx` (service-auth) | Autenticación y generación de JWT | 17 | 3.2.5 | 8081 |
  | `login` | Interfaz/servicio de inicio de sesión | 21 | 3.5.15 | 8082 |
  | `service_usuario` | Gestión de usuarios | 21 | 3.5.14 | 8083 |
  | `service_gestionusuarios` | Administración avanzada de usuarios | 21 | 3.5.14 | 8084 |
  | `service_camiones` | Gestión de flota de camiones | 21 | 3.5.15 | 8085 |
  | `service_cargas` | Registro y control de cargas | 21 | 3.5.14 | 8086 |
  | `service_gestioncargas` | Gestión avanzada de cargas | 21 | 3.5.14 | 8087 |
  | `service_asignacion` | Asignación de cargas a camiones | 17 | 3.2.6 | 8088 |
  | `service_seguimiento` | Seguimiento de operaciones | 17 | 3.5.15 | 8089 |

  ---

  ## Tecnologías

  ### Core
  - **Java 17 / 21**
  - **Spring Boot 3.2.x — 3.5.x**
  - **Spring Cloud 2025.0.2** (Gateway)
  - **Maven**

  ### Persistencia
  - **Spring Data JPA** — ORM
  - **MySQL** — Base de datos relacional
  - **Jakarta Persistence API 3.1**

  ### Seguridad
  - **Spring Security**
  - **JWT** — `jjwt-api`, `jjwt-impl`, `jjwt-jackson` v0.11.5

  ### Frontend / Vistas
  - **Thymeleaf** — Motor de plantillas HTML
  - **Spring Boot Validation** — Validación de formularios

  ### Utilidades
  - **Lombok** — Reducción de boilerplate
  - **Spring Boot DevTools** — Recarga en caliente (desarrollo)
  - **Spring WebFlux** (service_gestionusuarios)

  ---

  ## Requisitos Previos

  Antes de ejecutar el proyecto, asegúrate de tener instalado:

  - **Java 17** o superior (algunos módulos requieren Java 21)
  - **Maven 3.8+**
  - **MySQL 8.0+**
  - **Git**

  ---

  ## Instalación y Ejecución

  ### 1. Clonar el repositorio

  git clone https://github.com/Sebbvw24/FullStack1.git
  cd FullStack1
  ```

  ### 2. Configurar la base de datos

  Crear las bases de datos necesarias en MySQL:

  ```sql
  CREATE DATABASE db_auth;
  CREATE DATABASE db_usuarios;
  CREATE DATABASE db_camiones;
  CREATE DATABASE db_cargas;
  CREATE DATABASE db_asignacion;
  CREATE DATABASE db_seguimiento;
  ```

  ### 3. Configurar `application.properties` de cada servicio

  En cada módulo, editar el archivo `src/main/resources/application.properties`:

  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/nombre_db
  spring.datasource.username=root
  spring.datasource.password=tu_password
  spring.jpa.hibernate.ddl-auto=update
  server.port=808X
  ```

  ### 4. Compilar y ejecutar cada microservicio

  Dentro de cada carpeta de módulo:

  ```bash
  cd auth_impx
  mvn spring-boot:run
  ```

  Repetir para cada módulo. Se recomienda iniciar en este orden:

  1. `auth_impx`
  2. `service_usuario`
  3. `service_camiones`
  4. `service_cargas`
  5. `service_asignacion`
  6. `service_gestioncargas`
  7. `service_gestionusuarios`
  8. `service_seguimiento`
  9. `login`
  10. `gateway` (último)

  ---

  ## Estructura del Proyecto

  ```
  impexp-sistema/
  ├── auth_impx/              # Microservicio de autenticación JWT
  │   └── src/
  │       └── main/java/com/impexp/
  │           ├── model/
  │           ├── repository/
  │           ├── service/
  │           └── controller/
  ├── gateway/                # API Gateway (Spring Cloud)
  ├── login/                  # Módulo de login
  ├── service_asignacion/     # Asignación de cargas
  ├── service_camiones/       # Flota de camiones
  ├── service_cargas/         # Gestión de cargas
  ├── service_gestioncargas/  # Administración avanzada de cargas
  ├── service_gestionusuarios/# Administración de usuarios
  ├── service_seguimiento/    # Seguimiento de operaciones
  └── service_usuario/        # Datos de usuarios
  ```

  Cada microservicio sigue la arquitectura estándar de capas:

  ```
  controller/   ← Expone los endpoints REST
  service/      ← Lógica de negocio
  repository/   ← Acceso a datos (Spring Data JPA)
  model/        ← Entidades JPA
  ```

  ---

  ## Seguridad

  El módulo `auth_impx` genera tokens JWT que deben incluirse en el header de cada petición a los demás servicios:

  ```
  Authorization: Bearer <token>
  ```

  El `gateway` actúa como intermediario y valida o redirige las peticiones según las rutas configuradas.

  ---

  ##  Notas

  - Los módulos con Thymeleaf (`service_camiones`, `service_cargas`, `service_gestioncargas`, `service_gestionusuarios`, `service_usuario`) incluyen vistas web además de la API REST.
  - `service_seguimiento` usa directamente `jakarta.persistence-api` sin el starter de Spring Boot.
  - `service_gestionusuarios` incluye **WebFlux** para soporte reactivo.
