/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
     * 
     * @param nomPaquet le nom du paquet (ou package) dans lequel chercher les classes
     * @return La liste des classes trouvées
     * @throws ClassNotFoundException
     * @throws IOException 
     */
    public static List<Class> getClasses(String nomRepertoire) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = nomRepertoire.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL)resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, nomRepertoire));
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param repertoire le répertoire de base
     * @param nomRepertoire le nom du paquet (ou package) pour les classes trouver dans le répertoire
     * @return Les classes
     * @throws ClassNotFoundException
     */
    public static List findClasses(File repertoire, String nomRepertoire) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!repertoire.exists()) {
            return classes;
        }
        File[] files = repertoire.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, nomRepertoire + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(nomRepertoire + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
