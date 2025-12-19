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

    private Modele m;
    private VueBureau vueBureau;
    private Colonne c;

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
            this.vueBureau.setColonneEnModif(null); //on enleve la colonne du mode modification
            m.notifier();
        }
    }
}
