/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import launch.Main;
import model.Fabrique;
import model.IRecette;

/**
 *
 * @author Clément
 */
public class LivreWindowController implements Initializable {
    
    private Main main;
    
    @FXML
    private TableView<IRecette> recettesTable;
    @FXML
    private TableColumn<IRecette, String> nomColumn;
    @FXML
    private SplitPane splitpane;
    @FXML
    private AnchorPane anchorPaneFirst, anchorPaneSecond;
    @FXML
    private GridPane gridPane;
    @FXML 
    private ColumnConstraints column1, column2, column3;
    @FXML
    private HBox hBox;
    @FXML
    private ScrollPane recetteScrollPane;
    
    @FXML
    private Label nomLabel, dureeLabel, difficulteLabel, budgetLabel, recetteLabel;
    @FXML
    private ListView ingredientsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        splitpane.setDividerPositions(0.22);
        anchorPaneFirst.prefWidthProperty().bind(splitpane.getDividers().get(0).positionProperty());
        recettesTable.prefWidthProperty().bind(anchorPaneFirst.prefWidthProperty());
        nomColumn.prefWidthProperty().bind(recettesTable.prefWidthProperty().subtract(5));
        gridPane.prefWidthProperty().bind(anchorPaneSecond.prefHeightProperty().subtract(hBox.getPrefHeight()));
        recetteLabel.prefWidthProperty().bind(recetteScrollPane.widthProperty().subtract(15));
        
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        afficherDetailsRecette(null);
        recettesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsRecette(newValue));
    }
    
    /**
     * Est appelé par l'application pour donné une référence vers lui-même
     * @param mainApp
     */
    public void setMain(Main main) {
        this.main = main;
        recettesTable.setItems(main.getRecettesList());
    }
    
    private void afficherDetailsRecette(IRecette recette){
        if(recette != null){
            nomLabel.setText(recette.getNom());
            dureeLabel.setText(Integer.toString(recette.getDuree()));
            difficulteLabel.setText(recette.getDifficulte().toString());
            budgetLabel.setText(recette.getPrix().toString());         
            recetteLabel.setText(recette.getRecette());
            ingredientsLabel.setItems(recette.getIngredientsObservable());
        }else{
            nomLabel.setText("");
            dureeLabel.setText("");
            difficulteLabel.setText("");
            budgetLabel.setText("");
            recetteLabel.setText("");
            ingredientsLabel.setItems(FXCollections.observableArrayList());
        }
    }
    
    @FXML
    private void supprimerRecette() {
        int index = recettesTable.getSelectionModel().getSelectedIndex();
        IRecette r = recettesTable.getSelectionModel().getSelectedItem();
        if(index >= 0){
            recettesTable.getItems().remove(index);
            main.getLivre().supprimerRecette(r);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Selection");
            alert.setHeaderText("Aucune recette selectionnée");
            alert.setContentText("S'il vous plaît, selectionnez une recette à supprimer dans la liste.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void ajouterRecette(){
        IRecette nouvelle = Fabrique.creerRecette();
        nouvelle = main.lancerRecetteFormulaire(nouvelle);
        if(nouvelle != null){
            main.getLivre().ajouterRecette(nouvelle);
            recettesTable.getSelectionModel().select(nouvelle);
        }
    }
    
    @FXML
    private void modifierRecette(){
        IRecette nouvelle = recettesTable.getSelectionModel().getSelectedItem();
        if(nouvelle != null){
            String old = nouvelle.getNom();
            nouvelle = main.lancerRecetteFormulaire(nouvelle);
            if(nouvelle != null){
                main.getLivre().modifierRecette(old,nouvelle);
                recettesTable.getSelectionModel().select(nouvelle);
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Selection");
            alert.setHeaderText("Aucune recette selectionnée");
            alert.setContentText("S'il vous plaît, selectionnez une recette à modifier dans la liste.");
            alert.showAndWait();
        }        
    }
}
