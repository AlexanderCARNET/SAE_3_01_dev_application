package vues;

import donnees.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Date;
import java.util.List;

public class VueGantt extends Pane implements StrategieModeAffichage {

    private static final int HAUTEUR_LIGNE = 30;
    private static final int ECHELLE_JOUR = 20;

    @Override
    public Pane genererAffichage(Modele modele) {

        this.getChildren().clear();

        GanttCalculator calc = new GanttCalculator();

        List<Tache> toutesLesTaches = modele.getGantt().getSelection();

        List<GanttTask> gantt = calc.generer(toutesLesTaches);

        if (gantt.isEmpty()) return this;

        Date minDate = gantt.stream().map(GanttTask::getDebut).min(Date::compareTo).orElse(new Date());

        int y = 20;

        for (GanttTask gt : gantt) {

            long offsetJour = (gt.getDebut().getTime() - minDate.getTime()) / (1000 * 60 * 60 * 24);

            long dureeJour = (gt.getFin().getTime() - gt.getDebut().getTime()) / (1000 * 60 * 60 * 24);

            Rectangle barre = new Rectangle(offsetJour * ECHELLE_JOUR, y, Math.max(dureeJour, 1) * ECHELLE_JOUR, 20);

            barre.setFill(Color.DODGERBLUE);
            barre.setArcHeight(5);
            barre.setArcWidth(5);

            Label label = new Label(gt.getTache().getTitre());
            label.setLayoutX(barre.getX() + 5);
            label.setLayoutY(y - 15);

            this.getChildren().addAll(barre, label);
            y += HAUTEUR_LIGNE;
        }

        return this;
    }
}
