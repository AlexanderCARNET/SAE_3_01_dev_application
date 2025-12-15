package vues;

import donnees.Colonne;
import donnees.Modele;
import donnees.Tache;
import donnees.TacheComposite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestVue extends Application {

    @Override
    public void start(Stage stage){
        Date date = new Date();

        Colonne c1 = new Colonne("Fini");
        c1.ajouteTache(new Tache("Database Design", "Archi", 2, date));

        Colonne c2 = new Colonne("en Cours");
        c2.ajouteTache(new Tache("ok", "Dev", 5, date));
        c2.ajouteTache(new Tache("Sleep", "Hobby", 5, date));

        Colonne c3 = new Colonne("A faire");
        c3.ajouteTache(new Tache("Testing", "QA", 1, date));

        Modele model = new Modele();
        model.ajouterColonne(c1);
        model.ajouterColonne(c2);
        model.ajouterColonne(c3);

        VueTaches vueTaches = new VueTaches(model);
        model.ajouterObservateur(vueTaches);

        VueListes vue = new VueListes();

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

    public static void main(String[] args) {
        launch(args);
    }
}
