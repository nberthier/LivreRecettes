/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Interface d'acces à un ingrédient,
 * extends Cloneable
 * @author Clément
 */
public interface IIngredient extends Cloneable { // extends IngredientBis {
    
    /**
     * Accesseur du nom
     * @return String son nom
     */
    public String getNom(); 
    /**
     * Mutateur du nom
     * @param nom son nouveau nom
     */
    public void setNom(String nom);
    /**
     * Accesseur de la quantite
     * @return int la valeur de la quantite
     */
    public int getQuantite();
    /**
     * Mutateur de la quantite
     * @param quantite sa nouvelle valeur
     */
    public void setQuantite(int quantite);   
    /**
     * Accesseur de l'unite
     * @return la constante de l'énumération Unite 
     */
    public Unite getUnite();
    /**
     * Mutateur de l'unite
     * @param unite une constante
     */
    public void setUnite(Unite unite);
    /**
     * Fonction de Cloneable à redéfinir
     * @return le nouvelle ingrédient
     */
    public Object clone();
}
