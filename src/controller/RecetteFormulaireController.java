/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import launch.Main;
import model.Budget;
import model.Difficulte;
import model.Fabrique;
import model.IRecette;

/**
 * FXML Controller class
 *
 * @author Clément
 */
public class RecetteFormulaireController implements Initializable {

    private Main main;
    
    @FXML
    private TextField nomField;
    @FXML
    private TextArea recetteField;
    @FXML
    private TextField dureeField;
    @FXML 
    private ComboBox difficulteField;
    @FXML 
    private ComboBox budgetField;
    
    private Stage stage;
    private IRecette temporaire;
    public IRecette getRecette(){
        return temporaire;
    }
    private boolean valider = false;
    public boolean estValider(){
        return valider;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        difficulteField.setItems(FXCollections.observableArrayList(Difficulte.values()));
        difficulteField.setValue(Difficulte.fromInt(0));
        budgetField.setItems(FXCollections.observableArrayList(Budget.values()));
        budgetField.setValue(Budget.fromInt(0));
    }    
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void setRecette(IRecette recette){
        this.temporaire = recette;
        
        nomField.setText(recette.getNom());
        recetteField.setText(recette.getRecette());
        dureeField.setText(Integer.toString(recette.getDuree()));
        difficulteField.setValue(recette.getDifficulte());
        budgetField.setValue(recette.getPrix());
    }
    
    @FXML
    public void valider(){
        temporaire = Fabrique.creerRecette(nomField.getText(), recetteField.getText(), Integer.parseInt(dureeField.getText()), Difficulte.fromInt(difficulteField.getSelectionModel().getSelectedIndex()), Budget.fromInt(budgetField.getSelectionModel().getSelectedIndex()));
        valider = true;
        stage.close();
    }
    
    @FXML
    public void annuler(){
        stage.close();
    }
    
}
