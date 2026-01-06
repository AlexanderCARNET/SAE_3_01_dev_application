package controlleur;

import donnees.Colonne;
import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurSupprimerColonne implements EventHandler<ActionEvent> {
    private Modele modele;
    private Colonne courant;

    public ControleurSupprimerColonne(Modele modele, Colonne c) {
        this.modele = modele;
        this.courant = c;
    }

    @Override
    public void handle(ActionEvent a) {
        modele.supprimerColonne(courant);
        modele.notifier();
    }
}
