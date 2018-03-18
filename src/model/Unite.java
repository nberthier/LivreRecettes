/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author cb946032
 */
public enum Unite {
    unite ("", true),
    g ("g", true),
    mL ("mL", true),
    L ("L", true),
    recette ("", true),
    cuillere ("cuillère"),
    cuillereSoupe ("cuillère à soupe"),
    tranche ("tranche"),
    morceau ("morceau"),
    pincee ("pincee"),
    sachet ("sachet"),
    pot ("pot");

    private String valeur;
    private boolean measureUnit;
    public boolean isMeasureUnit(){
        return measureUnit;
    }
    
    Unite(String valeur) {
        this(valeur, false);
    }
    
    Unite(String valeur, boolean mesure){
        this.valeur = valeur;
        this.measureUnit = mesure;
    }
    
    private static Map<Integer, Unite> map = new HashMap<Integer, Unite>();
    static {
        int i = 0;
        for(Unite unite : Unite.values()){
            map.put(i,unite);
            i++;
        }
    }
    
    public String toString(){
        return valeur;
    }
    
    public int toInt(){
        for(Entry<Integer, Unite> entry : map.entrySet())
            if(this.equals(entry.getValue()))
                return entry.getKey();

        return 0;
    }
    
    public static Unite fromInt(int i){
        return map.get(i);
    }
}
