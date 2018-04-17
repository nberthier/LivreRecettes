package model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.xml.sax.SAXException;

/**
 * L'interface permettant de définir les méthodes a écrire dans une classe gérant la persistance
 * @author Clément
 */
public interface DataManager {
    
    /**
     * Méthode à redéfinir permettant de charger les recettes
     * @return liste de IRecette charger
     * @throws Exception si une exception est levée
     */
    public List<IRecette> chargementRecettes() throws Exception;
    
    /**
     * Méthode à redéfinir permettant de sauvegarder les recettes
     * @param recettes la liste des recettes à sauvegarder
     * @throws Exception si une exception est levée
     */
    public void sauvegardeRecettes(List<IRecette> recettes) throws Exception;
    
    /**
     * Mutateur du fichier de sauvegarde.
     * @param filePath le chemin du fichier
     */
    public void setFile(String filePath);
    /**
     * Accesseur du fichier
     * @return file
     */
    public File getFile();
}
