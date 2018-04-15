/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Classe permettant de trouver des classes
 * @author Clément
 */
public class ClassesManager {

    /**
     * Méthode pour récupérer les classes d'un paquet (ou package)
     * 
     * @param nomPaquet le nom du paquet (ou package) dans lequel chercher les classes
     * @return La liste des classes trouvées
     * @throws ClassNotFoundException si cette exception est levée dans une méthode utilisée
     * @throws IOException si cette exception est levée dans une méthode utilisée
     */
    public static List<Class> getClasses(String nomPaquet) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = nomPaquet.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL)resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, nomPaquet));
        }
        return classes;
    }

    /**
     * Méthode récursive utilisée pour trouver toutes les classes dans un répertoire et ses sous-répertoires.
     * 
     * @param repertoire le répertoire de base
     * @param nomPaquet le nom du paquet (ou package) pour les classes trouvées dans le répertoire
     * @return Les classes trouvées dans le répertoire
     * @throws ClassNotFoundException si une classe manipulée n'existe pas
     */
    public static List findClasses(File repertoire, String nomPaquet) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!repertoire.exists()) {
            return classes;
        }
        File[] files = repertoire.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, nomPaquet + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(nomPaquet + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
