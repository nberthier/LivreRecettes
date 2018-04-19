package model;

import utils.RecetteComparateur;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe Livre permettant la gestion des recettes.
 * @author Clément
 */
public class Livre {
    
    /**
     * La collection de recettes
     */
    private ObservableList<IRecette> recettes = FXCollections.observableArrayList();
    /**
     * Recupère la collection de recettes sous forme de liste
     * @return recettes au format List de IRecette
     */
    public List<IRecette> getRecettes(){ return recettes.stream().collect(Collectors.toList()); }
    /**
     * Mutateur de la collection recettes
     * @param recettes la nouvelle collection
     */
    public final void setRecettes(List<IRecette> recettes) { this.recettes = FXCollections.observableArrayList(recettes); }
    /**
     * Accesseur de recettes
     * @return la collection observable de recettes
     */
    public ObservableList<IRecette> getRecettesObservable() { return recettes; }
    /**
     * Permet de connaître le nombre de recette dans le livre
     * @return entier
     */
    public int getNbRecettes(){ return getRecettesObservable().size(); }
    
    /**
     * Collection provisoire pour ne pas réeffectuer d'opération longue.
     */
    private ObservableList<IRecette> listeTemporaire;
    
    /**
     * Objet permettant la persistance des recettes
     */
    private DataManager dataManager;
    /**
     * Mutateur du DataManager
     * @param dataManager nouvelle instance de type DataManager
     */
    public final void setDataManager(DataManager dataManager) { this.dataManager = dataManager; }
    /**
     * Accesseur du DataManager
     * @return l'objet dataManager
     */
    public DataManager getDataManager() { return dataManager; }
    
    /**
     * Constructeur par défaut de la classe Livre
     * Initialise une liste de recettes vide
     */
    public Livre(){
        setRecettes(new ArrayList<>());
    }
    
    /**
     * Constructeur secondaire à favoriser
     * @param dataManager permettant la persistance
     */
    public Livre(DataManager dataManager){
        this();
        setDataManager(dataManager);
    }
    
    /**
     * Récupère une recette
     * @param nom à partir du nom de la recette
     * @return IRecette retourne une recette
     */
    public IRecette getRecette(String nom){
        return getRecettes().stream().filter(r->r.getNom().equals(nom)).findFirst().get();
    }
    
    /**
     * Récupère l'index d'une recette dans la liste des recettes
     * @param nom à partir du nom de la recette
     * @return int l'index
     */
    public int getRecetteIndex(String nom){
        return getRecettesObservable().indexOf(new Recette(nom, ""));
    }
    
    /**
     * Ajoute une recette dans la liste
     * @param recette la recette à ajouter
     */
    public void ajouterRecette(IRecette recette){
        if(!getRecettesObservable().contains(recette))
            getRecettesObservable().add(recette);
    }
    
    /**
     * Ajoute une liste de recette à la liste
     * @param recettes la liste des recettes
     */
    public void ajouterRecettes(List<IRecette> recettes){
        recettes.forEach(recette -> ajouterRecette(recette));
    }
    
    /**
     * Supprime une recette de la liste
     * @param recette la recette à supprimer
     */
    public void supprimerRecette(IRecette recette){
        getRecettes().remove(recette);
    }
    
    /**
     * Supprime une recette de la liste
     * @param index la position de la recette à supprimer dans la liste
     */
    public void supprimerRecette(int index){
        supprimerRecette(getRecettes().get(index));
    }
    
    /**
     * Modifie une recette
     * @param nomEx le nom de la recette à modifier
     * @param recette la recette contenant ses nouvelles valeurs
     * Remplace la recette par la nouvelle
     */
    public void modifierRecette(String nomEx, IRecette recette){
        int indexEx = getRecetteIndex(nomEx);
        getRecettesObservable().remove(indexEx);
        getRecettesObservable().add(indexEx, recette);
    }
    
    /**
     * Charge les recettes grâce à la persistance dans la liste
     * @throws Exception si une exception est levée par le dataManager durant le chargement, ou si aucun DataManager instancié
     */
    public void chargerRecettes() throws Exception{
        if(dataManager == null) throw new Exception("Aucun mode de persistance sélectionné !");
        List<IRecette> l = getDataManager().chargementRecettes();
        if(l.size() > 0){
            getRecettesObservable().clear();
            ajouterRecettes(l);
        }
    }
    
    /**
     * Sauvegarde la liste des recettes grâce à la persistance
     * @throws Exception si une exception est levée par le dataManager durant la sauvegarde, ou si aucun DataManager instancié
     */
    public void sauvegarderRecettes() throws Exception{
        if(dataManager == null) throw new Exception("Aucun mode de persistance sélectionné !");
        getDataManager().sauvegardeRecettes(getRecettes());
    }
    
    /**
     * Méthode permettant de rechercher les recettes contenant tous (ou presques tous) les éléments de la recette de recherche.
     * @param recherche la recette de recherche
     * <br>Passe toutes les recettes dans une liste temporaire pour avoir dans la liste de recettes celles correspondantes.
     */
    public void rechercherRecettes(IRecette recherche){
        if(listeTemporaire == null || listeTemporaire.size() == 0)
            listeTemporaire = FXCollections.observableArrayList(recettes);
        
        //System.out.println(recherche.recetteToString());
        List<IRecette> trouves = new ArrayList<>((recettes.isEmpty()?listeTemporaire:recettes));
        recettes.clear();
        
        ajouterRecettes(listeTemporaire.stream().filter((r) -> {
            boolean retour = RecetteComparateur.nomsRecettesEqual(r, recherche) || RecetteComparateur.nomsRecettesContenu(r, recherche);// || 
            retour &= recherche.getIngredients().stream().anyMatch(i -> RecetteComparateur.ingredientInRecette(i, r));
            return retour;
        }).collect(Collectors.toList()));
        
        if(recettes.isEmpty())
            ajouterRecettes(listeTemporaire.stream().filter(r -> RecetteComparateur.nomsRecettesEqual(r, recherche) || RecetteComparateur.nomsRecettesContenu(r, recherche) || recherche.getIngredients().stream().anyMatch(i -> RecetteComparateur.ingredientInRecette(i, r))).collect(Collectors.toList()));
    }
    
    /**
     * Méthode qui restaure les recettes dans la liste recettes.
     */
    public void restaurerRecettes(){
        if(listeTemporaire != null && listeTemporaire.size() > 0){
            recettes.clear();
            ajouterRecettes(listeTemporaire);
            listeTemporaire.clear();
        }
    }
    
    /**
     * Redéfinition de la fonction toString
     * @return La liste des recettes en String
     */
    @Override
    public String toString(){
        return getRecettes().toString();
    }

    /**
     * Méthode d'affichage des recettes du livre de manière plus détaillée
     * @return String correspondant au texte à afficher
     */
    public String livreToString(){
        String retour = "Livre de recettes :\n===================\n";
        if(getRecettes().size() < 1)
            retour += "Il n'y a aucune recette\n";
        retour = getRecettes().stream().map(recette -> recette.recetteToString() + "\n").reduce(retour, String::concat);
        return retour;
    }
}
