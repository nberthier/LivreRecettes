package model;

/**
 * Enumération correspondant à la difficulté de réalisation d'une recette
 * @author Clément
 */
public enum Difficulte {
    Inconnue,
    Facile,
    Moyen,
    Difficile,
    Expert;
    
    /**
     * Convertit une constante de l'énumération en entier
     * @return int la valeur équivalente
     */
    public int toInt(){
        int retour;
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
    
     /**
     * Permet de récupérer la constante depuis un entier
     * @param i l'entier
     * @return Difficulte la constante équivalente
     */
    public static Difficulte fromInt(int i){
        Difficulte b;
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
