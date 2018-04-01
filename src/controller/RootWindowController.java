/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import launch.Main;
import model.ClassesManager;
import model.DataManager;

/**
 * FXML Controller class
 *
 * @author Clément
 */
public class RootWindowController implements Initializable {

    Main main;
    
    @FXML
    Menu dataManagerMenu;
    
    ToggleGroup dataManagerGroup;
    
    List<Class> dataManagerModes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManagerGroup = new ToggleGroup();
        dataManagerGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(dataManagerGroup.getSelectedToggle() != null){
                try {
                    Class clazz = Class.forName(dataManagerGroup.getSelectedToggle().getUserData().toString());
                    main.getLivre().setDataManager((DataManager)clazz.newInstance());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(RootWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void setMain(Main main) {
        this.main = main;
        creerMenuModeDataManager();
    }    
    
    public void creerMenuModeDataManager(){
        try {
            dataManagerModes = ClassesManager.getClasses("data");
            for(Class classe : dataManagerModes){
                RadioMenuItem item = new RadioMenuItem(classe.getSimpleName().replace("DataManager", ""));
                item.setUserData(classe.getName());
                item.setToggleGroup(dataManagerGroup);
                if(main.getLivre().getDataManager() != null && classe.equals(main.getLivre().getDataManager().getClass()))
                    item.setSelected(true);
                dataManagerMenu.getItems().add(item);
            }
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(RootWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void changerMode(){
        /** créer nouvelle fenetre avec combobox **/
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
    
}
