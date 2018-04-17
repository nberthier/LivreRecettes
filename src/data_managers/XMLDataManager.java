package data_managers;

import model.DataManager;
import model.Fabrique;
import model.Unite;
import model.Budget;
import model.IRecette;
import model.IIngredient;
import model.Difficulte;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Persistance des données en XML
 * @author Clément
 */
public class XMLDataManager implements DataManager {
    
    /**
     * Le fichier de sauvegarde
     */
    private File file; // String file = "src/data_managers/sauvegarde.xml";
    /**
     * Mutateur de l'url du fichier de sauvegarde.
     * @param filePath le chemin du nouveau fichier
     */
    @Override
    public void setFile(String filePath){
        this.file = new File(filePath);
    }
    /**
     * Accesseur de l'url du fichier
     * @return le fichier
     */
    @Override
    public File getFile(){
        return this.file;
    }
    /**
     * La liste des recettes à récupérer
     */
    private List<IRecette> recettes;
    /**
     * Le document pour gérer le document XML
     */
    private Document document;
    
    /**
     * L'objet pour obtenir le DOM du fichier XML
     */
    DocumentBuilder docBuilder;
    /**
     * L'objet pour construire le DOM XML
     */
    DocumentBuilderFactory dbFactory;
 
    /**
     * Constructeur par défaut
     */
    public XMLDataManager(){
        this("src/data_managers/sauvegarde.xml");
    }
    
    /**
     * Constructeur utilisant un fichier XML choisi
     * @param fileUrl le chemin du fichier
     */
    public XMLDataManager(String fileUrl){
        setFile(fileUrl);
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();            
        }catch (ParserConfigurationException e){}
    }
    
    /**
     * Charge les recette depuis le fichier
     * @return la liste des recettes
     * @throws SAXException si erreur de parsing
     * @throws IOException si erreur durant entrée ou sortie flux de données
     */
    @Override
    public List<IRecette> chargementRecettes() throws SAXException, IOException{
        recettes = new ArrayList<>();
        
        document = docBuilder.parse(file);

        Element noeudRecettes = document.getDocumentElement();
        NodeList noeudsRecette = noeudRecettes.getChildNodes();

        for (int i = 0; i < noeudsRecette.getLength(); i++) {
            if(noeudsRecette.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element recette = (Element)noeudsRecette.item(i);
                String nom = recette.getElementsByTagName("nom").item(0).getTextContent();
                Budget budget = Budget.fromInt(Integer.parseInt(recette.getElementsByTagName("budget").item(0).getTextContent()));
                int duree = Integer.parseInt(recette.getElementsByTagName("duree").item(0).getTextContent());
                Difficulte difficulte = Difficulte.fromInt(Integer.parseInt(recette.getElementsByTagName("difficulte").item(0).getTextContent()));
                String etapes = recette.getElementsByTagName("etapes").item(0).getTextContent();

                IRecette r = Fabrique.creerRecette(nom, etapes, duree, difficulte, budget);

                Element noeudIngredients = (Element)recette.getElementsByTagName("ingredients").item(0);
                NodeList noeudsIngredient = noeudIngredients.getChildNodes();
                for(int j = 0; j < noeudsIngredient.getLength(); j++){
                    if(noeudsIngredient.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element ingredient = (Element)noeudsIngredient.item(j);
                        String nomIng = ingredient.getElementsByTagName("nom").item(0).getTextContent();
                        int quantite = Integer.parseInt(ingredient.getElementsByTagName("quantite").item(0).getTextContent());
                        Unite unite = Unite.fromInt(Integer.parseInt(ingredient.getElementsByTagName("unite").item(0).getTextContent()));

                        r.ajouterIngredient(Fabrique.creerIngredient(nomIng, quantite, unite));
                    }
                }

                recettes.add(r);
            }
        }
        
        return recettes;
    }

    /**
     * Sauvegarde les recettes en XML dans le fichier
     * @param recettes les recettes à sauvegarder
     * @throws javax.xml.transform.TransformerException si erreur durant transformation XML
     */
    @Override
    public void sauvegardeRecettes(List<IRecette> recettes) throws TransformerException {
        // élément de racine
        document = docBuilder.newDocument();
        Element racine = document.createElement("recettes");
        document.appendChild(racine);

        // pour chaque recette ajoute l'element XML à la racine
        recettes.forEach(recette -> racine.appendChild(this.recetteToXML(recette)));

        // écrit le contenu dans le fichier
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult resultat = new StreamResult(file);

        transformer.transform(source, resultat);
    }
    
    /**
     * Créer le DOM XML d'un ingrédient
     * @param produit l'ingrédient à convertir
     * @return représente l'ingrédient en XML
     */
    protected Element ingredientToXML(IIngredient produit){
        Element ingredient = document.createElement("ingredient");
        
        // le nom du produit
        Element nom = document.createElement("nom");
        nom.appendChild(document.createTextNode(produit.getNom()));
        ingredient.appendChild(nom);
        
        // la quantite
        Element quantite = document.createElement("quantite");
        quantite.appendChild(document.createTextNode(Integer.toString(produit.getQuantite())));
        ingredient.appendChild(quantite);
        
        // l'unite
        Element unite = document.createElement("unite");
        unite.appendChild(document.createTextNode(Integer.toString(produit.getUnite().toInt())));
        ingredient.appendChild(unite);
        
        return ingredient;
    }
    
    /**
     * Créer le DOM XML d'une recette
     * @param recette la recette à convertir
     * @return représente la recette en XML
     */
    protected Element recetteToXML(IRecette recette){
        // l'élément recette
        Element element = document.createElement("recette");

        // le nom
        Element nom = document.createElement("nom");
        nom.appendChild(document.createTextNode(recette.getNom()));
        element.appendChild(nom);

        // le budjet
        Element budjet = document.createElement("budget");
        budjet.appendChild(document.createTextNode(Integer.toString(recette.getPrix().toInt())));
        element.appendChild(budjet);

        // le budjet
        Element duree = document.createElement("duree");
        duree.appendChild(document.createTextNode(Integer.toString(recette.getDuree())));
        element.appendChild(duree);

        // le niveau de difficulté
        Element difficulte = document.createElement("difficulte");
        difficulte.appendChild(document.createTextNode(Integer.toString(recette.getDifficulte().toInt())));
        element.appendChild(difficulte);

        // le niveau de difficulté
        Element etapes = document.createElement("etapes");
        etapes.appendChild(document.createTextNode(recette.getRecette()));
        element.appendChild(etapes);

        // les ingrédients
        Element ingredients = document.createElement("ingredients");
        element.appendChild(ingredients);

        for(IIngredient ing : recette.getIngredients())
            ingredients.appendChild(this.ingredientToXML((IIngredient) ing));
        
        return element;
    }
    
}
