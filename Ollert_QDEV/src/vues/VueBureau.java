package vues;

import donnees.Colonne;
import donnees.Modele;
import donnees.Tache;
import donnees.TacheComposite;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VueBureau extends HBox implements StrategieModeAffichage {

    Modele modele;

    public VueBureau() {

    }

    @Override
    public void genererAffichage(Modele model) {
        this.getChildren().clear();
        for(Colonne c :modele.getColonnes()){
            this.getChildren().add(genererColonne(c));
        }
    }

    private VBox genererColonne(Colonne c){
        VBox vBox = new VBox();

        vBox.setSpacing(10);
        vBox.setMinWidth(200);

        vBox.getChildren().add(new Label(c.getTitre()));

        for(TacheComposite t : c.getListe()){
            vBox.getChildren().add(genererTache(t));
        }

        return  vBox;
    }

    private Label genererTache(TacheComposite t){
        return new Label(t.getTitre());
    }

}
