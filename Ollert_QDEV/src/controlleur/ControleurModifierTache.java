package controlleur;

import donnees.Modele;
import donnees.Tache;
import donnees.TacheComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControleurModifierTache implements EventHandler<ActionEvent> {

    private final Modele modele;
    //private final ComboBox<TacheComposite> cbTache;
    private final Tache cbTache;
    private final TextField tfTitre;
    private final TextArea taDescription;
    private final Spinner<Integer> spDuree;
    private final DatePicker dpDateDebut;

    public ControleurModifierTache(Modele modele, /*ComboBox<TacheComposite>*/Tache cbTache,TextField tfTitre, TextArea taDescription, Spinner<Integer> spDuree, DatePicker dpDateDebut) {
        this.modele = modele;
        this.cbTache = cbTache;
        this.tfTitre = tfTitre;
        this.taDescription = taDescription;
        this.spDuree = spDuree;
        this.dpDateDebut = dpDateDebut;
    }

    @Override
    public void handle(ActionEvent event) {

        LocalDate localDate = dpDateDebut.getValue();
        Date dateDebut = null;

        if (localDate != null) {
            dateDebut = Date.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );
        }

        modele.modifierTache(cbTache/*cbTache.getValue()*/, tfTitre.getText(), taDescription.getText(), spDuree.getValue(), dateDebut
        );
    }
}
