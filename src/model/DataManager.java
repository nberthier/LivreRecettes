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
    
    /**
     * Mutateur de l'url du fichier de sauvegarde.
     * @param fileUrl 
     */
    public void setFile(String fileUrl);
    /**
     * Accesseur de l'url du fichier
     * @return file
     */
    public String getFile();
}
