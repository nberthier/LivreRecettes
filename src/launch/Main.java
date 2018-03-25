/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */

package launch;

import controller.LivreWindowController;
import controller.RecetteFormulaireController;
import data.XMLDataManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.IRecette;
import model.Livre;

/** 
 *
 * @author clboissard
 */
public class Main extends Application {
    
    private Stage stage;
    public Stage getStage(){
        return stage;
    }
    private BorderPane rootWindow;
    
    //public Livre livre
    private final ObjectProperty<Livre> livre = new SimpleObjectProperty<Livre>();
    public Livre getLivre(){ return livre.get(); }
    public void setLivre(Livre l){ livre.set(l); }
    public ObjectProperty<Livre> livreProperty() { return livre; }
    
    private ObservableList<IRecette> recettesList;
    public ObservableList<IRecette> getRecettesList(){ return recettesList; }
    
    public Main(){
        setLivre(new Livre(new XMLDataManager()));
        getLivre().chargerRecettes();
        recettesList = getLivre().getRecettesObservable();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.getIcons().add(new Image("http://sr.photos2.fotosearch.com/bthumb/CSP/CSP389/k19789040.jpg"));
        stage.setTitle("Livre de recettes");
        
        initRootWindow();
        lancerLivreWindow();
    }
    
    public void initRootWindow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootWindow.fxml"));
            
            rootWindow = (BorderPane) loader.load();
            Scene scene = new Scene(rootWindow,700,600);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void lancerLivreWindow(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LivreWindow.fxml"));
            AnchorPane livreWindow = (AnchorPane) loader.load();
            
            rootWindow.setCenter(livreWindow);
            LivreWindowController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public IRecette lancerRecetteFormulaire(IRecette recette){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RecetteFormulaire.fxml"));
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
            
            return controller.getRecette();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
