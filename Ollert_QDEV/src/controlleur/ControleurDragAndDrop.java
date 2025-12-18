package controlleur;

import donnees.Colonne;
import donnees.Modele;
import donnees.TacheComposite;
import javafx.scene.Node;
import javafx.scene.input.*;

public class ControleurDragAndDrop {

    private final Modele modele;

    private TacheComposite tacheEnCours;

    public ControleurDragAndDrop(Modele modele) {
        this.modele = modele;
    }

    public void activerDragTache(Node sourceNode, TacheComposite tache) {
        sourceNode.setOnDragDetected(e -> {
            tacheEnCours = tache;

            Dragboard db = sourceNode.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("tache");
            db.setContent(content);

            db.setDragView(sourceNode.snapshot(null, null));
            e.consume();
        });
    }

    public void activerDropColonne(Node targetNode, Colonne colonneCible) {
        targetNode.setOnDragOver(e -> {
            e.acceptTransferModes(TransferMode.MOVE);
            e.consume();
        });

        targetNode.setOnDragDropped(e -> {
            if (tacheEnCours != null) {
                modele.deplacerTache(tacheEnCours, colonneCible);
                tacheEnCours = null;
                e.setDropCompleted(true);
            }
            e.consume();
        });
    }
}
