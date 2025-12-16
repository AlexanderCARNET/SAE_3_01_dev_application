package controlleur;
import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;


public class ControleurAjouterColonne implements EventHandler<ActionEvent> {

    private Modele modele;
    private TextArea taTitre;

    @Override
    public void handle(ActionEvent actionEvent) {
        modele.ajouterColonne(taTitre.getText());
    }
}
