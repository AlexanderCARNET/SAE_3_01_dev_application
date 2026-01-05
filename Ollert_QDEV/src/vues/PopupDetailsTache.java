package vues;
import donnees.TacheComposite;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupDetailsTache {

    public void display(TacheComposite tache) {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Détails de la tâche");
        stage.setResizable(false);

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f7f7f7;");

        Label lblTitre = new Label(tache.getTitre());
        lblTitre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");




    }
    }


