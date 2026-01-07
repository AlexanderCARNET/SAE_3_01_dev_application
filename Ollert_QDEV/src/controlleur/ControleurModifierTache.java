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
        private final List<TacheComposite> nouvellesDependances;

        public ControleurModifierTache(Modele modele, /*ComboBox<TacheComposite>*/Tache cbTache,TextField tfTitre, TextArea taDescription, Spinner<Integer> spDuree, DatePicker dpDateDebut, List<TacheComposite> dependancesChoisies) {
            this.modele = modele;
            this.cbTache = cbTache;
            this.tfTitre = tfTitre;
            this.taDescription = taDescription;
            this.spDuree = spDuree;
            this.dpDateDebut = dpDateDebut;
            this.nouvellesDependances = dependancesChoisies;
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

            boolean ok = modele.remplacerDependances(cbTache, nouvellesDependances);

            if (!ok) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur dépendances");
                alert.setHeaderText("Cycle détecté");
                alert.setContentText("Les dépendances sélectionnées créent un cycle.");
                alert.showAndWait();
                return;
            }


            modele.modifierTache(cbTache/*cbTache.getValue()*/, tfTitre.getText(), taDescription.getText(), spDuree.getValue(), dateDebut
            );




        }
    }
