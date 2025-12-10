package vues;

import donnees.Tache;
import java.util.List;

public interface Observateur {
    void actualiser(List<Tache> taches);
}
