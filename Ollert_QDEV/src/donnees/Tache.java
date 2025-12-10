package donnees;

import java.util.Date;

public class Tache extends TacheComposite {

    public Tache(String titre, String description, int duree, Date debut) {
        super(titre, description, duree, debut);
    }
}
