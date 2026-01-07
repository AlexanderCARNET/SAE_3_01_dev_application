package vues;
import donnees.TacheComposite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        String texteDeps;
        if (tache.getDependances().isEmpty()) {
            texteDeps = "Aucune";
        } else {
            StringBuilder sb = new StringBuilder();
            for (TacheComposite dep : tache.getDependances()) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(dep.getTitre());
            }
            texteDeps = sb.toString();
        }

        Label lblDependances = new Label(texteDeps);
        lblDependances.setWrapText(true);
        lblDependances.setAlignment(Pos.TOP_LEFT);
        lblDependances.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 8;" +
                        "-fx-border-color: #dddddd;"
        );





        Label lblSTTitre = new Label("Sous-Taches :");
        lblSTTitre.setStyle("-fx-font-weight: bold;");

        String texteST;
        if (tache.getSousTaches().isEmpty()) {
            texteST = "Aucune";
        } else {
            StringBuilder sb = new StringBuilder();
            for (TacheComposite dep : tache.getSousTaches()) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(dep.getTitre());
            }
            texteST = sb.toString();
        }

        Label lblST = new Label(texteST);
        lblST.setWrapText(true);
        lblST.setAlignment(Pos.TOP_LEFT);
        lblST.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 8;" +
                        "-fx-border-color: #dddddd;"
        );




        grid.add(lblDescTitre, 0, 0);
        grid.add(lblDescription, 1, 0);

        grid.add(lblDureeTitre, 0, 1);
        grid.add(lblDuree, 1, 1);

        grid.add(lblDateTitre, 0, 2);
        grid.add(lblDate, 1, 2);

        grid.add(lblDepTitre, 0, 3);
        grid.add(lblDependances, 1, 3);

        grid.add(lblSTTitre, 0, 4);
        grid.add(lblST, 1, 4);

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

        btnFermer.setOnMouseExited(e ->
                btnFermer.setStyle("-fx-background-color: #d9534f;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 8 18;" +
                                "-fx-background-radius: 6;" +
                                "-fx-cursor: hand;"
                )
        );



        btnFermer.setOnAction(e -> stage.close());

        HBox boxBtn = new HBox(btnFermer);
        boxBtn.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(
                lblTitre,
                new Separator(),
                grid,
                new Separator(),
                boxBtn
        );

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }
}

