package controlleur;

import donnees.Modele;
import donnees.Tache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurSelectionnerToutGantt implements EventHandler<ActionEvent> {

    Modele m;

    public ControleurSelectionnerToutGantt(Modele m) {
        this.m = m;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        m.selectionnerTout();
    }
}
