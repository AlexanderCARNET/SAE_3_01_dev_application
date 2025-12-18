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

        super();

        this.setPadding(new Insets(10));

        HBox menu = new HBox();
        menu.setPadding(new Insets(5));
        menu.setSpacing(5);

        Button vBureau = new Button("Bur");
        vBureau.setMaxWidth(50);
        vBureau.setOnAction(e -> setVueBureau());

        Button vListe = new Button("List");
        vListe.setMaxWidth(50);
        vListe.setOnAction( e -> setVueListe());

        Button vGantt = new Button("Gantt");
        vGantt.setMaxWidth(50);
        vGantt.setOnAction(e -> setVueGantt());

        Button archive = new Button("Archive");
        archive.setMaxWidth(80);
        archive.setOnAction( e -> afficherArchive());

        menu.getChildren().addAll(vBureau, vListe, vGantt, archive);

        this.getChildren().add(menu);
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
        if(this.getChildren().size()==2){
            this.getChildren().remove(1);
        }
        Pane pane = this.modeAffichage.genererAffichage(this.model);
        this.getChildren().add(pane);
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

    private void setVueGantt(){
        return;
    }

    private void afficherArchive(){
        PopupArchive.display(this.model);
    }
}
