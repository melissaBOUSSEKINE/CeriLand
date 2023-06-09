# CeriLand
C'est une plateforme de location libre où chaque utilisateur peut louer ses propres objets inutilisés ou demander la location d'objets qui l'intéressent sur la plateforme, en attendant le **validation** ou éventuellement la **refuse** du propriétaire de l'objet.

Sur la page d'accueil de la plateforme, vous pouvez parcourir plus de **10 000 objets différents**. En cliquant sur la cart détaillée d'un objet, vous pouvez consulter les messages d'autres utilisateurs concernant cet objet. Cependant, il est nécessaire de vous connecter à votre compte pour laisser un message. Sinon, vous pourrez seulement voir les messages existants pour l'objet, mais sans afficher les auteurs des messages ni pouvoir laisser de nouveaux messages.

En cliquant sur le bouton "**Ajouter dans le panier**" dans la cart détaillée de chaque objet sur la page d'accueil, vous pouvez ajouter l'objet à votre panier. Ensuite, tous les utilisateurs peuvent accéder à leur profil en cliquant sur le bouton "profil" pour consulter « **Panier** »,  « **Commandes envoyées** » (parcourir les objets pour lesquels ils ont envoyé une demande de location aux propriétaires) , et « **Commandes validées** » (parcourir tous les objets en cours de location (ceux pour lesquels la demande de location a été acceptée par le propriétaire de l'objet)) . Si l'utilisateur a le statut de "**owner**", deux boutons supplémentaires seront disponibles sur le page profil « **Mes objets** » ( pour consulter tous les objets qu'il a mis en location ) et « **Demande reçu** » ( toutes les demandes de location reçues pour les objets concernés ) .


## Base de donnée & API

Base de donnée de notre application est installé sur PostgreSQL, et l'api est en python.

![La relation de base de données](https://github.com/melissaBOUSSEKINE/CeriLand/blob/main/Back-end/relation_BD.png)

Il faut créer un fichier ```CeriLand/Back-end/.env```

contenu de fichier .env
```
DB_HOST=##nom de host##
DB_DATABASE=##nom de base##
DB_USER=##user identifiant##
DB_PASSWORD=##password##
```

Dans le base, copier le contenu du fichier <a href="[https://example.com](https://github.com/melissaBOUSSEKINE/CeriLand/blob/main/Back-end/db.sql)">https://github.com/melissaBOUSSEKINE/CeriLand/blob/main/Back-end/db.sql</a> dans le base **pgsql** pour créer les relation de tableaux.

Lancer le fichier ```CeriLand/Back-end/ceriLandApi.py``` pour lancer l'api de l'application.

  - Pour initialiser et insérer les données par défaut dans le base, lancer la route ```/initAllData```
  - Pour supprimer tous les données dans le base, lancer la route ```/resetAllData```
