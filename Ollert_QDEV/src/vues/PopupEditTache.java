package vues;

import controlleur.ControleurModifierTache;
import donnees.Colonne;
import donnees.Modele;
import donnees.Tache;
import donnees.TacheComposite;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PopupEditTache {

    public static void display(Modele modele, Tache tache){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modifier la Tâche");
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

        Label lDep = new Label("Dépendances :");

        List<TacheComposite> dependancesChoisies = new ArrayList<>(tache.getDependances());

        ListView<TacheComposite> listDependances = new ListView<>();
        listDependances.setPrefHeight(150);

        for(Colonne col : modele.getColonnes()) {
            for(Tache t : col.getListe()) {
                if(!t.equals(tache)) {
                    listDependances.getItems().add(t);
                }
            }
        }

        listDependances.setCellFactory(param -> new ListCell<TacheComposite>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(TacheComposite item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(item.getTitre());

                    checkBox.setSelected(dependancesChoisies.contains(item));



                    checkBox.setOnAction(e -> {
                        if (checkBox.isSelected()) {
                            if (!dependancesChoisies.contains(item)) {
                                dependancesChoisies.add(item);
                            }
                        } else {
                            dependancesChoisies.remove(item);
                        }
                    });

                    setGraphic(checkBox);
                }
            }
        });

        Label lSous = new Label("Sous-taches :");

        List<TacheComposite> sousChoisies = tache.getSousTaches();

        ListView<TacheComposite> sousTaches = new ListView<>();
        sousTaches.setPrefHeight(150);

        for(Colonne col : modele.getColonnes()) {
            for(Tache t : col.getListe()) {
                if(!t.equals(tache))
                    sousTaches.getItems().add(t);
            }
        }

        sousTaches.setCellFactory(param -> new ListCell<TacheComposite>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(TacheComposite item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(item.getTitre());

                    checkBox.setSelected(sousChoisies.contains(item));


                    checkBox.setOnAction(e -> {
                        if (checkBox.isSelected()) {
                            if (!sousChoisies.contains(item)) {
                                sousChoisies.add(item);
                            }
                        } else {
                            sousChoisies.remove(item);
                        }
                    });

                    setGraphic(checkBox);
                }
            }
        });

        grid.add(lTitre, 0, 0);       grid.add(tTitre, 1, 0);
        grid.add(lDesc, 0, 1);        grid.add(tDesc, 1, 1);
        grid.add(lDuree, 0, 2);       grid.add(duree, 1, 2);
        grid.add(lDate, 0, 3);        grid.add(dateDebut, 1, 3);
        grid.add(lDep, 0, 4);         grid.add(listDependances, 1, 4);
        grid.add(lSous, 0, 5);        grid.add(sousTaches, 1, 5);

        Button modifier = new Button("Modifier");
        Button annuler = new Button("Annuler");
        HBox buttonBox = new HBox(10, annuler, modifier);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttonBox, 1, 6);

        ControleurModifierTache controller = new ControleurModifierTache(
                modele,
                tache,
                tTitre,
                tDesc,
                duree,
                dateDebut,
                dependancesChoisies
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
