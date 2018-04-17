package launchers;

import data_managers.StubDataManager;
import data_managers.XMLDataManager;
import java.io.File;
import model.Livre;
import java.util.Scanner;

/**
 * Classe de l'application en console,
 * utile avant la création de la partie graphique
 * 
 * @author Clément
 */
public class Console {
    
    /**
     * Le mode de persistance en cours d'utilisation
     * <br>Attributs de classe permettant le bon fonctionnement.
     */
    private static String typeSauvegarde = "non sélectionné";
    /**
     * Le chemin du fichier en cours d'utilisation
     * <br>Attributs de classe permettant le bon fonctionnement. 
     */
    private static String fileUrl = "fichier non sélectionné";
    /**
     * Une instance de Livre
     */
    private static Livre livre = new Livre();
    
    /**
     * Fonction main de test
     * @param argc les arguments passés en argument de la ligne de commande
     */
    public static void main(String argc[]){
        System.out.println("Menu principale : ");
        actions();
    }
    
    /**
     * Fonction affichant le menu avec les différents choix d'action
     * @return int la valeur du choix réalisé
     */
    public static int menu(){
        System.out.println();
        System.out.println("1- Afficher les recettes");
        System.out.println("2- Ajouter une recette");
        System.out.println("3- Supprimer une recette");
        System.out.println("4- Charger les recettes");
        System.out.println("5- Sauvegarder les recettes");
        System.out.println("6- Modifier système de sauvegarde ("+typeSauvegarde+")");
        System.out.println("7- Changer le fichier de sauvegarde ("+fileUrl+")");
        System.out.println("0- Quitter");
        System.out.println();
        return choix();
    }
    
    /**
     * Demande le choix à réaliser,
     * il faut entrer un entier
     * @return int la valeur du choix
     */
    public static int choix(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Taper le numéro de l'action à effectuer :\t");
        return sc.nextInt();
    }
    
    /**
     * Gère les actions suivant celle choisie
     */
    public static void actions(){
        int choix;
        while((choix = menu()) > 0){
            System.out.println("");
            switch(choix){
                case 1 :
                    System.out.print(livre.livreToString());
                    break;
                case 4 :
                    try {
                        livre.chargerRecettes();
                        System.out.println("Chargement réalisé");
                    } catch(Exception e){
                        System.err.println(e.getMessage());
                        System.out.println("Erreur de chargement des recettes");
                    }
                    break;
                case 5 :
                    try {
                        livre.sauvegarderRecettes();
                        System.out.println("Fichier sauvegardé avec succès!");
                    } catch(Exception e){
                        System.err.println(e.getMessage());
                        System.out.println("Erreur d'enregistrement des recettes");
                    }
                    break;
                case 6 :
                    typeSauvegarde();
                    break;
                case 7 :
                    fichierSauvegarde();
                    break;
                default : 
                    System.out.println("Erreur ! Le numéro n'est pas valide, veuillez tapez un numéro valide");
            }
        }
    }
    
    /**
     * Pour le choix du fichier de persistance
     */
    public static void fichierSauvegarde(){
        if(livre.getDataManager() == null){
            System.out.println("Aucun DataManager défini");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est le chemin du fichier ?");
        livre.getDataManager().setFile(sc.nextLine());
        fileUrl = livre.getDataManager().getFile().getPath();
        //src/data_managers/test_de_sauv_dans_ce_fichier.xml
    }
    
    /**
     * Menu et choix du mode de persistance
     */
    public static void typeSauvegarde(){
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel type de sauvegarde voulez-vous utiliser ?");
        System.out.println("1- Stub");
        System.out.println("2- XML");
    
        while(!ok){
            ok = true;
            switch(sc.nextInt()){
                case 1 :
                    livre.setDataManager(new StubDataManager());
                    typeSauvegarde = "Stub";
                    break;
                case 2 :
                    livre.setDataManager(new XMLDataManager());
                    typeSauvegarde = "XML";
                    break;
                default :
                    System.out.println("Le numéro n'est pas valide. Veuillez reeffectuer\n");
                    ok = false;
            }
        }
    }
}
