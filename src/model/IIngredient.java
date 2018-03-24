/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Clément
 */
public interface IIngredient extends Cloneable { // extends IngredientBis {
    
    public String getNom(); 
    public void setNom(String nom);
    public int getQuantite();
    public void setQuantite(int quantite);   
    public Unite getUnite();
    public void setUnite(Unite unite);
    public Object clone();
}
