/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    /**
     * Accesseur du nom
     * @return String le nom
     */
    @Override
    public String getNom(){ return nom; }
    /**
     * Mutateur du nom
     * @param nom le nouveau nom
     */
    protected final void setNom(String nom){ 
        this.nom = nom; 
        setNomP(this.getNom());
    }
    /**
     * Propriété de nom
     */
    private final StringProperty nomP = new SimpleStringProperty();
    /**
     * Mutateur de la propriété nom de la recette
     * @param nom le nom 
     */
    protected void setNomP(String nom) { this.nomP.set(nom); }
    /**
     * Accesseur de la propriété nomP
     * @return la propiété nomP
     */
    @Override
    public StringProperty nomProperty() {return this.nomP; }
    
    /**
     * Réalisation de la recette, suite d'instruction
     */
    private String recette;
    /**
     * Accesseur de la réalisation
     * @return String les instructions
     */
    @Override
    public String getRecette(){ return recette; }
    /**
     * Mutateur de la réalisation
     * @param recette le texte des instructions
     */
    protected final void setRecette(String recette) { 
        this.recette = recette; 
        setRecetteP(this.getRecette());
    }
    /**
     * Propriété de la réalisation de la recette
     */
    private final StringProperty recetteP = new SimpleStringProperty();
    /**
     * Mutateur de la propriété recette de la recette
     * @param recette le texte
     */
    protected void setRecetteP(String recette) { this.recetteP.set(recette); }
    @Override
    public StringProperty recetteProperty() {return this.recetteP; }
        
    /**
     * Liste des ingredients
     */
    private ObservableList<IIngredient> ingredients;
    /**
     * Convertis l'ObservableList en List
     * @return List la liste des ingrédients
     */
    @Override
    public List<IIngredient> getIngredients() { return ingredients.stream().collect(Collectors.toList()); }
    /**
     * Mutateur de l'observable liste des ingrédients
     * @param liste la liste des ingrédients
     */
    public final void setIngredients(List<IIngredient> liste) { this.ingredients = FXCollections.observableArrayList(liste); }
    @Override
    public ObservableList<IIngredient> getIngredientsObservable() { return ingredients; }
    
    /**
     * Nombre d'ingrédients
     * @return entier le nombre d'ingrédient
     */
    @Override
    public int nbIngredients(){ return this.getIngredients().size(); }
    /**
     * Propriété pour le nombre d'ingrédients
     */
    private final StringProperty nbIngredientsP = new SimpleStringProperty();
    /**
     * Accesseur de la propriété nbIngredientP
     * @return StringProperty le nombre d'ingrédient sous forme de string
     */
    @Override
    public StringProperty nbIngredientsProperty() {
        nbIngredientsP.set(Integer.toString(nbIngredients()));
        return this.nbIngredientsP; 
    }
    
    /**
     * La duree de réalisation d'une recette
     */
    private int duree;
    /**
     * Mutateur de la duree
     * @param duree le temps en minutes
     */
    @Override
    public final void setDuree(int duree) { 
        if(duree < 0) duree *= -1;
        this.duree = duree; 
    }
    /**
     * Accesseur de la duree
     * @return le nombre de minutes
     */
    @Override
    public int getDuree() { return this.duree; }
    
    /**
     * La difficulté de réalisation parmis l'énumération Difficulte
     */
    private Difficulte difficulte;
    /**
     * Mutateur de la difficulté
     * @param difficulte une constante de l'énumération Difficulte
     */
    @Override
    public final void setDifficulte(Difficulte difficulte) { this.difficulte = difficulte; }
    /**
     * Accesseur de la difficulté
     * @return constante de Difficulte
     */
    @Override
    public Difficulte getDifficulte() { return difficulte; }
    
    /**
     * Le Budget de la recette parmis l'énumération Budget
     */
    private Budget prix;
    /**
     * Mutateur du budget
     * @param prix une constante de Budget
     */
    @Override
    public final void setPrix(Budget prix) { this.prix = prix; }
    /**
     * Accesseur du prix
     * @return une constante de Budget
     */
    @Override
    public Budget getPrix() { return prix; }
    
    /**
     * Constructeur de Recette
     * @param nom le nom de la recette
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
    @Override
    public void ajouterIngredient(IIngredient ingredient){
        ingredients.add(ingredient);
    }
    
    /**
     * Ajoute plusieurs ingrédients au livre
     * @param ingredients la liste d'ingrédient
     */
    @Override
    public void ajouterIngredients(List<IIngredient> ingredients){
        setIngredients(ingredients);
    }
    
    /**
     * Supprime un ingredient
     * @param ingredient l'ingrédient à supprimer
     */
    @Override
    public void supprimerIngredient(IIngredient ingredient){
        ingredients.remove(ingredient);
    }
    
    /**
     * Supprime un ingrédient
     * @param index la position de l'ingrédient dans la liste
     */
    @Override
    public void supprimerIngredient(int index){
        ingredients.remove(index);
    }
    
    /**
     * Redéfinition de la fonction toString
     * @return String texte d'une recette
     */
    @Override
    public String toString(){
        String retour = getNom()+ " : \n";
        for(int i = 0; i < getNom().length(); i++)
            retour += "-";
        retour += "--\n" + getRecette() + "\n";
        retour += "# Réalisation :\n";
        retour += "* Durée : " + getDuree() + "\n";
        retour += "* Difficulté : " + getDifficulte() + "\n";
        retour += "* Budget : " + getPrix() + "\n";
        retour += "# Les " + nbIngredients() + " ingrédients sont :\n";
        retour = getIngredients().stream().map((ingredient) -> "* " + ingredient + "\n").reduce(retour, String::concat);
        return retour;
    }
    
    /**
     * Redéfinition de la fonction hashCode
     * @return un hash de la recette
     */
    @Override
    public int hashCode(){
        return getNom().toLowerCase().hashCode();
    }
 
    /**
     * Redéfinition de la fonction equals
     * @param o l'objet avec lequel comparer
     * @return boolean si égal ou non
     */
    @Override
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
