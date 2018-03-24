/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import launch.Main;
import model.Budget;
import model.Difficulte;
import model.Fabrique;
import model.IIngredient;
import model.IRecette;
import model.Unite;

/**
 * FXML Controller class
 *
 * @author Clément
 */
public class RecetteFormulaireController implements Initializable {

    private Main main;
    
    @FXML
    private TextField nomField, dureeField, nbIngField;
    @FXML
    private TextArea recetteField;
    @FXML 
    private ComboBox difficulteField, budgetField;
    @FXML
    private ListView<IIngredient> ingredientsList;
    @FXML
    private GridPane gridPane;
    @FXML
    private ColumnConstraints column1;
    @FXML
    private HBox nbIngBox;
    
    private Stage stage;
    private IRecette temporaire;
    public IRecette getRecette(){
        return temporaire;
    }
    private boolean valider = false;
    public boolean estValider(){
        return valider;
    }
    
    private List<IIngredient> oldList;
    
    private String realOldValue = "0";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ingredientsList.prefWidthProperty().bind(gridPane.prefWidthProperty());
        nbIngBox.prefWidthProperty().bind(column1.prefWidthProperty());
        nbIngField.setText("0");
        nbIngField.prefWidthProperty().bind(nbIngBox.prefWidthProperty().multiply(0.25));
        nbIngField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                int newV = Integer.parseInt(newValue), oldV = temporaire.nbIngredients();
                if(newV != oldV){
                    int diff = newV - oldV;
                    if(diff > 0){
                        for(int i = 0; i < diff; i++){
                            if(newV > oldList.size())
                                temporaire.ajouterIngredient(Fabrique.creerIngredient("", 0, Unite.unite));
                            else
                                temporaire.ajouterIngredient(oldList.get(oldV + i));
                        }
                    }else if(diff < 0){
                        for(int i = 0; i < (-diff); i++)
                            temporaire.supprimerIngredient(temporaire.nbIngredients() -1);
                    }
                }
            }catch(NumberFormatException e){}
        });
        
        difficulteField.setItems(FXCollections.observableArrayList(Difficulte.values()));
        budgetField.setItems(FXCollections.observableArrayList(Budget.values()));
        nbIngField.setText("0");
    }    
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    public void setRecette(IRecette recette){
        this.temporaire = recette;
        this.oldList = Fabrique.cloneListeIngredients(recette.getIngredients());
        
        nomField.setText(recette.getNom());
        recetteField.setText(recette.getRecette());
        dureeField.setText(Integer.toString(recette.getDuree()));
        difficulteField.setValue(recette.getDifficulte());
        budgetField.setValue(recette.getPrix());
        nbIngField.setText(Integer.toString(recette.nbIngredients()));
        ingredientsList.setItems(recette.getIngredientsObservable());
        //ingredientsList.setCellFactory((param) -> {return new IngredientFormCell();}); // Même chose que en-dessous mais nécessite JDK8
        ingredientsList.setCellFactory(new Callback<ListView<IIngredient>, ListCell<IIngredient>>() {
            @Override
            public ListCell<IIngredient> call(ListView<IIngredient> param) {
                return new IngredientFormCell();
            }
        });
    }
    
    @FXML
    public void valider(){
        temporaire = Fabrique.creerRecette(nomField.getText(), recetteField.getText(), Integer.parseInt(dureeField.getText()), Difficulte.fromInt(difficulteField.getSelectionModel().getSelectedIndex()), Budget.fromInt(budgetField.getSelectionModel().getSelectedIndex()));
        temporaire.ajouterIngredients(ingredientsList.getItems());
        valider = true;
        stage.close();
    }
    
    @FXML
    public void annuler(){
        System.out.println(oldList);
        temporaire.ajouterIngredients(oldList);
        stage.close();
    }
}
