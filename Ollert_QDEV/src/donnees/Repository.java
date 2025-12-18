package donnees;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Repository {
    private final static String nomFichierSauvegarde = "fichierSave.txt";

    private static Repository instance;

    private Repository() throws IOException {
        File file = new File(nomFichierSauvegarde);

        instance = this;
    }

    public static synchronized Repository getInstance() throws IOException {
        if(instance == null){
            return new Repository();
        }
        return instance;
    }

    public synchronized void saveAll(Modele m) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Repository.nomFichierSauvegarde));
        outputStream.writeObject(m);
        outputStream.close();
    }

    public synchronized Modele loadAll() throws IOException, ClassNotFoundException {
        try {

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(Repository.nomFichierSauvegarde));

            Modele m = (Modele) inputStream.readObject();

            if (m == null) {
                return null;
            }

            inputStream.close();

            return m;

        }
catch (FileNotFoundException e) {
            return null;
}

    }

    public synchronized static void creerFichier() throws IOException {
        File file = new File(nomFichierSauvegarde);
        if(!file.exists()) {
            file.createNewFile();
        }
    }

    public synchronized static void supprimerFichier(){
        File file = new File(nomFichierSauvegarde);
        if(file.exists()) {
            file.delete();
        }
    }

}