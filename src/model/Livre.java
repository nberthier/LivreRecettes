/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Livre permettant la gestion des recettes.
 * @author Clément
 */
public class Livre {
    
   /* private List<IRecette> recettes;
    public void setRecettes(List<IRecette> recettes) { this.recettes = recettes; }
    public List<IRecette> getRecettes() { return this.recettes; }
    public ObservableList<IRecette> getRecettesObservable(){ 
        return FXCollections.observableArrayList(getRecettes()); 
    }*/
    private ObservableList<IRecette> recettes = FXCollections.observableArrayList();
    public List<IRecette> getRecettes(){ return recettes.stream().collect(Collectors.toList()); }
    public void setRecettes(List<IRecette> recettes) { this.recettes = FXCollections.observableArrayList(recettes); }
    public ObservableList<IRecette> getRecettesObservable() { return recettes; }
    
    private DataManager dataManager;
    public void setDataManager(DataManager dataManager) { this.dataManager = dataManager; }
    public DataManager getDataManager() { return dataManager; }
    
    /**
     * Constructeur par défaut de la classe Livre
     * Initialise une liste de recettes vide
     */
    public Livre(){
        setRecettes(new ArrayList<IRecette>());
    }
    
    /**
     * Constructeur secondaire à favoriser
     * @param dataManager permettant la persistance
     */
    public Livre(DataManager dataManager){
        this();
        setDataManager(dataManager);
    }
    
    /**
     * Récupère une recette
     * @param nom à partir du nom de la recette
     * @return IRecette retourne une recette
     */
    public IRecette getRecette(String nom){
        return getRecettes().stream().filter(r->r.getNom().equals(nom)).findFirst().get();
    }
    
    /**
     * Récupère l'index d'une recette dans la liste des recettes
     * @param nom à partir du nom de la recette
     * @return int l'index
     */
    public int getRecetteIndex(String nom){
        return getRecettesObservable().indexOf(new Recette(nom, ""));
    }
    
    /**
     * Ajoute une recette dans la liste
     * @param recette la recette à ajouter
     */
    public void ajouterRecette(IRecette recette){
        if(!getRecettesObservable().contains(recette))
            getRecettesObservable().add(recette);
    }
    
    /**
     * Ajoute une liste de recette à la liste
     * @param recettes la liste des recettes
     */
    public void ajouterRecettes(List<IRecette> recettes){
        //setRecettes(recettes);
        for(IRecette recette : recettes)
            ajouterRecette(recette);
    }
    
    /**
     * Supprime une recette de la liste
     * @param recette la recette à supprimer
     */
    public void supprimerRecette(IRecette recette){
        getRecettes().remove(recette);
    }
    
    /**
     * Supprime une recette de la liste
     * @param index la position de la recette à supprimer dans la liste
     */
    public void supprimerRecette(int index){
        supprimerRecette(getRecettes().get(index));
    }
    
    /**
     * Modifie une recette
     * @param nomEx le nom de la recette à modifier
     * @param recette la recette contenant ses nouvelles valeurs
     * Remplace la recette par la nouvelle
     */
    public void modifierRecette(String nomEx, IRecette recette){
        int indexEx = getRecetteIndex(nomEx);
        getRecettesObservable().remove(indexEx);
        getRecettesObservable().add(indexEx, recette);
    }
    
    /**
     * Charge les recettes grâce à la persistance dans la liste
     * @return boolean correspondant à la valeur de réussite de l'opération
     */
    public boolean chargerRecettes(){
        if(dataManager == null) return false;
        getRecettesObservable().clear();
        ajouterRecettes(getDataManager().chargementRecettes());
        return true;
    }
    
    /**
     * Sauvegarde la liste des recettes grâce à la persistance
     * @return boolean correspondant à la valeur de réussite de l'opération
     */
    public boolean sauvegarderRecettes(){
        if(dataManager == null) return false;
        getDataManager().sauvegardeRecettes(getRecettes());
        return true;
    }
    
    /**
     * Redéfinition de la fonction toString
     * @return String correspondant au texte à afficher
     */
    public String toString(){
        String retour = "Livre de recettes :\n===================\n";
        if(getRecettes().size() < 1)
            retour += "Il n'y a aucune recette\n";
        for(IRecette recette : getRecettes())
            retour += recette + "\n";
        return retour;
    }
}
