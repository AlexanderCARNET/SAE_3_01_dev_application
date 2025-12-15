package controlleur;

import donnees.TacheComposite;
import donnees.Modele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurAjouterDependances implements EventHandler<ActionEvent> {

    private final Modele modele;
    private final ComboBox<TacheComposite> cbCible;
    private final ComboBox<TacheComposite> cbDependance;

    public ControleurAjouterDependances(Modele modele, ComboBox<TacheComposite> cbCible, ComboBox<TacheComposite> cbDependance) {
        this.modele = modele;
        this.cbCible = cbCible;
        this.cbDependance = cbDependance;
    }

    @Override
    public void handle(ActionEvent event) {
        TacheComposite cible = cbCible.getValue();
        TacheComposite dependance = cbDependance.getValue();
        modele.ajouterDependance(cible, dependance);
    }
}


