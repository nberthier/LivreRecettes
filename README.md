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
- models : le package avec les classes modèles, les classes de bases livre, recette et ingrédient par exemple.
- data : dans ce package sont les classes pour la gestion de la persistance.
- launchers :  le package permettant d'executer le code, il contient les différentes classes ayant une méthode `main`, une pour lancer l'application en mode console et l'autre lançant l'application en mode graphique.
- views : contient les vues écrites en FXML.
- controllers : le package des contrôleurs associés à chacune des vues.

Digramme de classes du modèle :
-------------------------------

![Diagramme de classe du Modèle](https://raw.githubusercontent.com/BOISSARD/LivreRecettes/master/UMLDiagram/exports/Class_diagram-Model-001.png)
