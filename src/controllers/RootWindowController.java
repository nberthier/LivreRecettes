package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import launchers.Graphique;
import utils.ClassesManager;
import model.DataManager;

/**
 * FXML Controller class
 *
 * @author Clément
 */
public class RootWindowController implements Initializable {

    /**
     * Classe Graphique main, la classe qui lance l'application graphique
     */
    Graphique main;
    
    /**
     * Menu qui contiendra les modes de persistance de type DataManager
     */
    @FXML
    Menu dataManagerMenu;
    /**
     * Le groupe à "bascule" pour les modes de persistance
     */
    private ToggleGroup dataManagerGroup;
    /**
     * La liste des classes pour la persistance de type DataManager
     */
    private List<Class> dataManagerModes;
    
    /**
     * Menu qui contiendra le champs de texte pour l'url du fichier
     */
    @FXML
    Menu fileMenu;
    /**
     * TextField contenant le chemin du fichier
     */
    @FXML
    TextField fileTextField;
    
    /**
     * Url du fichier de sauvegarde
     */
    private /*SimpleObjectProperty<File>*/ File currentFile;
    
    /**
     * Initialise le contrôleur de la classe.
     * @param url l'emplacement pour les chemins de l'objet racine
     * @param rb les ressources utilisées pour localiser l'objet racine
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManagerGroup = new ToggleGroup();
        dataManagerGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(dataManagerGroup.getSelectedToggle() != null){
                try {
                    Class clazz = Class.forName(dataManagerGroup.getSelectedToggle().getUserData().toString());
                    main.getLivre().setDataManager((DataManager)clazz.newInstance());
                    // Modifie le fichier courant du menu
                        if(currentFile != null)
                            changerCurrentFile(currentFile);
                        else
                            changerCurrentFile(main.getLivre().getDataManager().getFile());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(RootWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void setMain(Graphique main) {
        this.main = main;
        creerMenuModeDataManager();
    }    
    
    public void creerMenuModeDataManager(){
        try {
            dataManagerModes = ClassesManager.getClasses("data_managers");
            /*
             * Ma boucle for pour parcourir les classes.
            for(Class classe : dataManagerModes){
                RadioMenuItem item = new RadioMenuItem(classe.getSimpleName().replace("DataManager", ""));
                item.setUserData(classe.getName());
                item.setToggleGroup(dataManagerGroup);
                if(main.getLivre().getDataManager() != null && classe.equals(main.getLivre().getDataManager().getClass()))
                    item.setSelected(true);
                dataManagerMenu.getItems().add(item);
            }
             */
            // "functional operation" créée par Netbeans équivalent à ma boucle for
            dataManagerModes.stream().map((classe) -> { 
                RadioMenuItem item = new RadioMenuItem(classe.getSimpleName().replace("DataManager", ""));
                item.setUserData(classe.getName());
                item.setToggleGroup(dataManagerGroup);
                if(main.getLivre().getDataManager() != null && classe.equals(main.getLivre().getDataManager().getClass()))
                    item.setSelected(true);
                return item;
            }).forEachOrdered((item) -> {
                dataManagerMenu.getItems().add(item);
            }); 
            
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(RootWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void sauvegarder(){
        try {
            if(main.dialogSauvegarde("Sauvegarde","La précédentes sauvegarde va être écraser !","Ëtes-vous sûr de vouloir valider la sauvegarde ?"))
                main.getLivre().sauvegarderRecettes();
        } catch (Exception ex) {
            main.popup("Erreur opération","Problème durant la sauvegarde",ex.getMessage()+"\nVeuillez vérifier le fichier choisi dans l'onglet 'Sauvegarde' puis 'Fichier'"+"\nVeuillez selectionner un mode de persistance dans l'onglet 'Sauvegarde' puis 'Changer mode'",true);
        }
    }
    
    @FXML
    public void charger(){
        try{
            if(main.dialogSauvegarde("Chargement","Les modifications effectuées sur les recettes, non sauvegarder, vont être écrasées !","Ëtes-vous sûr de vouloir valider le chargement ?"))
                main.getLivre().chargerRecettes();
        } catch (Exception ex) {
            main.popup("Erreur opération","Problème durant le chargement",ex.getMessage()+"\nVeuillez vérifier le fichier choisi dans l'onglet 'Sauvegarde' puis 'Fichier'"+"\nVeuillez selectionner un mode de persistance valide dans l'onglet 'Sauvegarde' puis 'Changer mode'",true);
        }
    }
    
    @FXML
    public void ouvrir(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Fichier de sauvegarde");
        if(currentFile != null)
            fc.setInitialDirectory(currentFile.getParentFile()); //currentFile.getValue().getParentFile()
        changerCurrentFile(fc.showOpenDialog(main.getStage()));
        System.out.println(currentFile);
    }
    
    public void changerCurrentFile(File file){
        if(file != null){
            if(main.getLivre().getDataManager() != null)
                main.getLivre().getDataManager().setFile(file.getAbsolutePath());
            currentFile = file;
            fileTextField.setText(currentFile.getAbsolutePath());
        }
    }
}
