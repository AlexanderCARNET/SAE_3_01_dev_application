package vues;
import donnees.TacheComposite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(12);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(140);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        grid.getColumnConstraints().addAll(col1, col2);

        // Description
        Label lblDescTitre = new Label("Description :");
        lblDescTitre.setStyle("-fx-font-weight: bold;");

        Label lblDescription = new Label(tache.getDescription());
        lblDescription.setWrapText(true);
        lblDescription.setMinHeight(90);
        lblDescription.setAlignment(Pos.TOP_LEFT);
        lblDescription.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 8;" +
                        "-fx-border-color: #dddddd;"
        );

        Label lblDureeTitre = new Label("Durée :");
        lblDureeTitre.setStyle("-fx-font-weight: bold;");
        Label lblDuree = new Label(String.valueOf(tache.getDuree()));

        Label lblDateTitre = new Label("Date de début :");
        lblDateTitre.setStyle("-fx-font-weight: bold;");
        Label lblDate = new Label(tache.getDateDebut().toString());

        Label lblDepTitre = new Label("Dépendances :");
        lblDepTitre.setStyle("-fx-font-weight: bold;");
        Label lblDependances = new Label(
                String.valueOf(tache.getDependances().size())
        );




    }
    }


