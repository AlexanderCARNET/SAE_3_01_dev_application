package donnees;

import java.io.*;

public class Repository {
    private final static String nomFichierSauvegarde = "fichierSave.txt";

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private static Repository instance;

    private Repository() throws IOException {
        File file = new File(nomFichierSauvegarde);
        if(!file.exists()) {
            file.createNewFile();
        }
        this.inputStream = new ObjectInputStream(new FileInputStream(Repository.nomFichierSauvegarde));
        this.outputStream = new ObjectOutputStream(new FileOutputStream(Repository.nomFichierSauvegarde));
        instance = this;
    }

    public static synchronized Repository getInstance() throws IOException {
        if(instance == null){
            return new Repository();
        }
        return instance;
    }

    public void closeConnexion() throws IOException {
        this.inputStream.close();
        this.outputStream.close();
        instance = null;
    }

}