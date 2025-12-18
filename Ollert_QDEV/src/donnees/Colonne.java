package donnees;

import java.io.Serializable;
import java.util.ArrayList;


public class Colonne implements Serializable {
    private String titre;
    private ArrayList<TacheComposite> liste;
    private static final long serialVersionUID = 1L;


    public Colonne(String nom){
        this.liste = new ArrayList<TacheComposite>();
        this.titre = nom;
    }

    public void supprimeTache(TacheComposite t){
        this.liste.remove(t);
    }

    public void ajouteTache(TacheComposite t){
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

    public ArrayList<TacheComposite> getListe() {
        return liste;
    }
}
