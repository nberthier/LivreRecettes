package launchers;

import data_managers.StubDataManager;
import data_managers.XMLDataManager;
import java.util.List;
import model.DataManager;
import model.Fabrique;
import model.IRecette;
import model.Livre;

/**
 * Classe pour effectuer des tests unitaires divers et variés
 * @author Clément
 */
public class Tests {

    public static void main(String[] argc) throws Exception{
        System.out.println("Tests :\n=======");
        
        // Création du livre
        Livre l = new Livre();
        IRecette r1, r2;
        // Ajout d'une recette Test
        r1 = Fabrique.creerRecette("Test", "Yolo ceci est un super test bie ntesté");
        l.ajouterRecette(r1);
        // Ajout d'une recette TéST similaire à Test qui ne sera donc pas ajoutée
        /*System.out.println(l);
        r2 = Fabrique.creerRecette("TéST", "Encore un fucking test");
        System.out.println("Ajout de la recette " + r2);
        l.ajouterRecette(r2);
        System.out.println(l);*/
        
        // Création du DataManager
        DataManager d = new StubDataManager();
        //System.out.println(d.getFile().getAbsolutePath());
        
        // Ajout du DataMAnager au livre 
        l.setDataManager(d);
        // Chargement de la liste et rajout de l'ancienne
        List<IRecette> liste = l.getRecettes();
        l.chargerRecettes();
        l.ajouterRecettes(liste);
        // Affichage du livre
        System.out.println(l);
        
        //l.rechercherRecettes();
        System.out.println(l);
    }
    
}
