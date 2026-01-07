package vues;

import controlleur.ControleurSelectionTache;
import controlleur.ControleurSelectionnerToutGantt;
import controlleur.ControlleurSelectionnerTacheGantt;
import donnees.Modele;
import donnees.Tache;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class PopupSelectionTaches implements Observateur {

    private VBox principale;
    private Modele m;
    private Stage window;

    public PopupSelectionTaches(Modele m) {
        this.m = m;
        this.principale = new VBox();
        this.window = new Stage();
    }

    public void display(){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Selection Taches pour Gantt");
        window.setMinWidth(400);

        this.m = m;
        this.actualiser();
        Scene scene = new Scene(principale);
        window.setScene(scene);
        window.showAndWait();
    }

    @Override
    public void actualiser() {

        List<Tache> taches = m.getTaches();
        List<Tache> tacheSelectionnees = m.getGantt().getSelection();


        principale.getChildren().clear();

        for (Tache tache : taches) {
            HBox ligneTache = new HBox();
            CheckBox cb = new CheckBox();
            cb.setOnAction(new ControlleurSelectionnerTacheGantt(m,tache));
            if(tacheSelectionnees.contains(tache)){
                cb.setSelected(true);
            }
            Label nom = new Label(tache.getTitre());
            ligneTache.getChildren().addAll(cb,nom);

            principale.getChildren().add(ligneTache);
        }

        HBox conteneurBarBas = new HBox();
        conteneurBarBas.setSpacing(20);

        Button boutonSelectionTout = new Button("Selection tout");
        boutonSelectionTout.setOnAction(new ControleurSelectionnerToutGantt(m));

        Button bouton = new Button("générer Diagramme");
        conteneurBarBas.getChildren().addAll(bouton, boutonSelectionTout);

        bouton.setOnAction(e -> {
            VueAccueil.getInstance().setVueGantt();
            window.close();
        });



        principale.getChildren().addAll(conteneurBarBas);
    }
}
