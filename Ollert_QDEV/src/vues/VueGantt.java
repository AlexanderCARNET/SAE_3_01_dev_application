package vues;

import donnees.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VueGantt extends Pane implements StrategieModeAffichage {

    private static final int HAUTEUR_LIGNE = 30;
    private static final int ECHELLE_JOUR = 60;

    @Override
    public Pane genererAffichage(Modele modele) {

        this.getChildren().clear();

        GanttCalculator calc = new GanttCalculator();

        List<Tache> toutesLesTaches = modele.getGantt().getSelection();

        List<GanttTask> gantt = calc.generer(toutesLesTaches);

        if (gantt.isEmpty()) return this;

        Date minDate = gantt.stream().map(GanttTask::getDebut).min(Date::compareTo).orElse(new Date());

        int y = 40;

        long maxJour = gantt.stream().map(gt -> (gt.getFin().getTime() - minDate.getTime()) / (1000 * 60 * 60 * 24)).max(Long::compare).orElse(0L);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

        for (int jour = 0; jour <= maxJour; jour++) {

            double x = jour * ECHELLE_JOUR;

            Date date = new Date(minDate.getTime()
                    + jour * 24L * 60 * 60 * 1000);

            Label lblDate = new Label(sdf.format(date));

            lblDate.setLayoutX(x + 2);
            lblDate.setLayoutY(0);
            lblDate.setStyle("-fx-font-size: 10px;");

            this.getChildren().add(lblDate);

        }


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

        double largeurTotale = (maxJour + 1) * ECHELLE_JOUR + 100;
        double hauteurTotale = y + 50;

        this.setPrefSize(largeurTotale, hauteurTotale);

        for (int jour = 0; jour <= maxJour; jour++) {

            double x = jour * ECHELLE_JOUR;

            Rectangle ligne = new Rectangle(x, 0, 1, this.getPrefHeight() + hauteurTotale);
            ligne.setFill(Color.LIGHTGRAY);

            this.getChildren().add(ligne);
        }

        return this;
    }
}
