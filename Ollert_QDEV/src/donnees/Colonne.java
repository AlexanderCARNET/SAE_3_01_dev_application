package donnees;

import java.io.Serializable;
import java.util.ArrayList;


public class Colonne implements Serializable {
    private String titre;
    private ArrayList<Tache> liste;
    private static final long serialVersionUID = 1L;


    public Colonne(String nom){
        this.liste = new ArrayList<>();
        this.titre = nom;
    }

    public void supprimeTache(Tache t){
        this.liste.remove(t);
    }

    public void ajouteTache(Tache t){
        this.liste.add(t);
    }

    public boolean contient(TacheComposite t) {
        return liste.contains(t);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<Tache> getListe() {
        return liste;
    }
}
