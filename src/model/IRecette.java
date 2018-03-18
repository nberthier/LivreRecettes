/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cb946032
 */
public interface IRecette {// extends IngredientBis{
    
    public void ajouterIngredient(IIngredient ingredient);

    public String getNom();
    public StringProperty nomProperty();
    
    public void setDuree(int duree);
    public int getDuree();
    
    public void setDifficulte(Difficulte difficulte);
    public Difficulte getDifficulte();
    
    public void setPrix(Budget prix);
    public Budget getPrix();
    
    public String getRecette();
    public StringProperty recetteProperty();
    
    public List<IIngredient> getIngredients(); 
    
    public int nbIngredients();
    public StringProperty nbIngredientsProperty();
}
