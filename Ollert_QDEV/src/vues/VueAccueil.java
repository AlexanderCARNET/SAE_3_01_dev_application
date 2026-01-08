package vues;

import controlleur.ControleurSelectionTache;
import donnees.Modele;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class VueAccueil extends VBox implements Observateur{

    private static final int LARGEUR_INTERFACE = 400;
    private static final double HAUTEUR_COLONNE = 50;
    private StrategieModeAffichage modeAffichage;
    private Modele model;
    private static VueAccueil instance;


    public VueAccueil(Modele modele){
        instance = this;
        this.modeAffichage = VueBureau.getInstance();
        this.model = modele;

        this.modeAffichage = VueBureau.getInstance();
        this.model = modele;


        this.setPadding(new Insets(10));

        HBox menu = new HBox();
        menu.setPadding(new Insets(5));
        menu.setSpacing(5);

        Button vBureau = new Button("Bureau");
        vBureau.setMaxWidth(100);
        vBureau.setOnAction(e -> setVueBureau());

        Button vListe = new Button("Liste");
        vListe.setMaxWidth(50);
        vListe.setOnAction( e -> setVueListe());

        Button archive = new Button("Archive");
        archive.setMaxWidth(80);
        archive.setOnAction( e -> afficherArchive());

        Button selectTachesGantt = new Button("Gantt");
        selectTachesGantt.setMaxWidth(80);
        selectTachesGantt.setOnAction(new ControleurSelectionTache(modele));

        styliserBoutonMenu(vBureau);
        styliserBoutonMenu(vListe);
        styliserBoutonMenu(archive);
        styliserBoutonMenu(selectTachesGantt);


        menu.getChildren().addAll(vBureau, vListe, archive, selectTachesGantt);

        this.getChildren().add(menu);


    }

    private void styliserBoutonMenu(Button b) {
        b.setMinHeight(32);
        b.setStyle(
                "-fx-background-color: #2b2b2b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 6 12;" +
                        "-fx-cursor: hand;"
        );

        b.setOnMouseEntered(e -> b.setStyle(
                "-fx-background-color: #3a3a3a;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 6 12;" +
                        "-fx-cursor: hand;"
        ));

        b.setOnMouseExited(e -> b.setStyle(
                "-fx-background-color: #2b2b2b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 6 12;" +
                        "-fx-cursor: hand;"
        ));
    }


    @Override
    public void actualiser() {
        if (this.getChildren().size() == 2) {
            this.getChildren().remove(1);
        }

        Pane pane = this.modeAffichage.genererAffichage(this.model);
        if (pane instanceof Region r) {
            r.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            VBox.setVgrow(r, Priority.ALWAYS);
        }


        if (this.modeAffichage instanceof VueGantt) {

            ScrollPane scroll = new ScrollPane(pane);
            scroll.setFitToHeight(false);
            scroll.setFitToWidth(false);
            scroll.setPannable(true);
            scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            this.getChildren().add(scroll);

        } else {
            this.getChildren().add(pane);
        }
    }




    private void setVueBureau(){
        ArrayList<Observateur> obs = this.model.getObservateurs();
        for(Observateur ob : obs){
            if(ob instanceof VueAccueil){
                ((VueAccueil) ob).setModeAffichage(VueBureau.getInstance());
            }
        }
        this.model.notifier();
    }

    private void setVueListe(){
        ArrayList<Observateur> obs = this.model.getObservateurs();
        for(Observateur ob : obs){
            if(ob instanceof VueAccueil){
                ((VueAccueil) ob).setModeAffichage(VueListes.getInstance());
            }
        }
        this.model.notifier();
    }

    public void setVueGantt(){
        this.modeAffichage = new VueGantt();
        actualiser();
    }

    public static VueAccueil getInstance(){
        return instance;
    }


    private void afficherArchive(){
        PopupArchive.display(this.model);
    }

    private void setModeAffichage(StrategieModeAffichage mode){
        this.modeAffichage = mode;
    }
}
