package vues;

import donnees.Colonne;
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
        List<Colonne> colonnes = new ArrayList<>();

        Colonne c1 = new Colonne("Fini");
        c1.ajouteTache(new Tache("Database Design", "Archi", 2, date));
        colonnes.add(c1);

        Colonne c2 = new Colonne("en Cours");
        c2.ajouteTache(new Tache("ok", "Dev", 5, date));
        c2.ajouteTache(new Tache("Sleep", "Hobby", 5, date));
        colonnes.add(c2);

        Colonne c3 = new Colonne("A faire");
        c3.ajouteTache(new Tache("Testing", "QA", 1, date));
        colonnes.add(c3);

        VueTaches vueTaches = new VueTaches(colonnes);

        VueListes strategiaLista = new VueListes();

        vueTaches.setModeAffichage(strategiaLista);

        vueTaches.actualiser(colonnes);

        VBox root = new VBox(strategiaLista);
        root.setFillWidth(true);
        strategiaLista.prefHeightProperty().bind(root.heightProperty());

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Test Pattern Strategy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
