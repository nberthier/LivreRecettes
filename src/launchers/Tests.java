package launchers;

import data_managers.XMLDataManager;
import model.Fabrique;
import model.IRecette;
import model.Livre;

/**
 * Classe pour effectuer des tests unitaires
 * @author Clément
 */
public class Tests {

    public static void main(String[] argc){
        System.out.println("Tests :\n=======");
        
        Livre l = new Livre();
        IRecette r1, r2;
        r1 = Fabrique.creerRecette("Test", "Yolo ceci est un super test bie ntesté");
        l.ajouterRecette(r1);
        System.out.println(l);
        r2 = Fabrique.creerRecette("TéST", "Encore un fucking test");
        System.out.println("Ajout de la recette " + r2);
        l.ajouterRecette(r2);
        System.out.println(l);
    }
    
}
