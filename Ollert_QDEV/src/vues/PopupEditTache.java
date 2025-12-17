package vues;

import controlleur.ControleurModifierTache;
import donnees.Modele;
import donnees.Tache;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PopupEditTache {

    public static void display(Modele modele, Tache tache){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nouvelle Tâche");
        window.setMinWidth(400);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(10);

        Label lTitre = new Label("Choisissiez le nom: ");
        TextField tTitre = new TextField();
        tTitre.setText(tache.getTitre());

        Label lDesc = new Label("Description: ");
        TextArea tDesc = new TextArea();
        tDesc.setText(tache.getDescription());
        tDesc.setPrefRowCount(3);

        Label lDate = new Label("Date debut:");
        DatePicker dateDebut = new DatePicker();

        Date dateDuModele = tache.getDateDebut();

        if (dateDuModele != null) {
            LocalDate dateModerne = dateDuModele.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            dateDebut.setValue(dateModerne);
        }

        Label lDuree = new Label("Duree (jours): ");
        Spinner<Integer> duree = new Spinner<>(1, 365, 1);
        duree.getValueFactory().setValue(tache.getDuree());
        duree.setEditable(true);

        grid.add(lTitre, 0, 0);       grid.add(tTitre, 1, 0);
        grid.add(lDesc, 0, 1);        grid.add(tDesc, 1, 1);
        grid.add(lDuree, 0, 2);       grid.add(duree, 1, 2);
        grid.add(lDate, 0, 3);        grid.add(dateDebut, 1, 3);

        Button modifier = new Button("Modifier");
        Button annuler = new Button("Annuler");
        HBox buttonBox = new HBox(10, annuler, modifier);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttonBox, 1, 5);

        ControleurModifierTache controller = new ControleurModifierTache(
                modele,
                tache,
                tTitre,
                tDesc,
                duree,
                dateDebut
        );

        modifier.setOnAction(event -> {

            controller.handle(event);

            window.close();

            modele.notifier();
        });

        annuler.setOnAction(e -> window.close());

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }
}
