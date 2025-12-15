package controlleur;

import donnees.TacheComposite;
import java.util.Date;

public class ControleurModifierTache {

    private final TacheComposite tache;

    public ControleurModifierTache(TacheComposite tache) {
        this.tache = tache;
    }

    public boolean modifierTache(String titre, String description, int duree, Date dateDebut) {

        if (titre == null || titre.isBlank()) return false;
        if (duree < 0) return false;
        if (dateDebut == null) return false;

        tache.setTitre(titre);
        tache.setDescription(description);
        tache.setDuree(duree);
        tache.setDateDebut(dateDebut);

        return true;
    }
}
