package vues;

import donnees.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Date;

public class TestVue extends Application {

    Modele model;

    @Override
    public void start(Stage stage) throws Exception {
        Date date = new Date();

        model = Repository.getInstance().loadAll();

        if (model == null) {
            model = new Modele();
        }


        VueAccueil vue = new VueAccueil(model);
        model.ajouterObservateur(vue);

        vue.actualiser();

        VBox root = new VBox(vue);
        root.setFillWidth(true);
        vue.prefHeightProperty().bind(root.heightProperty());

        Scene scene = new Scene(root, 650, 650);
        stage.setTitle("Ollert");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Repository.getInstance().saveAll(model);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
