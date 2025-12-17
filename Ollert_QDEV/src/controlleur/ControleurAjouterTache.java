package controlleur;

import donnees.Colonne;
import donnees.Modele;
import donnees.Tache;
import donnees.TacheComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControleurAjouterTache implements EventHandler<ActionEvent> {

    private final Modele modele;
    private final Colonne colonneCourante;

    private final TextField tfTitre;
    private final TextArea taDescription;
    private final Spinner<Integer> spDuree;
    private final DatePicker dpDateDebut;

    private final ListView<TacheComposite> lvDependances;
    private final ListView<TacheComposite> lvSousTaches;

    public ControleurAjouterTache(Modele modele, Colonne colonneCourante, TextField tfTitre, TextArea taDescription, Spinner<Integer> spDuree, DatePicker dpDateDebut, ListView<TacheComposite> lvDependances, ListView<TacheComposite> lvSousTaches) {
        this.modele = modele;
        this.colonneCourante = colonneCourante;
        this.tfTitre = tfTitre;
        this.taDescription = taDescription;
        this.spDuree = spDuree;
        this.dpDateDebut = dpDateDebut;
        this.lvDependances = lvDependances;
        this.lvSousTaches = lvSousTaches;
    }

    @Override
    public void handle(ActionEvent event) {

        LocalDate localDate = dpDateDebut.getValue();
        Date dateDebut = (localDate == null)
                ? null
                : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Tache nouvelle = modele.ajouterTache(colonneCourante, tfTitre.getText(), taDescription.getText(), spDuree.getValue(), dateDebut);

        if (nouvelle == null) return;

        for (TacheComposite dep : lvDependances.getSelectionModel().getSelectedItems()) {
            modele.ajouterDependance(nouvelle, dep);
        }
    }

}
