package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Enumération pour l'unité de la quantité d'un ingrédient
 * @author Clément
 */
public enum Unite {
    inconnu ("", true),
    unite ("", true),
    g ("g", true),
    mL ("mL", true),
    L ("L", true),
    cuillere ("cuillère"),
    cuillereSoupe ("cuillère à soupe"),
    tranche ("tranche"),
    morceau ("morceau"),
    pincee ("pincee"),
    sachet ("sachet"),
    pot ("pot"),
    brin("brin"),
    feuille("feuille"),
    bouteille("bouteille");

    /**
     * La chaîne de carctère de l'unité
     */
    private String valeur;
    
    /**
     * Le booléen si l'unité est une unité et non un objet :
     * <br>    - true  : si une unité
     * <br>    - false : si un objet et donc pas une unité
     */
    private boolean measureUnit;
    /**
     * Accesseur si l'unite est une unité de mesure conventionnel
     * @return boolean si ça l'est ou non
     */
    public boolean isMeasureUnit(){
        return measureUnit;
    }
    
    /**
     * Constructeur d'une constante unité
     * @param valeur le nom de l'unité
     */
    Unite(String valeur) {
        this(valeur, false);
    }
    
    /**
     * Constructeur d'une unite
     * @param valeur le nom
     * @param mesure si c'est une unité de mesure ou non
     */
    Unite(String valeur, boolean mesure){
        this.valeur = valeur;
        this.measureUnit = mesure;
    }
    
    /**
     * Permet d'assigner un entier à chaque constante de l'unité
     */
    private static Map<Integer, Unite> map = new HashMap<Integer, Unite>();
    static {
        int i = 0;
        for(Unite unite : Unite.values()){
            map.put(i,unite);
            i++;
        }
    }
    
    /**
     * Redéfinition de la fonction toString()
     * @return String le nom
     */
    @Override
    public String toString(){
        return valeur;
    }
    
    /**
     * Recupère l'entier associé à une des constantes de l'unité
     * @return l'entier associé à une constante
     */
    public int toInt(){
        for(Entry<Integer, Unite> entry : map.entrySet())
            if(this.equals(entry.getValue()))
                return entry.getKey();

        return 0;
    }
    
    /**
     * Recupère l'unité associé à l'entié
     * @param i l'entier
     * @return la constante de l'énumération Unite
     */
    public static Unite fromInt(int i){
        return map.get(i);
    }
}
