# Ardeotis Platform - Backend

## Description
Ardeotis Platform est une application backend développée avec Spring Boot permettant de gérer :
- Authentification JWT
- Gestion des utilisateurs
- Logique métier modulaire
- Sécurisation des endpoints

Le projet suit une architecture claire et maintenable basée sur une séparation des responsabilités.

---

## Architecture du projet

com.example.ardeotis_platform

- config        : configuration (Security, JWT, OpenAPI)
- controller    : endpoints REST
- service       : logique métier
- repository    : accès base de données
- model         : entités JPA
- dto
    - request     : objets d’entrée API
    - response    : objets de sortie API
- exception     : gestion des erreurs
- mapper        : conversion DTO ↔ Entity

---

## Technologies utilisées

- Java 17
- Spring Boot 3
- Spring Security
- PostgreSQL
- JWT
- Maven

---

## Installation

### Prérequis

- Java 17
- Maven
- PostgreSQL

---

### Configuration

Modifier le fichier application.properties :

spring.datasource.url=jdbc:postgresql://localhost:5432/ardeotis_db  
spring.datasource.username=postgres  
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true

---

### Lancer le projet

mvn spring-boot:run


---

## Sécurité

- Password encodé avec BCrypt
- Authentification stateless (JWT)
- Routes publiques : /auth/**
- Routes sécurisées : toutes les autres

---

## Base de données

- Hibernate gère la création des tables
- Entité principale : User
- Mot de passe sécurisé (hash BCrypt)



