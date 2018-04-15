package models;

/**
 * Enumération correspondant au budget d'une recette
 * @author Clément
 */
public enum Budget {
    Inconnu,
    Reduit,
    Moyen,
    Eleve;
    
    /**
     * Convertit une constante de l'énumération en entier,
     * @return int la valeur équivalente
     */
    public int toInt(){
        int retour;
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
    
    /**
     * Permet de récupérer la constante depuis un entier
     * @param i l'entier
     * @return Budget la constante équivalente
     */
    public static Budget fromInt(int i){
        Budget b;
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
