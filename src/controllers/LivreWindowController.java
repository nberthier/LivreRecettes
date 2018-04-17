package controllers;

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
import launchers.Graphique;
import model.Fabrique;
import model.IRecette;

/**
 *
 * @author Clément
 */
public class LivreWindowController implements Initializable {
    /**
     * Classe Graphique main, la classe qui lance l'application graphique
     */
    private Graphique main;
    
    /**
     * La table des recettes "Master"
     */
    @FXML
    private TableView<IRecette> recettesTable;
    /**
     * La colonne des nom de la table
     */
    @FXML
    private TableColumn<IRecette, String> nomColumn;
    /**
     * La séparation de la partie Master et de la partie Details
     */
    @FXML
    private SplitPane splitpane;
    /**
     * Volets d'ancrage pour les éléments graphiques
     */
    @FXML
    private AnchorPane anchorPaneFirst, anchorPaneSecond;
    /**
     * La grille centrale pour les éléments graphiques
     */
    @FXML
    private GridPane gridPane;
    /**
     * Les colonnes de la grille
     */
    @FXML 
    private ColumnConstraints column1, column2, column3;
    /**
     * Une boîte de rangement horizontal d'élément graphique
     */
    @FXML
    private HBox hBox;
    /**
     * Panneau scrollable
     */
    @FXML
    private ScrollPane recetteScrollPane;
    /**
     * Les différents labels
     */
    @FXML
    private Label nomLabel, dureeLabel, difficulteLabel, budgetLabel, recetteLabel;
    /**
     * La liste des ingrédients
     */
    @FXML
    private ListView ingredientsLabel;

    /**
     * Initialise le contrôleur de la classe.
     * @param url l'emplacement pour les chemins de l'objet racine
     * @param rb les ressources utilisées pour localiser l'objet racine
     */
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
     * @param main la classe mère du GUI
     */
    public void setMain(Graphique main) {
        this.main = main;
        recettesTable.setItems(main.getRecettesList());
    }
    
    /**
     * Méthode qui remplis la zone Details
     * @param recette la recette à détailler
     */
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
    
    /**
     * Méthode action pour supprimer une recette.
     * <br>Supprime la recette sélectionnée dans la liste Master.
     */
    @FXML
    private void supprimerRecette() {
        int index = recettesTable.getSelectionModel().getSelectedIndex();
        IRecette r = recettesTable.getSelectionModel().getSelectedItem();
        if(index >= 0){
            if(main.dialogSauvegarde("Suppression","Suppression la recette !","Ëtes-vous sûr de vouloir valider la suppression ?")){
                recettesTable.getItems().remove(index);
                main.getLivre().supprimerRecette(r);
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getStage());
            alert.setTitle("Selection");
            alert.setHeaderText("Aucune recette selectionnée");
            alert.setContentText("S'il vous plaît, selectionnez une recette à supprimer dans la liste.");
            alert.showAndWait();
        }
    }
    
    /**
     * Méthode action ajouter une recette.
     * <br>Lance la fenêtre de formulaire de recette.
     */
    @FXML
    private void ajouterRecette(){
        IRecette nouvelle = Fabrique.creerRecette();
        nouvelle = main.lancerRecetteFormulaire(nouvelle);
        if(nouvelle != null){
            main.getLivre().ajouterRecette(nouvelle);
            recettesTable.getSelectionModel().select(nouvelle);
        }
    }
    
    /**
     * Méthode action modifier une recette.
     * <br>Lance la fenêtre de formulaire de recette avec la recette à modifier.
     */
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
