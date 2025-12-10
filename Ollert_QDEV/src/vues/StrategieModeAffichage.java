package vues;

import donnees.Tache;

import java.util.List;

public interface StrategieModeAffichage {
    void genererAffichage(List<Tache> taches);
}
