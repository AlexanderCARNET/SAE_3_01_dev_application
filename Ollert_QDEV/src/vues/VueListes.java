package vues;

import donnees.Colonne;
import donnees.Tache;

import donnees.TacheComposite;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VueListes extends TableView<TacheComposite> implements StrategieModeAffichage{

    private Map<TacheComposite, String> colonnes = new HashMap<>();

    public VueListes() {
        super();
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<TacheComposite,String> colTache = new TableColumn<>("Tache");
        colTache.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<TacheComposite,String> colCol = new TableColumn<>("Colonne");
        colCol.setCellValueFactory(cellData -> {
            TacheComposite tache = cellData.getValue();
            String colonne = colonnes.get(tache);
            return new SimpleStringProperty(colonne);
        });
        colCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<TacheComposite, Date> colDate = new TableColumn<>("Date debut");
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDate.setStyle("-fx-alignment: CENTER;");
        colDate.setCellFactory(column -> new TableCell<TacheComposite, Date>() {
            private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setText(null);
                else setText(format.format(item));
            }
        });

        TableColumn<TacheComposite, Integer> colDuree = new TableColumn<>("Duree");
        colDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colDuree.setStyle("-fx-font-size: 15px; -fx-alignment: CENTER;");
        colDuree.setCellFactory(column -> new TableCell<TacheComposite, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) setText(null);
                else setText(item+"");
            }
        });

        this.getColumns().addAll(colTache,colCol,colDate,colDuree);

    }

    @Override
    public void genererAffichage(List<Colonne> colonnes) {

        this.colonnes.clear();

        ObservableList<TacheComposite> taches = FXCollections.observableArrayList();

        for (Colonne colonne : colonnes) {
            String nom = colonne.getTitre();
            for(TacheComposite tache : colonne.getListe()){
                taches.add(tache);

                this.colonnes.put(tache, nom);
            }
        }

        this.setItems(taches);
    }
}
