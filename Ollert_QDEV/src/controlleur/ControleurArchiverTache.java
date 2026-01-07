package controlleur;

import donnees.Modele;
import donnees.Tache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurArchiverTache implements EventHandler<ActionEvent> {
    Modele modele;
    Tache tache;

    public ControleurArchiverTache(Modele m, Tache t){
        this.modele = m;
        this.tache = t;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.modele.gestionArchive(tache);
    }
}
