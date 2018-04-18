package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import model.IIngredient;

/**
 * Cellule graphique de liste, cellule formulaire d'un seul ingrédient. 
 * <br>Une cellule de cette classe sera instancier pour chaque ingrédient.
 * @author Clément
 */
public class IngredientFormCell extends ListCell<IIngredient>{
    /**
     * Volet d'ancrage d'éléments graphiques.
     */
    private AnchorPane renderer;
    /**
     * Le contrôleur du formulaire d'ingrédient.
     */
    private IngredientFormCellController rendererController;
    
    /**
     * Le constructeur de la cellule formualire d'un ingrédient.
     */
    public IngredientFormCell(){
        super();
        try {
            final URL fxmlURL = getClass().getResource("/views/IngredientFormCell.fxml");
            final FXMLLoader fXMLLoader = new FXMLLoader(fxmlURL);
            renderer = fXMLLoader.load();
            rendererController = (IngredientFormCellController) fXMLLoader.getController();
        } catch (IOException ex) {
            Logger.getLogger(IngredientFormCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Méthode permettant de personnaliser la cellule.
     * @param object l'objet avec lequel remplir la cellule
     * @param empty si la cellule est vide ou non
     */
    @Override
    protected void updateItem(IIngredient object, boolean empty){
        super.updateItem(object, empty);
        Node graphic = null;
        if (!empty && object != null){
            graphic = renderer;
            rendererController.setCurrentIngredient(object);
        }
        setGraphic(graphic);
    }
    
}
