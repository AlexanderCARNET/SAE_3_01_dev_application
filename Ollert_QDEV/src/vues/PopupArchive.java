package vues;

import donnees.Modele;
import donnees.Tache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupArchive {

    public static void display(Modele modele){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Archive");
        window.setMinWidth(200);
        window.setMinHeight(400);

        TableView<Tache> table = new TableView<>();
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Tache,String> colTache = new TableColumn<>("Tache");
        colTache.setCellValueFactory(new PropertyValueFactory<>("titre"));

        table.getColumns().add(colTache);

        table.setRowFactory(ms->{
            TableRow<Tache> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem desarchiver = new MenuItem("Desarchiver");
            MenuItem supprimer = new MenuItem("Supprimer");

            desarchiver.setOnAction(event -> {
                Tache tache = row.getItem();
                modele.desarchiverTache(tache);
                modele.notifier();
                table.getItems().remove(tache);
            });

            supprimer.setOnAction(event -> {
                Tache tache = row.getItem();
                modele.supprimerArchiverTache(tache);
                modele.notifier();
                table.getItems().remove(tache);
            });

            if(modele.getColonnes().size()>0)
                contextMenu.getItems().addAll(desarchiver, supprimer);
            else
                contextMenu.getItems().add(supprimer);

            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(contextMenu)
            );
            return row;
        });

        ObservableList<Tache> taches = FXCollections.observableArrayList();

        taches.addAll(modele.getArchive().getTaches());

        table.setItems(taches);

        ScrollPane pane = new ScrollPane();
        pane.setContent(table);
        pane.setFitToWidth(true);

        Button exit = new Button("Exit");

        VBox component = new VBox();
        component.setPadding(new Insets(10));
        component.setSpacing(15);
        component.getChildren().addAll(pane, exit);

        exit.setOnAction( event -> window.close());

        Scene scene = new Scene(component);
        window.setScene(scene);
        window.showAndWait();
    }
}
