/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.IIngredient;
import model.Unite;

/**
 *
 * @author Clément
 */
public class IngredientFormCell extends ListCell<IIngredient>{
    
    private AnchorPane renderer;
    private IngredientFormCellController rendererController;
    
    public IngredientFormCell(){
        super();
        try {
            final URL fxmlURL = getClass().getResource("/view/IngredientFormCell.fxml");
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
