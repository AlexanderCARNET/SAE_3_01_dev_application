package controlleur;

import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vues.PopupSelectionTaches;

public class ControleurSelectionTache implements EventHandler<ActionEvent> {

    private Modele modele;
    public ControleurSelectionTache(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        PopupSelectionTaches popup = new PopupSelectionTaches(modele);
        modele.ajouterObservateur(popup);
        popup.display();
    }
}
