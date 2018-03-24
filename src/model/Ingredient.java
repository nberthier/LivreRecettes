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
class Ingredient implements IIngredient {
    
    private String nom;
    public String getNom(){ return nom; }
    public void setNom(String nom){ this.nom = nom; }
    
    private int quantite;
    public void setQuantite(int quantite){
        if(quantite < 0) quantite *= -1;
        this.quantite = quantite; 
    }
    public int getQuantite(){ return this.quantite; }
    
    private Unite unite;
    public void setUnite(Unite unite){ this.unite = unite; }
    public Unite getUnite(){ return this.unite; }
    protected String getUniteToString(){ 
        String uniteStr = this.unite.toString();
        if(getQuantite() > 1 && !unite.isMeasureUnit()){
            int index = uniteStr.indexOf(" ");
            if(index != -1)
                uniteStr = new StringBuilder(uniteStr).insert(index, "s").toString();
            else uniteStr += "s";
        }
        return uniteStr ; 
    }

    public Ingredient(String nom, int quantite, Unite unite){
        setNom(nom);
        setQuantite(quantite);
        setUnite(unite);
    }
   
    public Ingredient(){
        this("ingredient",0,Unite.unite);
    }
    
    public String toString(){
        return getNom() + " : " + getQuantite() + " " + getUniteToString();
    }
    
    public int hashCode(){
        return getNom().toLowerCase().hashCode();
    }
 
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Ingredient)) return false;
        return this.equals((Ingredient)o);
    }
    
    public boolean equals(Ingredient i){
        return this.hashCode() == i.hashCode();
    }
    
    public Object clone(){
        Ingredient ing = null;
        try{
            ing = (Ingredient) super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace(System.err);
        }
        return ing;
    }
}
