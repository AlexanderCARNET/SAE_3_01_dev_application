package controlleur;

import donnees.Tache;
import donnees.TacheComposite;

import java.util.Date;

public class ControleurAjouterSousTache  {

    private final TacheComposite tacheParente;

    public ControleurAjouterSousTache(TacheComposite parent) {
        this.tacheParente = parent;
    }


    public boolean ajouterSousTache(String titre, String description, int duree, Date dateDebut) {

        if (titre == null || titre.isBlank()) return false;
        if (duree < 0) return false;
        if (dateDebut == null) return false;

        Tache nouvelle = new Tache(titre, description, duree, dateDebut);

        tacheParente.ajouterSousTache(nouvelle);

        return true;
    }
}


