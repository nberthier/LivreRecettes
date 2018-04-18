package utils;

import model.IRecette;
import utils.StringOperation;

/**
 * Classe permettant de comparer des recettes entre elles suivant certains points.
 * @author Clément
 */
public class RecetteComparateur {
    
    /**
     * 
    /**
     * Recupère le nom simplifié pour effectuer de la comparaison.
     * <br>Sans accent et en minuscule.
     * @param chaine à simplifier
     * @return la chaîne simplifiée
     */
    private static String simplifie(String chaine){
        return StringOperation.transform(chaine,StringOperation.WITHOUT_ACCENTS | StringOperation.LOWER_CASE);
    }
    
    /**
     * Test si les noms {@link #simplifie(String chaine) simplifiés} de chaque recette sont égaux
     * @param r1 la première recette
     * @param r2 la seconde
     * @return si les noms simplifié sont égaux
     */
    public static boolean nomEqual(IRecette r1, IRecette r2){
        String nom1 = simplifie(r1.getNom()),
               nom2 = simplifie(r2.getNom());
        
        return nom1.equals(nom2);
    }
    
    /**
     * Test si les noms {@link #simplifie(String chaine) simplifiés} de chaque recette sont similaire
     * @param recherche la recette à rechercher
     * @param test la recette à laquelle comparer la recette recherchée
     * @return si le nom de la recette à rechercher est contenu dans celui de la recette à tester
     */
    public static boolean nomContenu(IRecette recherche, IRecette test){
        if(nomEqual(recherche, test)) return true;
        return simplifie(test.getNom()).contains(simplifie(recherche.getNom()));
    }
    
    
    
}
