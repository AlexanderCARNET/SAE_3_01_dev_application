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
        this.outputStream = new ObjectOutputStream(new FileOutputStream(Repository.nomFichierSauvegarde));
        outputStream.writeObject(m.getArchive());
        for(Colonne col : m.getColonnes()){
            outputStream.writeObject(col);
        }
        this.outputStream.close();
    }

    public ArrayList<Colonne> loadColonnes() throws IOException, ClassNotFoundException {
        this.inputStream = new ObjectInputStream(new FileInputStream(Repository.nomFichierSauvegarde));
        ArrayList<Colonne> colonnes = new ArrayList<>();
        this.inputStream.readObject();// on passe l'objet archive qui est le premier objet du fichier
        boolean finFichier = false;
        while(!finFichier) {
            try {
                colonnes.add((Colonne) inputStream.readObject());
            }catch(EOFException e){
                finFichier = true;
            }
        }
        this.inputStream.close();
        return colonnes;
    }

    public Archive loadArchive() throws IOException, ClassNotFoundException {
        this.inputStream = new ObjectInputStream(new FileInputStream(Repository.nomFichierSauvegarde));
        Archive archive;
        archive = ((Archive)this.inputStream.readObject());
        this.inputStream.close();
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