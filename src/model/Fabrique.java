package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'instancier des objects inacessible en dehors du paquet model
 * @author Clément
 */
public class Fabrique {
     /**
     * Appelle le constructeur par défaut d'une recette
     * @return une recette depuis son interface IRecette
     */
    public static IRecette creerRecette(){
        return new Recette();
    }
    
    /**
     * Appelle le constructeur basique prenant uniquement le nom
     * @param nom le nom de la recette
     * @return une recette
     */
    public static IRecette creerRecette(String nom){
        return new Recette(nom, "");
    }
    
    /**
     * Appelle le constructeur prenant 2 attributs
     * @param nom le nom de la recette
     * @param recette la recette à suivre pour la réaliser
     * @return une recette
     */
    public static IRecette creerRecette(String nom, String recette){
        return new Recette(nom, recette);
    }
    
    /**
     * Appelle le constructeur prenant toutes les valeurs nécessaire permettant de créer une recette
     * @param nom son nom
     * @param recette la recette
     * @param minutes la durée de réalisation
     * @param difficulte la difficulté de réalisation
     * @param prix le prix
     * @return une recette
     */
    public static IRecette creerRecette(String nom, String recette, int minutes, Difficulte difficulte, Budget prix){
        return new Recette(nom,recette, minutes, difficulte, prix);
    }
    
    /**
     * Appelle le constructeur prenant une liste d'ingédients en plus
     * @param nom son nom
     * @param recette la recette
     * @param minutes la durée de réalisation
     * @param difficulte la difficulté de réalisation
     * @param prix le prix
     * @param ingredients la liste d'ingrédients
     * @return une recette
     */
    public static IRecette creerRecette(String nom, String recette, int minutes, Difficulte difficulte, Budget prix, List<IIngredient> ingredients){
        return new Recette(nom, recette, minutes, difficulte, prix, ingredients);
    }
    
    /**
     * Appelle le constructeur d'un ingrédient
     * @param nom le nom
     * @param quantite la quantite
     * @param unite l'unite de la quantite
     * @return un ingrédient depuis son interface IIngredient
     */
    public static IIngredient creerIngredient(String nom, int quantite, Unite unite){
        return new Ingredient(nom, quantite, unite);
    }
    
    /**
     * Permet de construire un nouvelle ingrédient
     * @param ingredient depuis un ingrédient
     * @return un clone de l'ingrédient
     */
    public static IIngredient creerIIngredient(IIngredient ingredient){
        return (IIngredient)ingredient.clone();
    }
    
    /**
     * Clone une liste d'ingrédient
     * @param ingredients la liste à cloner
     * @return la nouvelle liste avec les clones des ingredients
     */
    public static List<IIngredient> cloneListeIngredients(List<IIngredient> ingredients){
        List<IIngredient> copy = new ArrayList<>();
        ingredients.forEach(i -> copy.add(Fabrique.creerIIngredient(i)));
        return copy;
    }
    
}
