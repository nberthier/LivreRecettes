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

Conception du modèle :
----------------------

La partie modèle de l'application est la partie qui gère les données et la logique en rapport avec les données. Sont, dans le modèle, les classes de base tels que la classe `Recette` ou la classe `Ingredient` par exemple, ainsi que toutes les classes nécessaire à la construction de ces classes ou leurs gestions telle que la gestion de la persistance des instances de ces classes.

### Diagramme de classes :

Voici donc le diagramme de classes de la partie Modèle de l'application, ce diagramme représente donc les classes de base pour que l'application fonctionne, mais ne montre pas les classes gérant la partie graphique de l'application.  
En effet sur ce diagramme sont réprésentées les classes du package `models` ainsi que les classes du package `data` (les classes héritant de l'interface `DataManager`).

![Diagramme de classe du Modèle](https://raw.githubusercontent.com/BOISSARD/LivreRecettes/master/UMLDiagram/exports/Class_diagram-Model-001.png)

### Conception :

Cette application à demander une certain quantité de réflexion et de recherche rien qu'au niveau de sa conception afin que l'application soit la plus "propre" possible au niveau code et notamment que celle-ci soit évolutive sans que tout le code ne se casse.  
Déjà la séparation avec la partie graphique doit être nette et logique.

#### Le Livre :

La classe `Livre` est la classe qui doit contenir la liste des recettes, c'est donc une classe importante dans la conception, c'est pourquoi c'est elle qui gère la partie modèle et qui en est l'entrée. Cette classe est la mise en place du patron de conception "Façade".  
En effet, c'est par cette classe que toute la partie modèle est gérée et par laquelle il faut passer pour la gestion des recettes, c'est à dire l'ajout/modification/suppresion d'une recette mais aussi la persistance (sauvegarde/chargement) des recettes.

#### Recettes et Ingrédients :

Ensuite viennent les classes les plus intéressantes, la classe `Recette` qui est l'élement important de l'application et la classe `Ingredient`, ces deux classes sont protégées afin que les classes extérieurs au sous-système ne puissent les manipuler directement mais doivent passer par la façade `Livre`.  
Le problème est que il est malgré tout nécessaire d'accéder à certain élément des classes notamment les "getter" pour cela chacune de ces deux classes implémentent respectivement l'interface `IRecette` et `IIngredient` afin de manipuler des recettes et des ingrédients protégés.  

#### Les énumérations :

Chacune de ces classes peut avoir un ou plusieurs attribut(s) correspondant à l'une des differentes Enumération. J'ai utilisé des énumérations afin de prédéfinir les différentes possibilité pour certain notion, la plus évidente étant l'énumération `Unite`.  
L'utilisation d'énumération est plus pratique après dans la partie graphique dans un formulaire, car il suffit de générer une combobox avec les différentes valeurs de l'énumération en question.

#### La fabrique :

La classe `Fabrique` permet d'instancier un objet d'une classe privée (ou "private") dans le sous-système (ici le package models), ainsi cette classe permet de gérer l'instanciation d'une classe protégée hors du sous-système. Cela correspond au patron de conception "Fabrique Simple".

#### La persistance (DataManager) :

L'interface `DataManager` permet de définir les classes gérant la persistance afin qu'elles puissent être utilisées pour la persistance des recettes.  
Cette conception correspond au patron de conception "Stratégie".  
Les classes permettant réellement de réaliser la persistance ce trouvent dans le package `data` telle que la classe `XMLDataManager` permettant la persistance au format XML. Ainsi avec cette conception il est possbible d'avoir autant de classe permettant la persistance que l'on souhaite, par exemple si l'on veut maintenant effectuer la persistance au format JSon il suffit de créer la classe avec le bon code et d'instancier la classe JSonDataManager plutôt que XMLDataManager ou autre. 

On peut voir qu'il y a une autre classe, nommée `StubDataManager`, cette classe correspond à une "persistance" en "dur" dans le code et permettait de voir si le code était fonctionnel avant de m'attaquer à une vrai classe de persistance, car dans ce cas si ça ne marche ça ne peut venir que d'un endroit la classe en rédaction.
