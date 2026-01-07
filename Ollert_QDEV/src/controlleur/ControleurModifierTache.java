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
import java.util.List;

public class ControleurModifierTache implements EventHandler<ActionEvent> {

    private final Modele modele;
    //private final ComboBox<TacheComposite> cbTache;
    private final Tache cbTache;
    private final TextField tfTitre;
    private final TextArea taDescription;
    private final Spinner<Integer> spDuree;
    private final DatePicker dpDateDebut;
    private final ListView<Tache> lvDependances;


    public ControleurModifierTache(
            Modele modele,
            Tache tache,
            TextField tfTitre,
            TextArea taDescription,
            Spinner<Integer> spDuree,
            DatePicker dpDateDebut,
            ListView<Tache> lvDependances
    ) {
        this.modele = modele;
        this.cbTache = tache;
        this.tfTitre = tfTitre;
        this.taDescription = taDescription;
        this.spDuree = spDuree;
        this.dpDateDebut = dpDateDebut;
        this.lvDependances = lvDependances;
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

        modele.modifierTache(cbTache/*cbTache.getValue()*/, tfTitre.getText(), taDescription.getText(), spDuree.getValue(), dateDebut);

        List<Tache> nouvellesDeps = lvDependances.getSelectionModel().getSelectedItems();

        cbTache.getDependances().removeIf(dep -> !nouvellesDeps.contains(dep));

        for (Tache dep : nouvellesDeps) {
            modele.ajouterDependance(cbTache, dep);
        }

    }
}
