package vues;

import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VueAccueil extends HBox implements Observateur{

    private static final int LARGEUR_INTERFACE = 400;
    private static final double HAUTEUR_COLONNE = 50;
    private VueTaches vT;
    private Modele mod;

    public VueAccueil(Modele m){
        this.vT = new VueTaches(m);
        vT.setModeAffichage(new VueBureau());
        this.mod = m;
    }

    public VueAccueil(Modele m, String s){
        this.vT = new VueTaches(m);
        if(s.equals("Liste") || s.equals("Listes")){
            vT.setModeAffichage(new VueListes());
        }
        else {
            vT.setModeAffichage(new VueBureau());
        }
    }

    @Override
    public void actualiser() {
        this.genererAffichage();
        vT.actualiser();
    }

    public void genererAffichage(){
        this.getChildren().clear();
        Button Bureau = new Button("Bureau");
        Button Liste = new Button("Liste");

        Bureau.setMinHeight(25);
        Bureau.setMaxHeight(25);
        Liste.setMinHeight(25);
        Liste.setMaxHeight(25);
        this.getChildren().add(Bureau);
        this.getChildren().add(Liste);

        Liste.setOnAction( event -> {
            this.vT.setModeAffichage(new VueListes());
            this.mod.notifier();
            System.out.println("ok");
        });


    }
}
