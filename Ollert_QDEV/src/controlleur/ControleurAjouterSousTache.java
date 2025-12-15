package controlleur;

import donnees.Modele;
import donnees.TacheComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurAjouterSousTache implements EventHandler<ActionEvent> {

    private final Modele modele;
    private final ComboBox<TacheComposite> cbParente;
    private final ComboBox<TacheComposite> cbSousTache;

    public ControleurAjouterSousTache(Modele modele, ComboBox<TacheComposite> cbParente, ComboBox<TacheComposite> cbSousTache) {

        this.modele = modele;
        this.cbParente = cbParente;
        this.cbSousTache = cbSousTache;
    }

    @Override
    public void handle(ActionEvent event) {

        TacheComposite parent = cbParente.getValue();
        TacheComposite sousTache = cbSousTache.getValue();

        if (parent == null || sousTache == null) return;

        modele.ajouterSousTache(parent, sousTache);
    }
}
