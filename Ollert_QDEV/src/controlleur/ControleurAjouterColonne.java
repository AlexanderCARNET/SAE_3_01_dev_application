package controlleur;
import donnees.Modele;
import exception.MaxColonneException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;


public class ControleurAjouterColonne implements EventHandler<ActionEvent> {

    private Modele modele;

    public ControleurAjouterColonne(Modele modele) {
        this.modele = modele;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            modele.ajouterColonne("Nouvelle colonne");
        } catch (MaxColonneException e) {
            //on ne fait rien
        }
    }
}
