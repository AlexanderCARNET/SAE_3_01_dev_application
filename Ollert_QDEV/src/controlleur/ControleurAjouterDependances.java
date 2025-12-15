package controlleur;

import donnees.TacheComposite;
import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurAjouterDependance implements EventHandler<ActionEvent> {

    private Modele modele;
    private ComboBox<TacheComposite> cbDependance;

    public ControleurAjouterDependance(Modele modele, ComboBox<TacheComposite> cb) {
        this.modele = modele;
        this.cbDependance = cb;
    }

    @Override
    public void handle(ActionEvent event) {
        TacheComposite dependance = cbDependance.getValue();
        modele.ajouterDependance(dependance);
    }
}

