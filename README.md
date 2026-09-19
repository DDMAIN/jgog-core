# JGOGCore

<img src="https://flagcdn.com/w20/us.png" width="22" alt="English"> / <img src="https://flagcdn.com/w20/gb.png" width="22" alt="English">

![State](https://img.shields.io/badge/State-in%20development-yellow)
![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-3.6.3-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-green)
![H2](https://img.shields.io/badge/H2%20database-2.4.240-blue)
![picocli](https://img.shields.io/badge/picocli-4.7.7-green)
![Lombok](https://img.shields.io/badge/Lombok-1.18.46-pink)

**JGOG Core** is a library for managing [GOG](https://www.gog.com) games, 
implemented as a standalone command-line application (CLI) built on Spring 
Boot, with subcommands based on picocli. Data persistence is handled using 
JPA on an H2 database stored on disk, so there is no need to install any
external database.

- Standalone CLI application (without a web environment) built with Spring Boot 4.1.1 and Java 21
- Modular subcommand architecture using picocli 4.7.7 (picocli-spring-boot-starter)
  H2 persistence in file mode (jdbc:h2:file) — data persists after the application is closed
- Automatic help/usage display when run without arguments

## 🛠️ Technology Stack

| Component         | Version   | Description                      |
|-------------------|-----------|----------------------------------|
| **JDK**           | 21 (LTS)  | Base runtime                     |
| **Maven**         | 3.9.6     | Dependency and build management  |
| **Lombok**        | 1.18.46   | Boilerplate reduction            |
| **SLF4J+Logback** | 2.0.x     | Structured logging               |

## 🚀 Quick Start

### Prerequisites

- **JDK 21** or higher
- **Maven 3.9.6** or higher

### Compilation

mvn clean package

### Ejecution

java -jar jgog-core-_version_.jar _comando_

### Comandos

JGOGCore currently has 2 commands:

| Command  | Description                                    |
|----------|------------------------------------------------|
| `status` | Displays the current status of the application |
| `init`   | Initializes the project configuration          |



---

<img src="https://flagcdn.com/w20/es.png" width="22" alt="Español">

![Estado](https://img.shields.io/badge/Estado-en%20desarrollo-yellow)
![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-3.6.3-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-green)
![H2](https://img.shields.io/badge/H2%20database-2.4.240-blue)
![picocli](https://img.shields.io/badge/picocli-4.7.7-green)
![Lombok](https://img.shields.io/badge/Lombok-1.18.46-pink)

**JGOG Core** es una librería para la gestión de juegos de 
[GOG](https://www.gog.com), implementada como una aplicación de línea de 
comandos (CLI) autónoma sobre Spring Boot, con subcomandos basados en 
picocli. La persistencia se realiza con JPA sobre una base de datos H2 en 
disco, por lo que no es necesario instalar ninguna base de datos externa.

---

## 📦 Características

- Aplicación CLI autónoma (sin entorno web) construida con Spring Boot 4.1.1 y Java 21
- Arquitectura modular de subcomandos con picocli 4.7.7 (picocli-spring-boot-starter)
  Persistencia H2 en modo fichero (jdbc:h2:file) — los datos sobreviven al cierre de la aplicación
- Muestra de ayuda/uso automática al ejecutarse sin argumentos

---

## 🛠️ Stack Tecnológico

| Componente      | Versión    | Descripción                         |
|-----------------|------------|-------------------------------------|
| **JDK**         | 21 (LTS)   | Runtime base                        |
| **Maven**       | 3.9.6      | Gestión de dependencias y builds    |
| **Lombok**      | 1.18.46    | Reducción de boilerplate            |
| **SLF4J+Logback** | 2.0.x    | Logging estructurado                |

---

## 🚀 Inicio Rápido

### Prerrequisitos

- **JDK 21** o superior
- **Maven 3.9.6** o superior

### Compilación

mvn clean package

### Ejecución

java -jar jgog-core-_version_.jar _comando_

### Comandos

Actualmente JGOGCore cuenta con 2 comandos:

| Comando  | Descripción                               |
|----------|-------------------------------------------|
| `status` | Muestra el estado actual de la aplicación |
| `init`   | Inicializa la configuración del proyecto  |