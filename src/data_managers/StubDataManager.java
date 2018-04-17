package data_managers;

import model.DataManager;
import model.Fabrique;
import model.Unite;
import model.Budget;
import model.IRecette;
import model.Difficulte;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistance en "dur" dans le code
 * @author Clément
 */
public class StubDataManager implements DataManager{
    

    @Override
    public void setFile(String fileUrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFile() {
        return ""; // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void sauvegardeRecettes(List<IRecette> recettes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
