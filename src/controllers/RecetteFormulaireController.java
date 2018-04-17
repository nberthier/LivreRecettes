package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import launchers.Graphique;
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

    private Graphique main;
    
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
    
    private boolean valider = false;
    /**
     * formulaire de recette a été valider ou annuler
     * @return boolean si oui ou non
     */
    public boolean estValider(){
        return valider;
    }
    
    /**
     * permet de récuperer la recette
     * @return la recette modifier ou non
     */
    public IRecette retour(){
        if(estValider())
            return temporaire;
        else return null;
    }
    
    /**
     * Ancienne liste des ingrédients
     */
    private List<IIngredient> oldList;
    
    /**
     * Initialise le contrôleur de la classe.
     * @param url l'emplacement pour les chemins de l'objet racine
     * @param rb les ressources utilisées pour localiser l'objet racine
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ingredientsList.prefWidthProperty().bind(gridPane.prefWidthProperty());
        nbIngBox.prefWidthProperty().bind(column1.prefWidthProperty());
        nbIngField.setText("0");
        nbIngField.prefWidthProperty().bind(nbIngBox.prefWidthProperty().multiply(0.25));
        // Permet d'ajouter ou supprimer un formulaire d'ingrédient en fonction du nombre d'ingrédient désiré
        nbIngField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                int newV = Integer.parseInt(newValue), oldV = temporaire.nbIngredients();
                if(newV != oldV){
                    int diff = newV - oldV;
                    if(diff > 0){
                        for(int i = 0; i < diff; i++){
                            // permet lors de l'ajout d'une cellule de formulaire d'ingrédient de le remplir avec un ingredient si il y en a un de libre
                            if(oldV < oldList.size() && (oldV + i) < oldList.size() )
                                temporaire.ajouterIngredient(oldList.get(oldV + i));
                            else temporaire.ajouterIngredient(Fabrique.creerIngredient("", 0, Unite.unite));                                
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
    
    /**
     * Associe la classe mère du GUI
     * @param main l'instance de la classe mère
     */
    public void setMain(Graphique main) {
        this.main = main;
    }
    
    /**
     * Associe le stage
     * @param stage le stage de la classe mère
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
    /**
     * Rempli le formulaire avec les valeurs de la recette en question
     * @param recette la recette dont les valeurs remplissent le formulaire
     */
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
        ingredientsList.setCellFactory((ListView<IIngredient> param) -> new IngredientFormCell()); // Même chose que en-dessous mais en version lambda expression, nécessite JDK8
        /*
        ingredientsList.setCellFactory(new Callback<ListView<IIngredient>, ListCell<IIngredient>>() {
            @Override
            public ListCell<IIngredient> call(ListView<IIngredient> param) {
                return new IngredientFormCell();
            }
        });
        */
    }
    
    /**
     * FXML action en cas d'appuis sur le bouton valider
     */
    @FXML
    public void valider(){
        temporaire = Fabrique.creerRecette(nomField.getText(), recetteField.getText(), Integer.parseInt(dureeField.getText()), Difficulte.fromInt(difficulteField.getSelectionModel().getSelectedIndex()), Budget.fromInt(budgetField.getSelectionModel().getSelectedIndex()));
        temporaire.ajouterIngredients(ingredientsList.getItems());
        valider = true;
        stage.close();
    }
    
    /**
     * FXML action en cas d'appuis sur le bouton annuler
     */
    @FXML
    public void annuler(){
        temporaire.ajouterIngredients(oldList);
        stage.close();
    }
}
