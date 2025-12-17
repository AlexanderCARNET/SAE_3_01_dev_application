package vues;

import donnees.Modele;

import java.util.List;

public interface StrategieModeAffichage extends Observateur{
    void genererAffichage(Modele model);
}
