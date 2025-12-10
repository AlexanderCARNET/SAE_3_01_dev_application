package donnees;

import java.io.*;

public class Repository {
    private final static String nomFichierSauvegarde = "fichierSave.txt";

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private static Repository instance;

    private Repository() throws IOException {
        this.inputStream = new ObjectInputStream(new FileInputStream(Repository.nomFichierSauvegarde));
        this.outputStream = new ObjectOutputStream(new FileOutputStream(Repository.nomFichierSauvegarde));
        instance = this;
    }

    public static Repository getInstance() throws IOException {
        if(instance == null){
            return new Repository();
        }
        return instance;
    }
}