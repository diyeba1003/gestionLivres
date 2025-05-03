# gestion de livres - projet Edacy 
Ce projet est une application web pour la gestion de livres, composé d'un backend en **Java Spring Boot avec Jwt** et d'un **Frontend Angular**.

## technos utilisées:
- Backend : Java, spring boot , spring security, Jwt , JPA, MySQL

- Frontend : Angular 16, Bootstrap 
- Outils : Postman, Git, github 

## Structure 

gestionLivres/
│
├── frontend/                 
│   
│
├── backend/                   


 ## Prérequis:
 - java 17 +
 - Node.js, NPM  + Angular CLI 
 - Maven

 ## lancement du backend:
git clone https://github.com/diyeba1003/gestionLivres.git

 - cd gestionLivres/backend
 - mvn clean
 - mvn install
 - mvn spring-boot:run

 - L'API sera accessible sur : http://localhost:8080

 ## lancement du frontend:
 git clone https://github.com/diyeba1003/gestionLivres.git

   

 - cd gestionLivres/frontend
 - npm install
 - npm start 

 - l'application sera accessible sur : http://localhost:4200

 ## Fonctionalités:
 - Connexion avec JWT
 - CRUD des livres 
 - interface 

 ## Remarque: 
 authentification via JWT pensez à utiliser le token dans le header pour accéder aux routes.

