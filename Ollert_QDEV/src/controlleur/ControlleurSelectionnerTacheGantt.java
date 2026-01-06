package controlleur;

import donnees.Modele;
import donnees.Tache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class ControlleurSelectionnerTacheGantt implements EventHandler<ActionEvent> {

    private Modele m;

    private Tache t;

    public ControlleurSelectionnerTacheGantt(Modele m, Tache t) {
        this.m = m;
        this.t = t;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Object checkbox = actionEvent.getSource();
        if (checkbox instanceof CheckBox) {
            CheckBox cb = (CheckBox) checkbox;
            if (cb.isSelected()) {
                m.getGantt().selectionner(t);
            }else{
                m.getGantt().deselectionner(t);
            }
        }

    }
}
