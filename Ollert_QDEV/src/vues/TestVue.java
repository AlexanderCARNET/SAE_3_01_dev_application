package vues;

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
        List<Tache> taches = new ArrayList<>();
        Date dataOggi = new Date();
        taches.add(new Tache("Database Design", "Archi", 2, dataOggi));
        taches.add(new Tache("Implementazione", "Dev", 5, dataOggi));
        taches.add(new Tache("Testing", "QA", 1, dataOggi));

        VueTaches vueTaches = new VueTaches(taches);

        VueListes strategiaLista = new VueListes();

        vueTaches.setModeAffichage(strategiaLista);

        vueTaches.actualiser(taches);

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
