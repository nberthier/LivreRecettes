package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import models.IIngredient;

/**
 * Cellule graphique de liste, cellule formulaire d'un seul ingrédient. Une cellule pour chaque ingrédient
 * @author Clément
 */
public class IngredientFormCell extends ListCell<IIngredient>{
    
    private AnchorPane renderer;
    private IngredientFormCellController rendererController;
    
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
