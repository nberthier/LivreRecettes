package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
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
     * Url du fichier de sauvegarde
     */
    private String fileUrl;
    
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
                    // Modifie l'url du fichier courant
                    if(main.getLivre().getDataManager() != null)
                        fileUrl = main.getLivre().getDataManager().getFile();
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
        if(main.dialogSauvegarde("Sauvegarde","La précédentes sauvegarde va être écraser !","Ëtes-vous sûr de vouloir valider la sauvegarde ?"))
            if(!main.getLivre().sauvegarderRecettes())
                main.popup("Erreur opération","Aucun mode de persistance selectionné","Veuillez selectionner un mode de persistance dans l'onglet 'Sauvegarde' puis 'Changer mode'");
    }
    
    @FXML
    public void charger(){
        if(main.dialogSauvegarde("Chargement","Les modifications effectuées sur les recettes, non sauvegarder, vont être écrasées !","Ëtes-vous sûr de vouloir valider le chargement ?"))
            if(!main.getLivre().chargerRecettes())
                main.popup("Erreur opération","Aucun mode de persistance selectionné","Veuillez selectionner un mode de persistance dans l'onglet 'Sauvegarde' puis 'Changer mode'");
    }
    
    @FXML
    public void ouvrir(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Fichier de sauvegarde");
        System.out.println(fileUrl);
        if(fileUrl != null)
            fc.setInitialDirectory(new File(fileUrl));
        File f = fc.showOpenDialog(main.getStage());
        System.out.println(f);
    }
    
}
