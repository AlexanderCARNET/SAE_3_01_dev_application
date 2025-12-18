package vues;

import donnees.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestVue extends Application {

    Modele model;

    @Override
    public void start(Stage stage) throws Exception {
        Date date = new Date();

        Colonne c1 = new Colonne("Fini");
        c1.ajouteTache(new Tache("Database Design", "Archi", 2, date));

        Colonne c2 = new Colonne("en Cours");
        c2.ajouteTache(new Tache("ok", "Dev", 5, date));
        c2.ajouteTache(new Tache("Sleep", "Hobby", 5, date));

        Colonne c3 = new Colonne("A faire");
        c3.ajouteTache(new Tache("Testing", "QA", 1, date));



            model = new Modele();
            model.ajouterColonne(c1);
            model.ajouterColonne(c2);
            model.ajouterColonne(c3);



        model = Repository.getInstance().loadAll();

        VueTaches vueTaches = new VueTaches(model);
        model.ajouterObservateur(vueTaches);

        VueBureau vue = new VueBureau();

        vueTaches.setModeAffichage(vue);


        vueTaches.actualiser();

        VBox root = new VBox(vue);
        root.setFillWidth(true);
        vue.prefHeightProperty().bind(root.heightProperty());

        Scene scene = new Scene(root, 600, 400);
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
