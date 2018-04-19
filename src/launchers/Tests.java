package launchers;

import data_managers.StubDataManager;
import data_managers.XMLDataManager;
import java.util.List;
import model.DataManager;
import model.Fabrique;
import model.IRecette;
import model.Livre;
import model.Unite;

/**
 * Classe pour effectuer des tests unitaires divers et variés
 * @author Clément
 */
public class Tests {

    public static Livre l;
    
    public static void main(String[] argc) throws Exception{
        System.out.println("Tests :\n=======");
        
        // ** Création de base ** //
        System.out.println();
        // Création d'un livre
        System.out.println("Création d'un Livre l.");
        l = new Livre();
        // Création d'un datamanager
        System.out.println("Création d'un DataManager dm.");
        DataManager dm = new StubDataManager(); // new XMLDataManager();
        // Ajout du DataMAnager au livre 
        System.out.println("Ajout du DataManager dm au Livre l.");
        l.setDataManager(dm);
        // Test sur le chemin du fichier
        try { System.out.println("Le chemin du fichier de sauvegarde du DataManager dm : " + dm.getFile().getAbsolutePath()); }
        catch(NullPointerException e){ System.out.println("Le fichier du DataManager dm est " + e.getMessage()); }
        
        // ** Test d'égalité entre 2 recettes ** //
        System.out.println();
        System.out.println("Test de l'égalité entre deux recettes r1 et r2, test d'égalité si les noms (simplifiés : sans accent et en minuscule) sont les mêmes.");
        IRecette r1, r2;
        // Ajout d'une recette Test
        r1 = Fabrique.creerRecette("Test", "Yolo ceci est un super test bie ntesté");
        System.out.println("Création et ajout d'une recette r1 : " + r1);
        l.ajouterRecette(r1);
        // Ajout d'une recette TéST similaire à Test qui ne sera donc pas ajoutée
        r2 = Fabrique.creerRecette("TéST", "Encore un fucking test");
        System.out.println("Création et ajout d'une recette r2 : " + r2);
        l.ajouterRecette(r2);
        System.out.println("Les recettes du livre sont : " + l + " ; il y a " + l.getNbRecettes() + " recette(s) sur les 2 ajouts.");
        
        // ** Chargement des recettes ** //
        System.out.println();
        System.out.println("Chargement des recettes, en plus, dans le Livre l depuis le DataManager dm");
        // Sauvegarde de l'ancienne liste de recettes
        List<IRecette> liste = l.getRecettes();
        // Chargement des recettes
        l.chargerRecettes();
        // Rajout de l'ancienne
        l.ajouterRecettes(liste);
        // Affichage du livre
        System.out.println("Le livre est maintenant composé de :\n\t" + l);
        
        // ** Recherche de recettes ** //
        System.out.println();
        System.out.println("Recherche de recettes dans le Livre l");
        // Création d'un "recette" de recherche
        IRecette recherche = Fabrique.creerRecette("pizza");
        // Recherche de recettes par le nom
        testRecherche(recherche);
        
        // Création d'une "recette" sans nom mais avec ingrédient(s)
        recherche = Fabrique.creerRecette("");
        recherche.ajouterIngredient(Fabrique.creerIngredient("tomates", 0, Unite.unite));
        // Recherche de recettes par ingrédient
        testRecherche(recherche);
        
        // Création d'une "recette" 
        recherche = Fabrique.creerRecette("pizza");
        recherche.ajouterIngredient(Fabrique.creerIngredient("tomates", 0, Unite.unite));
        // Recherche de recettes par ingrédient et par nom
        testRecherche(recherche);
    }
    
    private static void testRecherche(IRecette recette){
        System.out.println("---- Nouveau test de recherche ----");
        System.out.println("Création d'une \"recette\" de recherche : \"" + recette + "\" avec les ingrédients : " + recette.getIngredients());
        l.rechercherRecettes(recette);
        System.out.println("Les recettes trouvés : " + l);
    }
    
}
