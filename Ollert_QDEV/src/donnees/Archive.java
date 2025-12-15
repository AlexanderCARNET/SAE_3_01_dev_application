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
}
