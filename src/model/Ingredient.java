package model;

import utils.StringOperation;

/**
 * Classe Ingrédient
 * @author Clément
 */
class Ingredient implements IIngredient {
    
    /**
     * Le nom de l'ingrédient
     */
    private String nom;
    /**
     * Accesseur du nom
     * @return String le nom
     */
    @Override
    public String getNom(){ return nom; }
    /**
     * Mutateur du nom
     * @param nom le nom
     */
    @Override
    public final void setNom(String nom){ this.nom = nom; }
    
    /**
     * La quantite de l'ingrédient
     */
    private int quantite;
    /**
     * Mutateur de la quantite
     * @param quantite la valeur de la quantite
     */
    @Override
    public final void setQuantite(int quantite){
        if(quantite < 0) quantite *= -1;
        this.quantite = quantite; 
    }
    /**
     * Accesseur de la quantite de l'ingrédient
     * @return int la quantite
     */
    @Override
    public int getQuantite(){ return this.quantite; }
    
    /**
     * L'unité de la quantité de la recette
     */
    private Unite unite;
    /**
     * Mutateur de l'unite de la quantite
     * @param unite Constante de l'énumération Unite
     */
    @Override
    public final void setUnite(Unite unite){ this.unite = unite; }
    /**
     * Accesseur de l'unite
     * @return une constante de l'énumération Unite
     */
    @Override
    public Unite getUnite(){ return this.unite; }
    /**
     * Converti une constante de l'Unite en une String
     * @return String le nom de l'unite
     */
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

    /**
     * Constructeur d'un ingrédient
     * @param nom le nom
     * @param quantite la quantite
     * @param unite l'unite
     */
    public Ingredient(String nom, int quantite, Unite unite){
        setNom(nom);
        setQuantite(quantite);
        setUnite(unite);
    }
   
    /**
     * Constructeur par défaut d'un ingrédient
     */
    public Ingredient(){
        this("ingredient",0,Unite.unite);
    }
    
    /**
     * Redéfinition de la fonction toStrong
     * @return String texte d'affichage d'un ingrédient
     */
    @Override
    public String toString(){
        if(getUnite() != Unite.inconnu)
            return getNom() + " : " + getQuantite() + " " + getUniteToString();
        else
            return getNom();
    }
    
    /**
     * Redéfinition de la fonction hashCode
     * @return int le hash d'un ingrédient
     */
    @Override
    public int hashCode(){
        return StringOperation.transform(getNom(),StringOperation.WITHOUT_ACCENTS | StringOperation.LOWER_CASE).hashCode();
    }
 
    /**
     * Redéfinition de la fonction equals
     * @param o l'objet avec lequel comparer
     * @return boolean si égal ou non
     */
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof Ingredient)) return false;
        return this.equals((Ingredient)o);
    }
    
    /**
     * Test l'égalité avec un ingrédient
     * @param i l'ingrédient
     * @return boolean si égal ou non
     */
    public boolean equals(Ingredient i){
        return this.hashCode() == i.hashCode();
    }
    
    /**
     * Définition de la fonction clone pour copier un ingrédient
     * @return un nouvel ingrédient
     */
    @Override
    public Object clone(){
        Ingredient ing = null;
        try{ ing = (Ingredient) super.clone();
        }catch(CloneNotSupportedException e){}
        return ing;
    }
}
