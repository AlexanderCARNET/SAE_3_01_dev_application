package vues;

import donnees.Modele;
import javafx.scene.layout.Pane;

public interface StrategieModeAffichage{
    Pane genererAffichage(Modele model);
}
