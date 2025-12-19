package controlleur;

import donnees.Colonne;
import donnees.Modele;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import vues.VueBureau;

public class ControleurChangeTitreColonne implements EventHandler<MouseEvent> {

    private VueBureau vueBureau;
    private Colonne c;
    private Modele m;

    public ControleurChangeTitreColonne(VueBureau vueBureau, Colonne c, Modele m){
        this.vueBureau=vueBureau;
        this.c=c;
        this.m=m;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            this.vueBureau.setColonneEnModif(c);
            this.m.notifier();
        }
    }
}
