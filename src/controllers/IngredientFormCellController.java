package controllers;

import model.Unite;
import model.IIngredient;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 * 
 * @author Clément
 */
public class IngredientFormCellController implements Initializable {

    @FXML
    private TextField nomIngField, quantiteField;
    @FXML
    private ComboBox uniteField;
    @FXML
    private HBox HBox;
    @FXML
    private AnchorPane AnchorPane;
    
    /**
     * Ingrédient courant pour mémoriser l'ingrédient avant sa modification, 
     * permet de récupérer l'ancienne version en cas d'abandon de la modification.
     */
    private IIngredient ingredientCourant;
    
    /**
     * Initialise le contrôleur de la classe.
     * @param url l'emplacement pour les chemins de l'objet racine
     * @param rb les ressources utilisées pour localiser l'objet racine
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HBox.prefWidthProperty().bind(AnchorPane.prefWidthProperty());
        nomIngField.prefWidthProperty().bind(HBox.prefWidthProperty().multiply(0.56));
        quantiteField.prefWidthProperty().bind(HBox.prefWidthProperty().multiply(0.14));
        uniteField.prefWidthProperty().bind(HBox.prefWidthProperty().multiply(0.3));
        
        nomIngField.setText("");
        quantiteField.setText("0");
        uniteField.setItems(FXCollections.observableArrayList(Unite.values()));
        uniteField.setValue(Unite.unite);
    }    
    
    /**
     * Remplit le formulaire d'un ingrédient avec ses valeurs
     * @param ingredient l'ingrédient à remplir
     */
    public void setCurrentIngredient(IIngredient ingredient){
        ingredientCourant = ingredient;
        nomIngField.setText(ingredient.getNom());
        nomIngField.textProperty().addListener((observable, oldValue, newValue) -> {
            ingredientCourant.setNom(newValue);
        });
        quantiteField.setText(Integer.toString(ingredient.getQuantite()));
        quantiteField.textProperty().addListener((observable, oldValue, newValue) -> {
            ingredientCourant.setQuantite(Integer.parseInt(newValue));
        });
        uniteField.setValue(ingredient.getUnite());
        uniteField.valueProperty().addListener((observable, oldValue, newValue) -> {
            ingredientCourant.setUnite(Unite.fromInt(uniteField.getSelectionModel().getSelectedIndex()));
        });
    }
    
    /**
     * Accesseur de l'ingrédient courant du formulaire
     * @return l'ingredient courant
     */
    public IIngredient getIngredient(){
        return ingredientCourant;
    }
    
}
