package data_managers;

import java.io.File;
import model.DataManager;
import model.Fabrique;
import model.Unite;
import model.Budget;
import model.IRecette;
import model.Difficulte;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Persistance en "dur" dans le code
 * @author Clément
 */
public class StubDataManager implements DataManager{

    /**
     * Mutateur de l'url du fichier de sauvegarde.
     * @param filePath le chemin du fichier ne sera pas mis en place car pas de fichier dans cette classe.
     */
    @Override
    public void setFile(String filePath) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Accesseur du chemin du fichier.
     * @return null car pas de fichier
     */
    @Override
    public File getFile() {
        return null;
    }
    
    /**
     * Une liste de recettes
     */
    private List<IRecette> recettes = new ArrayList<>();
    
    /**
     * Constructeur de la classe remplis 
     */
    public StubDataManager(){
        IRecette r1 = Fabrique.creerRecette("Crêpe", "Dans un saladier, versez la farine et les oeufs. Puis progressivement ajoutez le lait tout en mélangeant avec votre fouet. Ajoutez le sucre vanillé, la pincée de sel. Versez une demi-louche de votre pâte à crêpe et faites cuire 1 à 2 minutes par face.", 30, Difficulte.Facile, Budget.Reduit);
        r1.ajouterIngredient(Fabrique.creerIngredient("farine", 250, Unite.g));
        r1.ajouterIngredient(Fabrique.creerIngredient("oeuf", 4, Unite.unite));
        r1.ajouterIngredient(Fabrique.creerIngredient("lait", 500, Unite.mL));
        r1.ajouterIngredient(Fabrique.creerIngredient("sel", 1, Unite.pincee));
        r1.ajouterIngredient(Fabrique.creerIngredient("beurre", 50, Unite.g));
        r1.ajouterIngredient(Fabrique.creerIngredient("sucre", 1, Unite.sachet));
        r1.ajouterIngredient(Fabrique.creerIngredient("rhum", 1, Unite.cuillereSoupe));
        recettes.add(r1);
        
        IRecette r2 = Fabrique.creerRecette("Gauffre", "Mettre la farine dans un saladier, y ajouter le sucre, les jaunes d'œufs et le beurre ramolli. Délayer peu à peu le tout en y ajoutant le lait pour qu'il n'y ait pas de grumeaux. Battre les blancs en neige avec une pincée de sel et les ajouter au restant en remuant délicatement. Cuire le tout dans un gaufrier légèrement beurré.");
        r2.setDuree(17);
        r2.setDifficulte(Difficulte.Facile);
        r2.setPrix(Budget.Reduit);
        r2.ajouterIngredient(Fabrique.creerIngredient("farine", 200, Unite.g));
        r2.ajouterIngredient(Fabrique.creerIngredient("sucre", 30, Unite.g));
        r2.ajouterIngredient(Fabrique.creerIngredient("oeufs", 3, Unite.unite));        
        r2.ajouterIngredient(Fabrique.creerIngredient("beurre", 20, Unite.g));
        r2.ajouterIngredient(Fabrique.creerIngredient("lait", 250, Unite.mL));
        r2.ajouterIngredient(Fabrique.creerIngredient("sel", 1, Unite.pincee));
        recettes.add(r2);
        
        IRecette r3 = Fabrique.creerRecette("Pâte à pizza","Mettre 350 g de farine dans un grand saladier puis ajouter successivement le sel, la levure boulangère et l'huile d'olive. Verser petit à petit l'eau tiède tout en mélangeant avec une cuillère en bois. Remuer longuement jusqu'à obtention d'une pâte qui se détache du saladier. Laisser reposer la pâte pendant 1h en couvrant le saladier avec un torchon dans un endroit chaud.Après le temps de repos, déposer de la farine sur votre plan de travail y déposer la pâte à pizza. La travailler comme on travaillerait une pâte à pain. Façonner votre pizza aux dimensions de votre plaque à four où l'idéal, c'est de déposer la pâte sur du papier cuisson.");
        r3.setDuree(35);
        r3.setDifficulte(Difficulte.Moyen);
        r3.setPrix(Budget.Reduit);
        r3.ajouterIngredient(Fabrique.creerIngredient("farine", 350, Unite.g));
        r3.ajouterIngredient(Fabrique.creerIngredient("farine pour travailler la pâte", 100, Unite.g));
        r3.ajouterIngredient(Fabrique.creerIngredient("sel", 2, Unite.cuillere));
        r3.ajouterIngredient(Fabrique.creerIngredient("levure", 1, Unite.sachet));
        r3.ajouterIngredient(Fabrique.creerIngredient("huile d'olive", 3, Unite.cuillereSoupe));
        r3.ajouterIngredient(Fabrique.creerIngredient("eau tiède", 250, Unite.mL));
        recettes.add(r3);
        
        IRecette r4 = Fabrique.creerRecette("Pizza aux 3 fromages", "Etaler la pâte à pizza dans un plat à tarte, la piquer avec une fourchette, étaler le coulis de tomate sur la pâte. Découper des rondelles de fromage de chèvre et de mozzarella, découper aussi de fines tranches de roquefort. Placer le fromage en alternance (une tranche de fromage de chèvre, une de mozzarella, une de roquefort). Couvrir de gruyère râpé. Saler et poivrer selon les goûts.Mettre au four à Thermostat 7 (210°C), pendant 1/2 heure et plus si nécessaire.");
        r4.setDuree(40);
        r4.setDifficulte(Difficulte.Facile);
        r4.setPrix(Budget.Reduit);
        r4.ajouterIngredient(Fabrique.creerIngredient("pâte à pizza", 1, Unite.unite));
        r4.ajouterIngredient(Fabrique.creerIngredient("fromage de chèvre long", 1, Unite.unite));
        r4.ajouterIngredient(Fabrique.creerIngredient("roquefort", 1, Unite.unite));
        r4.ajouterIngredient(Fabrique.creerIngredient("mozzarella", 1, Unite.sachet));
        r4.ajouterIngredient(Fabrique.creerIngredient("gruyère râpé", 1, Unite.sachet));
        r4.ajouterIngredient(Fabrique.creerIngredient("coulis de tomate", 1, Unite.sachet));
        r4.ajouterIngredient(Fabrique.creerIngredient("poivre", 1, Unite.pincee));
        r4.ajouterIngredient(Fabrique.creerIngredient("sel", 1, Unite.pincee));
        recettes.add(r4);
        
        IRecette r5 = Fabrique.creerRecette("Ratatouille", "du texte", 80, Difficulte.Moyen, Budget.Moyen);
        r5.ajouterIngredients(Arrays.asList(Fabrique.creerIngredient("Aubergines", 350, Unite.g),Fabrique.creerIngredient("Courgettes", 350, Unite.g),Fabrique.creerIngredient("Oignons", 350, Unite.g),
        Fabrique.creerIngredient("Tomates bien mûres", 500, Unite.g),Fabrique.creerIngredient("Gousses d'ail", 3, Unite.unite),Fabrique.creerIngredient("Huile d'olive", 6, Unite.cuillereSoupe),
        Fabrique.creerIngredient("Thym", 1, Unite.brin),Fabrique.creerIngredient("Laurier", 1, Unite.feuille),Fabrique.creerIngredient("Poivre", 1, Unite.inconnu),Fabrique.creerIngredient("Sel", 1, Unite.inconnu)));
        recettes.add(r5);
        
        IRecette r6 = Fabrique.creerRecette("Hamburger", "du texte", 25, Difficulte.Facile, Budget.Reduit,Arrays.asList(Fabrique.creerIngredient("Pains spéciaux hamburgers", 4, Unite.unite), Fabrique.creerIngredient("Steaks hachés", 4, Unite.unite),
        Fabrique.creerIngredient("Oignon", 1, Unite.unite),Fabrique.creerIngredient("Fromages mimolette", 4, Unite.tranche),Fabrique.creerIngredient("Sauces", 1, Unite.inconnu),Fabrique.creerIngredient("Cornichons", 4, Unite.unite)));
        recettes.add(r6);
        
        IRecette r7 = Fabrique.creerRecette("Beuf bourguignon", "du texte", 360, Difficulte.Moyen, Budget.Moyen, Arrays.asList(Fabrique.creerIngredient("Viande de bourguignon", 600, Unite.g),Fabrique.creerIngredient("Oignons",4,Unite.unite),
        Fabrique.creerIngredient("Carottes", 4, Unite.unite),Fabrique.creerIngredient("Vin rouge", 1, Unite.bouteille),Fabrique.creerIngredient("Beurre", 100, Unite.g),Fabrique.creerIngredient("Poivre", 1, Unite.inconnu),Fabrique.creerIngredient("Sel", 1, Unite.inconnu)));
        recettes.add(r7);
        
        IRecette r8 = Fabrique.creerRecette("Sauce bolognaise", "de texte", 25, Difficulte.Facile, Budget.Reduit, Arrays.asList(Fabrique.creerIngredient("Beurre", 30, Unite.g),Fabrique.creerIngredient("Huile d'olive", 1, Unite.cuillereSoupe),
        Fabrique.creerIngredient("Coulis de tomate", 1, Unite.bouteille),Fabrique.creerIngredient("Viande hachée", 200, Unite.g),Fabrique.creerIngredient("Oignon", 1, Unite.inconnu),Fabrique.creerIngredient("Ail", 1, Unite.inconnu),
        Fabrique.creerIngredient("Poivre", 1, Unite.inconnu),Fabrique.creerIngredient("Sel", 1, Unite.inconnu)));
        recettes.add(r8);
        
        IRecette r9 = Fabrique.creerRecette("Cassoulet", "du texte", 240, Difficulte.Moyen, Budget.Moyen, Arrays.asList(Fabrique.creerIngredient("Haricot coco ou mojette de Vendée", 750, Unite.g),Fabrique.creerIngredient("Saucissons à l'ail", 1, Unite.tranche),Fabrique.creerIngredient("Saucisses de Toulouse non fumées", 8, Unite.unite),
        Fabrique.creerIngredient("Poitrines de porc fraîche", 4, Unite.tranche), Fabrique.creerIngredient("Poitrines de porc fumée", 4, Unite.tranche),Fabrique.creerIngredient("Cuisse de confits de canard", 4, Unite.unite),Fabrique.creerIngredient("Concentré de tomates", 70, Unite.g),Fabrique.creerIngredient("Chapelure", 1, Unite.inconnu),
        Fabrique.creerIngredient("Ail", 1, Unite.inconnu),Fabrique.creerIngredient("Thym", 1, Unite.inconnu),Fabrique.creerIngredient("Laurier", 1, Unite.inconnu),Fabrique.creerIngredient("Poivre", 1, Unite.inconnu),Fabrique.creerIngredient("Sel", 1, Unite.inconnu)));
        recettes.add(r9);
        
        IRecette r10 = Fabrique.creerRecette("Clafoutis aux cerises", "du texte", 55, Difficulte.Facile, Budget.Moyen, Arrays.asList(Fabrique.creerIngredient("Cerises bien mûres", 700, Unite.g),Fabrique.creerIngredient("Oeufs", 2, Unite.unite),Fabrique.creerIngredient("Jaune d'oeufs", 2, Unite.unite),
        Fabrique.creerIngredient("Farine", 100, Unite.g),Fabrique.creerIngredient("Sucre roux", 100, Unite.g),Fabrique.creerIngredient("Lait", 250, Unite.mL),Fabrique.creerIngredient("Beurre", 60, Unite.g),
        Fabrique.creerIngredient("Sel", 1, Unite.pincee),Fabrique.creerIngredient("Extrait de vanille", 1, Unite.cuillere),Fabrique.creerIngredient("Sucre vanillé", 1, Unite.sachet)));
    }

    /**
     * Définition de la fonction de chargement
     * @return la liste des recettes
     */
    @Override
    public List<IRecette> chargementRecettes() {
        return recettes;
    }

    /**
     * La fonction de sauvegarde des recettes, throws UnsupportedOperationException
     * @param recettes la liste des recettes
     */
    @Override
    public void sauvegardeRecettes(List<IRecette> recettes) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
