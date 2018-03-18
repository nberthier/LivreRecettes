/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cb946032
 */
public enum Difficulte {
    Inconnue,
    Facile,
    Moyen,
    Difficile,
    Expert;
    
    public int toInt(){
        int retour = 0;
        switch(this){
            case Facile : 
                retour = 1;
                break;
            case Moyen : 
                retour = 2;
                break;
            case Difficile :
                retour = 3;
                break;
            case Expert :
                retour = 4;
                break;
            default :
                retour = 0;
                break;
        }
        return retour;
    }
    
    public static Difficulte fromInt(int i){
        Difficulte b = Difficulte.Inconnue;
        switch(i){
            case 1 :
                b = Difficulte.Facile;
                break;
            case 2 :                
                b = Difficulte.Moyen;
                break;
            case 3 :                
                b = Difficulte.Difficile;
                break;
            case 4 :                
                b = Difficulte.Expert;
                break;
            default :
                b = Difficulte.Inconnue;
                break;
        }
        return b;
    }
}
