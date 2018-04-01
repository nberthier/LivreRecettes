/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * L'interface permettant de définir les méthodes a écrire dans une classe gérant la persistance
 * @author Clément
 */
public interface DataManager {
    
    /**
     * Méthode à redéfinir permettant de charger les recettes
     * @return liste de IRecette charger
     */
    public List<IRecette> chargementRecettes();
    
    /**
     * Méthode à redéfinir permettant de sauvegarder les recettes
     * @param recettes la liste des recettes à sauvegarder
     */
    public void sauvegardeRecettes(List<IRecette> recettes);
    
    
}
