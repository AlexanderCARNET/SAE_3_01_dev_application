package controlleur;

import donnees.Colonne;
import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControleurAjouterTache implements EventHandler<ActionEvent> {

    private Modele modele;
    private TextField tfTitre;
    private TextArea taDescription;
    private Spinner<Integer> spDuree;
    private DatePicker dpDateDebut;
    private ComboBox<Colonne> cbColonne;

    @Override
    public void handle(ActionEvent event) {

        LocalDate localDate = dpDateDebut.getValue();

        Date dateDebut = null;
        if (localDate != null) {
            dateDebut = Date.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );
        }

        modele.ajouterTache(cbColonne.getValue(), tfTitre.getText(), taDescription.getText(), spDuree.getValue(), dateDebut);
    }
}
