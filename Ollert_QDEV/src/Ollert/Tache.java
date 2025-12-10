package Ollert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tache extends TacheComposite {

    protected List<TacheComposite> sousTaches = new ArrayList<>();


    public Tache(String titre, String description, int duree, Date debut) {
        super(titre, description, duree, debut);
    }

    public void ajouterSousTache(TacheComposite t) {
        sousTaches.add(t);
    }

    public void supprimerSousTache(TacheComposite t) {
        sousTaches.remove(t);
    }

    public List<TacheComposite> getSousTaches() {
        return sousTaches;
    }

}
