package donnees;

import java.io.Serializable;
import java.util.ArrayList;

public class Archive implements Serializable {

    private ArrayList<Tache> taches = new ArrayList<Tache>();

    public void ajouterTache(Tache tache) {
        taches.add(tache);
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public boolean contains(Tache tache){
        return this.taches.contains(tache);
    }

    public void supprimeTache(Tache tache){
        this.taches.remove(tache);
    }
}
