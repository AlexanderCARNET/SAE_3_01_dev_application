package donnees;

import java.io.*;
import java.util.ArrayList;

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
        this.outputStream = new ObjectOutputStream(new FileOutputStream(Repository.nomFichierSauvegarde));
        this.inputStream = new ObjectInputStream(new FileInputStream(Repository.nomFichierSauvegarde));
        instance = this;
    }

    public static synchronized Repository getInstance() throws IOException {
        if(instance == null){
            return new Repository();
        }
        return instance;
    }

    public void saveAll(Modele m) throws IOException {
        outputStream.writeObject(m.getArchive());
        for(Colonne col : m.getColonnes()){
            outputStream.writeObject(col);
        }
    }

    public ArrayList<Colonne> loadColonnes() throws IOException, ClassNotFoundException {
        ArrayList<Colonne> colonnes = new ArrayList<>();
        this.inputStream.readObject();// on passe l'objet archive qui est le premier objet du fichier
        while(inputStream.available() > 0) {
            colonnes.add((Colonne) inputStream.readObject());
        }
        return colonnes;
    }

    public Archive loadArchive() throws IOException, ClassNotFoundException {
        Archive archive;
        archive = ((Archive)this.inputStream.readObject());
        return archive;
    }
    public void closeConnexion() throws IOException {
        this.inputStream.close();
        this.outputStream.close();
        instance = null;
    }

    public static void creerFichier() throws IOException {
        File file = new File(nomFichierSauvegarde);
        if(!file.exists()) {
            file.createNewFile();
        }
    }

    public static void supprimerFichier(){
        File file = new File(nomFichierSauvegarde);
        if(file.exists()) {
            file.delete();
        }
    }

}