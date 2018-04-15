package launchers;

import controllers.LivreWindowController;
import controllers.RecetteFormulaireController;
import controllers.RootWindowController;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.IRecette;
import models.Livre;

/**
 * La classe main de l'application lançant l'application graphique
 * @author Clément
 */
public class Main extends Application {
    
    private Stage stage;
    /**
     * Le conteneur JavaFX de plus haut level
     * @return Stage
     */
    public Stage getStage(){
        return stage;
    }
    private BorderPane rootWindow;
    
    private final ObjectProperty<Livre> livre = new SimpleObjectProperty<>();
    public final Livre getLivre(){ return livre.get(); }
    public final void setLivre(Livre l){ livre.set(l); }
    public ObjectProperty<Livre> livreProperty() { return livre; }
    
    /**
     * L'observable liste des recettes
     */
    private ObservableList<IRecette> recettesList;
    public ObservableList<IRecette> getRecettesList(){ return recettesList; }
    public void setRecetteList(List<IRecette> recettes){ recettesList = FXCollections.observableArrayList(recettes); }
    
    /**
     * Constructeur de la classe Main qui créer un livre et charge les recettes dans ce dernier
     */
    public Main(){
        this.setLivre(new Livre());
        this.getLivre().chargerRecettes();
        recettesList = this.getLivre().getRecettesObservable();
    }
    
    /**
     * Méthode de lancement de l'application graphique appelée par la fonction launch
     * @param stage le conteneur JavaFX de plus haut level
     * @throws Exception si une exception quelconque est levée
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        initRootWindow();
        lancerLivreWindow();
    }
    
    /**
     * Initialise le contenu de notre fenêtre graphique,
     * tel que ses dimensions, son nom, son icon et la barre de menu
     */
    public void initRootWindow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/RootWindow.fxml"));
            rootWindow = (BorderPane) loader.load();
            
            RootWindowController controller = loader.getController();
            controller.setMain(this);
            
            stage.getIcons().add(new Image("http://sr.photos2.fotosearch.com/bthumb/CSP/CSP389/k19789040.jpg"));
            stage.setTitle("Livre de recettes");
            stage.setScene(new Scene(rootWindow,700,600));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Charge le contenu graphique avec la liste
     */
    public void lancerLivreWindow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/LivreWindow.fxml"));
            AnchorPane livreWindow = (AnchorPane) loader.load();
            
            rootWindow.setCenter(livreWindow);
            LivreWindowController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {}
    }
    
    /**
     * Lance le formulaire permettant de modifier ou d'ajouter une recette
     * @param recette la recette en question
     * @return IRecette la nouvelle recette
     */
    public IRecette lancerRecetteFormulaire(IRecette recette){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/RecetteFormulaire.fxml"));
            AnchorPane formulaire = (AnchorPane) loader.load();
            
            Stage stageEdition = new Stage();
            stageEdition.setTitle("Edition Recette");
            stageEdition.initModality(Modality.WINDOW_MODAL);
            stageEdition.initOwner(stage);
            
            Scene scene = new Scene(formulaire);
            stageEdition.setScene(scene);
            
            RecetteFormulaireController controller = loader.getController();
            controller.setStage(stageEdition);
            controller.setRecette(recette);
            controller.setMain(this);
            
            stageEdition.showAndWait();
            return controller.retour();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Méthode principale lançant l'interface graphique
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Affiche un message sous la forme d'une boîte de dialogue
     * @param titre de la boîte de dialogue
     * @param sous_titre de la boîte de dialogue
     * @param explication contenu de la boîte de dialogue
     */
    public void popup(String titre, String sous_titre, String explication){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(this.getStage());
        alert.setTitle(titre);
        alert.setHeaderText(sous_titre);
        alert.setContentText(explication);
        alert.showAndWait();
    }
    
    /**
     * Affiche une boîte de dialogue demandant confirmation d'une opération
     * @param titre l'opération
     * @param indication l'indication
     * @param explication le texte de vérification
     * @return le choix 
     */
    public boolean dialogSauvegarde(String titre, String indication, String explication){
        ButtonType oui = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        ButtonType non = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(this.getStage());
        alert.setTitle(titre);
        alert.setHeaderText(indication);
        alert.setContentText(explication);
        alert.getButtonTypes().setAll(oui,non);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == oui;
    }
}
