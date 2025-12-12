package controlleur;

import donnees.Colonne;
import donnees.Tache;

import java.util.Date;

public class ControleurAjouterTache {

    private final Colonne colonne;

    public ControleurAjouterTache(Colonne colonne) {
        this.colonne = colonne;
    }

    public boolean ajouterTache(String titre, String description, int duree, Date dateDebut) {

        if (titre == null || titre.isBlank()) return false;
        if (duree < 0) return false;
        if (dateDebut == null) return false;

        Tache t = new Tache(titre, description, duree, dateDebut);

        colonne.ajouteTache(t);

        return true;
    }
}
