package vues;

import donnees.Archive;
import donnees.Colonne;
import donnees.Modele;
import donnees.Tache;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

        VBox taches = new VBox(5);
        taches.setPadding(new Insets(10));

        for(Tache task:modele.getArchive().getTaches()){
            VBox tache = new VBox(5);
            tache.setPadding(new Insets(10));

            Label titre = new Label(task.getTitre());

            Label desc = new Label(task.getDescription());
            desc.setMinWidth(100);

            tache.getChildren().addAll(titre, desc);
            taches.getChildren().add(tache);
        }

        ScrollPane pane = new ScrollPane();
        pane.setContent(taches);
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
