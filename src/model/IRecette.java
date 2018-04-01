/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Interface d'acces à une recette
 * @author Clément
 */
public interface IRecette {
    /**
     * Méthode ajout d'un ingrédient
     * @param ingredient l'ingrédient
     */
    public void ajouterIngredient(IIngredient ingredient);
    /**
     * Méthode ajout de plusieurs ingrédients
     * @param ingredients liste d'ingrédients
     */
    public void ajouterIngredients(List<IIngredient> ingredients);
    
    /**
     * Méthode suppression d'un ingrédient
     * @param ingredient l'ingrédient à supprimer
     */
    public void supprimerIngredient(IIngredient ingredient);
    /**
     * Méthode suppression d'un ingrédient
     * @param index la position dans la liste
     */
    public void supprimerIngredient(int index);

    /**
     * Accesseur du nom
     * @return String le nom
     */
    public String getNom();
    /**
     * Accesseur de la propriété nom
     * @return la propriété nom
     */
    public StringProperty nomProperty();
    
    /**
     * Mutateur de la duree
     * @param duree la valeur
     */
    public void setDuree(int duree);
    /**
     * Accesseur de la duree
     * @return int la duree
     */
    public int getDuree();
    
    /**
     * Mutateur de la difficulte
     * @param difficulte une constante de l'énumération Difficulte
     */
    public void setDifficulte(Difficulte difficulte);
    /**
     * Accesseur de la difficulte
     * @return une constante de l'énumération Difficulte
     */
    public Difficulte getDifficulte();
    
    /**
     * Mutateur du prix
     * @param prix une constante de l'énumération Budget
     */
    public void setPrix(Budget prix);
    /**
     * Accesseur du prix
     * @return une constante de l'énumération de Budget
     */
    public Budget getPrix();
    
    /**
     * Accesseur de la recette
     * @return String la recette
     */
    public String getRecette();
    /**
     * Accesseur de la propriété recette
     * @return la propriété de la recette
     */
    public StringProperty recetteProperty();
    
    /**
     * Accesseur des ingrédients
     * @return la liste des ingrédients
     */
    public List<IIngredient> getIngredients(); 
    /**
     * Accesseur des ingrédients
     * @return liste observable des ingrédients
     */
    public ObservableList<IIngredient> getIngredientsObservable(); 
    /**
     * Acceseur du nombre d'ingrédients de la liste
     * @return int le nombre d'ingrédients
     */
    public int nbIngredients();
    /**
     * Accesseur de la propriété du nombre d'ingrédients
     * @return la propriété du nombre d'ingrédient
     */
    public StringProperty nbIngredientsProperty();
}
