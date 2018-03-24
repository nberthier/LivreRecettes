/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cb946032
 */
public class Fabrique {
    
    public static IRecette creerRecette(){
        return new Recette();
    }
    
    public static IRecette creerRecette(String nom, String recette){
        return new Recette(nom, recette);
    }
    
    public static IRecette creerRecette(String nom, String recette, int minutes, Difficulte difficulte, Budget prix){
        return new Recette(nom,recette, minutes, difficulte, prix);
    }
    
    public static IIngredient creerIngredient(String nom, int quatite, Unite unite){
        return new Ingredient(nom, quatite, unite);
    }
    
    public static IIngredient creerIIngredient(IIngredient ingredient){
        return (IIngredient)ingredient.clone();
    }
    
    public static List<IIngredient> cloneListeIngredients(List<IIngredient> ingredients){
        List<IIngredient> copy = new ArrayList<>();
        for(IIngredient i : ingredients)
            copy.add(Fabrique.creerIIngredient(i));
        return copy;
    }
}
