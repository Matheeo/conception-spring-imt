# Projet Spring avec JPA

Ce projet est une application Spring utilisant JPA (Java Persistence API).

## Prérequis

- Java 21
- Docker

## Instructions pour lancer le projet

1. **Se positionner dans le répertoire du projet**

   Naviguez dans le répertoire suivant :
   ```bash
   cd TournamentMaster3000
   ```

2. **Nettoyage et installation Maven**

   Exécutez la commande suivante :
   ```bash
   mvn clean install
   ```

3. **Lancement des conteneurs Docker**

   Lancez les conteneurs nécessaires avec la commande :
   ```bash
   docker-compose up
   ```

4. **Démarrer l'application**

   Une fois les étapes précédentes terminées, démarrez l'application Spring Boot.

## Accéder aux points d'API

Les différents points d'API sont accessibles via l'URL suivante :

[<img src="https://upload.wikimedia.org/wikipedia/commons/a/ab/Swagger-logo.png" alt="Swagger UI" width="100">](http://localhost:8080/swagger-ui/index.html)
