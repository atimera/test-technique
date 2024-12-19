# test-technique

- Backend : Java 21 / Spring Boot 3.4.0 / base de données postgres / Docker
- Frontend : Angular 18

-----------------------------

# A faire :
Le but est de pouvoir manipuler des positions GPS (latitude et longitude).

API:
L'API devra pouvoir permettre :
- De créer une position GPS ;
- De lister les positions GPS ;
- De supprimer une position GPS ;
- De dire si une position GPS est à plus ou moins 10 km d’une autre position GPS ;



L’interface devra permettre :

- De saisir une position GPS ;
- De visualiser les positions GPS en base de données ;
- De supprimer une position GPS ;
- De choisir deux positions GPS et d’afficher si elles sont éloignées de plus ou moins 10km.

## Besoin de :
    - Docker / Java 21 / NodeJs version 20.12.2 minimum et npm 10.8.3 minimum
    

## Pour lancer le projet
    - Faire un clone du projet
    - Se mettre à la racine du dossier "test-technique"
    - Lancer la commande : docker-compose up -d

    == Backend
    - Se mettre dans le dossier backend
    - Lancer la commande : ./mvnw spring-boot:run
    - => Lancement du backend sur le port 8080
    
    == Frontend
    - Se mettre dans le dossier frontend
    - Lancer la commande : npm install
    - npm start
    => Lancement du frontend sur le port 4200