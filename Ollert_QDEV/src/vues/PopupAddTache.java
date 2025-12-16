package vues;

import controlleur.ControleurAjouterTache;
import donnees.Colonne;
import donnees.Modele;
import donnees.TacheComposite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PopupAddTache {

    public static void display(Modele modele, Colonne colonne){
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
        tTitre.setPromptText("Ex: Faire les courses");

        Label lDesc = new Label("Description: ");
        TextArea tDesc = new TextArea();
        tDesc.setPrefRowCount(3);

        Label lDate = new Label("Date debut:");
        DatePicker dateDebut = new DatePicker(LocalDate.now());

        Label lDuree = new Label("Duree (jours): ");
        Spinner<Integer> duree = new Spinner<>(1, 365, 1);
        duree.setEditable(true);

        Label lDep = new Label("Dependances:");
        ListView<TacheComposite> dependances = new ListView<>();
        dependances.setPrefHeight(100);
        dependances.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (Colonne c : modele.getColonnes()) {
            dependances.getItems().addAll(c.getListe());
        }

        Label lSous = new Label("Sous-taches:");
        ListView<TacheComposite> sousTaches = new ListView<>();
        sousTaches.setPrefHeight(100);

        grid.add(lTitre, 0, 0);       grid.add(tTitre, 1, 0);
        grid.add(lDesc, 0, 1);        grid.add(tDesc, 1, 1);
        grid.add(lDuree, 0, 2);       grid.add(duree, 1, 2);
        grid.add(lDate, 0, 3);        grid.add(dateDebut, 1, 3);

        VBox boxListes = new VBox(5, lDep, dependances, lSous, sousTaches);
        grid.add(boxListes, 0, 4, 2, 1);

        Button btnAjouter = new Button("Ajouter");
        Button btnAnnuler = new Button("Annuler");
        HBox buttonBox = new HBox(10, btnAnnuler, btnAjouter);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttonBox, 1, 5);

        ControleurAjouterTache controller = new ControleurAjouterTache(
                modele,
                colonne,
                tTitre,
                tDesc,
                duree,
                dateDebut,
                dependances,
                sousTaches
        );

        btnAjouter.setOnAction(event -> {

            controller.handle(event);

            window.close();

            modele.notifier();
        });

        btnAnnuler.setOnAction(e -> window.close());

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }
}
