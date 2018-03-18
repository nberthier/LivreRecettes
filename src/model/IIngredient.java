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
public interface IIngredient { // extends IngredientBis {
    
    public String getNom();    
    public int getQuantite();
    public Unite getUnite();
}
