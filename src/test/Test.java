/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.*;
import java.util.Scanner;
import model.*;

/**
 * Classe de test en console,
 * utile avant la création de la partie graphique
 * 
 * @author Clément
 */
public class Test {
    
    /**
     * Attributs de classe permettant le bon fonctionnement
     */
    private static String typeSauvegarde = "non sélectionné";
    private static Livre l = new Livre();
    
    /**
     * Fonction main de test
     * @param argc les arguments passés en argument de la ligne de commande
     */
    /*public static void main(String argc[]){
        System.out.println("Menu principale : ");
        actions();
    }*/
    
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
                    System.out.print(l);
                    break;
                case 4 :
                    if(l.chargerRecettes())
                        System.out.println("Chargement réalisé");
                    else
                        System.out.println("Erreur de chargement des recettes");
                    break;
                case 5 :
                    if(l.sauvegarderRecettes())
                        System.out.println("Sauvegarde réussie");
                    else
                        System.out.println("Erreur d'enregistrement des recettes");
                    break;
                case 6 :
                    typeSauvegarde();
                    break;
                default : 
                    System.out.println("Erreur ! Le numéro n'est pas valide, veuillez tapez un numéro valide");
            }
        }
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
                    l.setDataManager(new StubDataManager());
                    typeSauvegarde = "Stub";
                    break;
                case 2 :
                    l.setDataManager(new XMLDataManager());
                    typeSauvegarde = "XML";
                    break;
                default :
                    System.out.println("Le numéro n'est pas valide. Veuillez reeffectuer\n");
                    ok = false;
            }
        }
    }
}
