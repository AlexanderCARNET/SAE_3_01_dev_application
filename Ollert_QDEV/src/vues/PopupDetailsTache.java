package vues;
import donnees.TacheComposite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

        grid.add(lblDescTitre, 0, 0);
        grid.add(lblDescription, 1, 0);

        grid.add(lblDureeTitre, 0, 1);
        grid.add(lblDuree, 1, 1);

        grid.add(lblDateTitre, 0, 2);
        grid.add(lblDate, 1, 2);

        grid.add(lblDepTitre, 0, 3);
        grid.add(lblDependances, 1, 3);

        Button btnFermer = new Button("Fermer");
        btnFermer.setStyle(
                "-fx-background-color: #d9534f;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 8 18;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;"
        );

        btnFermer.setOnMouseEntered(e ->
                btnFermer.setStyle(
                        "-fx-background-color: #c9302c;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 8 18;" +
                                "-fx-background-radius: 6;" +
                                "-fx-cursor: hand;"
                )
        );



    }
    }


