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
 * Classe représentant une recette
 * @author Clément
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
     * @return entier le nombre d'ingrédient
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
     * @param nom le nom
     * @param recette les indications pour la réalisation
     * @param liste la liste des ingrédients
     */
    Recette(String nom, String recette, List<IIngredient> liste){
        setNom(nom);
        setRecette(recette);
        setIngredients(liste);
        setDuree(0);
        setDifficulte(Difficulte.Inconnue);
        setPrix(Budget.Inconnu);
    }
    
    /**
     * Constructeur prenant 2 paramètres principaux
     * @param nom le nom
     * @param recette la recette
     */
    Recette(String nom, String recette){
        this(nom, recette, new ArrayList<IIngredient>());
    }
    
    /**
     * Constructeur de Recette plus complet
     * @param nom le nom   
     * @param recette la recette
     * @param minutes la duree
     * @param difficulte la difficulte
     * @param prix le prix
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
     * Ajoute un ingrédient au livre
     * @param ingredient l'ingrédient
     */
    public void ajouterIngredient(IIngredient ingredient){
        ingredients.add(ingredient);
    }
    
    /**
     * Ajoute plusieurs ingrédients au livre
     * @param ingredients la liste d'ingrédient
     */
    public void ajouterIngredients(List<IIngredient> ingredients){
        setIngredients(ingredients);
    }
    
    /**
     * Supprime un ingredient
     * @param ingredient l'ingrédient à supprimer
     */
    public void supprimerIngredient(IIngredient ingredient){
        ingredients.remove(ingredient);
    }
    
    /**
     * Supprime un ingrédient
     * @param index la position de l'ingrédient dans la liste
     */
    public void supprimerIngredient(int index){
        ingredients.remove(index);
    }
    
    /**
     * Redéfinition de la fonction toString
     * @return String texte d'une recette
     */
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
    
    /**
     * Redéfinition de la fonction hashCode
     * @return un hash de la recette
     */
    public int hashCode(){
        return getNom().toLowerCase().hashCode();
    }
 
    /**
     * Redéfinition de la fonction equals
     * @param o l'objet avec lequel comparer
     * @return boolean si égal ou non
     */
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Recette)) return false;
        return this.equals((Recette)o);
    }
    
    /**
     * Test si égal à une recette
     * @param r la recette avec laquelle tester
     * @return boolean si égal ou non
     */
    public boolean equals(Recette r){
        return this.hashCode() == r.hashCode();
    }
}
