Livre de Recettes
=================

Explication du projet :
-----------------------

### Objectif du projet :

Ce projet a pour objectif de réaliser une application Java en utilisant les fonctionnalités de la POO (Programmation Orientée Objet) pour cela j'ai choisi de réaliser un livre de recettes.  
En effet un livre de recette permet d'appliquer la programmation objet.

Un livre de recettes est en fait un conteneur d'une liste non-exhaustive de recettes.  
Une recette est un élément composé d'un nom, des instructions pour la réaliser, d'une liste d'ingrédients, et d'autres informations.  
Un ingrédient quant à lui est un élément composé d'un nom, d'une quantité et d'une unité associée à la quantité.

### Architecture utilisée :

Pour réaliser ce projet j'ai construit mon programme en utilisant une architecture MVC pour Modèle-vue-contrôleur

Projet réalisé en JavaFX, c'est-à-dire que le model est réalisé en Java et la partie graphique est réalisée en FXML pour les vues et Java pour les contrôleurs.

Explication des packages :
--------------------------

Mon code est donc réparti suivant plusieurs répertoires :
- model : le package modèle avec les classes de bases livre, recette et ingrédient par exemple.
- data : dans ce package sont les classes pour la gestion de la persistance.
- test : le package permettant d'executer le code sous forme d'application console, pratique pour tester l'efficacité du code.
- launch : contient la classe main lançant l'application graphique.
- view : contient les vues écrites en FXML.
- controller : le package des contrôleurs associés à chacune des vues.

