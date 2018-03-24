/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cb946032
 */
class Recette implements IRecette{
    
    /**
     * Nom de la recette
     */
    private String nom;
    public String getNom(){ return nom; }
    protected void setNom(String nom){ 
        this.nom = nom; 
        setNomP(this.getNom());
    }
    /**
     * Propriété de nom
     */
    private final StringProperty nomP = new SimpleStringProperty();
    public String getNomP(){ return this.nomP.get(); }
    protected void setNomP(String nom) { this.nomP.set(nom); }
    public StringProperty nomProperty() {return this.nomP; }
    
    /**
     * Réalisation de la recette
     */
    private String recette;
    public String getRecette(){ return recette; }
    protected void setRecette(String recette) { 
        this.recette = recette; 
        setRecetteP(this.getRecette());
    }
    /**
     * Propriété de la réalisation de la recette
     */
    private final StringProperty recetteP = new SimpleStringProperty();
    public String getRecetteP(){ return this.recetteP.get(); }
    protected void setRecetteP(String nom) { this.recetteP.set(nom); }
    public StringProperty recetteProperty() {return this.recetteP; }
        
    /**
     * Liste des ingredients
     */
    private ObservableList<IIngredient> ingredients; //= FXCollections.observableArrayList();
    public List<IIngredient> getIngredients() { return ingredients.stream().collect(Collectors.toList()); }
    public void setIngredients(List<IIngredient> liste) { this.ingredients = FXCollections.observableArrayList(liste); }
    public ObservableList<IIngredient> getIngredientsObservable() { return ingredients; }
    /*private List<IIngredient> ingredients;
    public List<IIngredient> getIngredients() { return ingredients; }
    protected void setIngredients(List<IIngredient> ingredients) { this.ingredients = ingredients; }*/
    
    /**
     * Nombre d'ingrédients
     * @return 
     */
    public int nbIngredients(){ return this.getIngredients().size(); }
    private final StringProperty nbIngredientsP = new SimpleStringProperty();
    public StringProperty nbIngredientsProperty() {
        nbIngredientsP.set(Integer.toString(nbIngredients()));
        return this.nbIngredientsP; 
    }
    
    private int duree;
    public void setDuree(int duree) { 
        if(duree < 0) duree *= -1;
        this.duree = duree; 
    }
    public int getDuree() { return this.duree; }
    
    private Difficulte difficulte;
    public void setDifficulte(Difficulte difficulte) { this.difficulte = difficulte; }
    public Difficulte getDifficulte() { return difficulte; }
    
    private Budget prix;
    public void setPrix(Budget prix) { this.prix = prix; }
    public Budget getPrix() { return prix; }
    
    /**
     * Constructeur de Recette
     * @param nom
     * @param recette
     * @param liste 
     */
    Recette(String nom, String recette, List<IIngredient> liste){
        setNom(nom);
        setRecette(recette);
        setIngredients(liste);
        setDuree(0);
        setDifficulte(Difficulte.Inconnue);
        setPrix(Budget.Inconnu);
    }
    
    Recette(String nom, String recette){
        this(nom, recette, new ArrayList<IIngredient>());
    }
    
    /**
     * Constructeur de Recette plus complet
     * @param nom
     * @param recette
     * @param minutes
     * @param difficulte
     * @param prix 
     */
    Recette(String nom, String recette, int minutes, Difficulte difficulte, Budget prix){
        this(nom, recette);
        setDuree(minutes);
        setDifficulte(difficulte);
        setPrix(prix);
    }
    
    /**
     * Constructeur de Recette anonyme
     */
    Recette(){
        this("","");
    }
    
    /**
     * Fonction permettant d'ajouter un ingrédient au livre
     * @param ingredient 
     */
    public void ajouterIngredient(IIngredient ingredient){
        ingredients.add(ingredient);
    }
    
    public void ajouterIngredients(List<IIngredient> ingredients){
        setIngredients(ingredients);
    }
    
    public void supprimerIngredient(IIngredient ingredient){
        ingredients.remove(ingredient);
    }
    
    public void supprimerIngredient(int index){
        ingredients.remove(index);
    }
    
    public String toString(){
        String retour = getNom();/*+ " : \n";
        for(int i = 0; i < getNom().length(); i++)
            retour += "-";
        retour += "--\n" + getRecette() + "\n";
        retour += "# Réalisation :\n";
        retour += "* Durée : " + getDuree() + "\n";
        retour += "* Difficulté : " + getDifficulte() + "\n";
        retour += "* Budget : " + getPrix() + "\n";
        retour += "# Les " + nbIngredients() + " ingrédients sont :\n";
        for(IIngredient ingredient : getIngredients())
            retour += "* " + ingredient + "\n";*/
        return retour;
    }
    
    public int hashCode(){
        return getNom().toLowerCase().hashCode();
    }
 
    /**
     * 
     * @param Object o
     * @return 
     */
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Recette)) return false;
        return this.equals((Recette)o);
    }
    
    public boolean equals(Recette r){
        return this.hashCode() == r.hashCode();
    }
}
