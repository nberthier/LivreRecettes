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
public enum Budget {
    Inconnu,
    Reduit,
    Moyen,
    Eleve;
    
    public int toInt(){
        int retour = 0;
        switch(this){
            case Reduit : 
                retour = 1;
                break;
            case Moyen : 
                retour = 2;
                break;
            case Eleve :
                retour = 3;
                break;
            default :
                retour = 0;
                break;
        }
        return retour;
    }
    
    public static Budget fromInt(int i){
        Budget b = Budget.Inconnu;
        switch(i){
            case 1 :
                b = Budget.Reduit;
                break;
            case 2 :                
                b = Budget.Moyen;
                break;
            case 3 :                
                b = Budget.Eleve;
                break;
            default :
                b = Budget.Inconnu;
                break;
        }
        return b;
    }
}
