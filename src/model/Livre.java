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
 *
 * @author cb946032
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
    private DataManager getDataManager() { return dataManager; }
    
    public Livre(){
        setRecettes(new ArrayList<IRecette>());
    }
    
    public Livre(DataManager mgr){
        this();
        setDataManager(mgr);
    }
    
    public IRecette getRecette(String nom){
        return getRecettes().stream().filter(r->r.getNom().equals(nom)).findFirst().get();
    }
    
    public int getRecetteIndex(String nom){
        return getRecettesObservable().indexOf(new Recette(nom, ""));
    }
    
    public void ajouterRecette(IRecette recette){
        if(!getRecettesObservable().contains(recette))
            getRecettesObservable().add(recette);
    }
    
    public void ajouterRecettes(List<IRecette> recettes){
        //setRecettes(recettes);
        for(IRecette recette : recettes)
            ajouterRecette(recette);
    }
    
    public void supprimerRecette(IRecette recette){
        getRecettes().remove(recette);
    }
    
    public void supprimerRecette(int index){
        supprimerRecette(getRecettes().get(index));
    }
    
    public void modifierRecette(String nomEx, IRecette recette){
        int indexEx = getRecetteIndex(nomEx);
        getRecettesObservable().remove(indexEx);
        getRecettesObservable().add(indexEx, recette);
    }
    
    public boolean chargerRecettes(){
        if(dataManager == null) return false;
        getRecettesObservable().clear();
        ajouterRecettes(getDataManager().chargementRecettes());
        return true;
    }
    
    public boolean sauvegarderRecettes(){
        if(dataManager == null) return false;
        getDataManager().sauvegardeRecettes(getRecettes());
        return true;
    }
    
    public String toString(){
        String retour = "Livre de recettes :\n===================\n";
        if(getRecettes().size() < 1)
            retour += "Il n'y a aucune recette\n";
        for(IRecette recette : getRecettes())
            retour += recette + "\n";
        return retour;
    }
}
