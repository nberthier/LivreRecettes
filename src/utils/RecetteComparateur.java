package utils;

import model.IIngredient;
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
     * 
     * @param test
     * @param recherche
     * @return 
     */
    private static boolean contient(String test, String recherche){
        return test.contains(recherche);
    }
    
    /**
     * Test si les noms {@link #simplifie(String chaine) simplifiés} de chaque recette sont égaux
     * @param r1 la première recette
     * @param r2 la seconde
     * @return si les noms simplifié sont égaux
     */
    public static boolean nomsRecettesEqual(IRecette r1, IRecette r2){
        String nom1 = simplifie(r1.getNom()),
               nom2 = simplifie(r2.getNom());
        
        return nom1.equals(nom2);
    }
    
    /**
     * Test si les noms {@link #simplifie(String chaine) simplifiés} de chaque recette sont similaire
     * @param test la recette à rechercher
     * @param recherche la recette à laquelle comparer la recette recherchée
     * @return si le nom de la recette à rechercher est contenu dans celui de la recette à tester
     */
    public static boolean nomsRecettesContenu(IRecette test, IRecette recherche){
        if(nomsRecettesEqual(test, recherche)) return true;
        if(recherche.getNom() == null || recherche.getNom().length() == 0) return false;
        return contient(simplifie(test.getNom()),simplifie(recherche.getNom()));
    }
    
    /**
     * Test si les noms {@link #simplifie(String chaine) simplifiés} de chacun des ingrédients sont similaires
     * @param test premier ingrédient
     * @param recherche second ingrédient
     * @return si le nom de l'ingrédient 2 est contenu dans celui de l'ingrédient 1
     */
    private static boolean nomsIngredientsContenu(IIngredient test, IIngredient recherche){
        boolean retour = false; 
        String nom = recherche.getNom();
        int longueur = 0;
        do {
            retour = contient(simplifie(test.getNom()),simplifie(nom));
            if(nom.length() <= 5)
                longueur = ((int)nom.length()*4/5);
            else longueur = ((int)nom.length()*2/3);
            nom = nom.substring(0, longueur);
        }while (!retour && nom.length() > 3);
        return retour;
    }
    
    /**
     * Test si un ingrédiant est dans une recette
     * @param ingredient l'ingrédient à trouver
     * @param recette la recette dans laquelle chercher
     * @return si il y a ou non l'ingrédient
     */
    public static boolean ingredientInRecette(IIngredient ingredient, IRecette recette){
        return recette.getIngredients().stream().anyMatch((i) -> (nomsIngredientsContenu(i, ingredient)));
    }
}
