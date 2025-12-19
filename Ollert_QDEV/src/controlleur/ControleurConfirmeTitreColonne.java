package controlleur;

import donnees.Colonne;
import donnees.Modele;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import vues.VueBureau;

public class ControleurConfirmeTitreColonne implements EventHandler<KeyEvent> {

    Modele m;
    VueBureau vueBureau;
    Colonne c;

    public ControleurConfirmeTitreColonne(VueBureau vueBureau, Modele modele, Colonne c) {
        this.vueBureau=vueBureau;
        this.m=modele;
        this.c=c;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            TextField tf=(TextField)keyEvent.getSource();
            this.m.modifierNomColonne(c,tf.getText());
            this.vueBureau.colonneEnModif = null; //on remet enleve la colonne en mode modif
            m.notifier();
        }
    }
}
