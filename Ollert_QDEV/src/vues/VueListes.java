package vues;

import donnees.Colonne;

import donnees.Modele;
import donnees.TacheComposite;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.DefaultStringConverter;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class VueListes extends TableView<TacheComposite> implements StrategieModeAffichage{

    private Map<TacheComposite, String> colonnes = new HashMap<>();
    private ObservableList<String> nomColonnes = FXCollections.observableArrayList();
    private Modele model;

    public VueListes() {
        super();
        this.setEditable(true);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<TacheComposite,String> colTache = new TableColumn<>("Tache");
        colTache.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<TacheComposite,String> colCol = new TableColumn<>("Colonne");
        colCol.setCellValueFactory(cellData -> {
            TacheComposite tache = cellData.getValue();
            String colonne = colonnes.get(tache);
            return new SimpleStringProperty(colonne);
        });

        colCol.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), nomColonnes));

        colCol.setOnEditCommit(event -> {
            String newCol = event.getNewValue();
            String oldCol = event.getOldValue();
            TacheComposite tache = event.getRowValue();

            this.colonnes.put(tache, newCol);

            deplacerTache(tache, newCol, oldCol);
        });

        colCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<TacheComposite, String> colDate = new TableColumn<>("Date debut");
        colDate.setCellValueFactory(cellData -> {
            TacheComposite tache = cellData.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(tache.getDateDebut());
            return new SimpleStringProperty(date);
        });
        colDate.setStyle("-fx-alignment: CENTER;");

        TableColumn<TacheComposite, Integer> colDuree = new TableColumn<>("Duree");
        colDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colDuree.setStyle("-fx-font-size: 15px; -fx-alignment: CENTER;");

        this.getColumns().addAll(colTache,colCol,colDate,colDuree);

    }

    @Override
    public void genererAffichage(Modele model) {
        this.model=model;

        this.colonnes.clear();

        ObservableList<TacheComposite> taches = FXCollections.observableArrayList();

        for (Colonne colonne : model.getColonnes()) {
            String nom = colonne.getTitre();

            this.nomColonnes.add(nom);
            for(TacheComposite tache : colonne.getListe()){
                taches.add(tache);

                this.colonnes.put(tache, nom);
            }
        }

        this.setItems(taches);
    }

    private void deplacerTache(TacheComposite tache, String newCol, String oldCol){
        for(Colonne colonne: this.model.getColonnes()){
            if(colonne.getTitre().equals(oldCol))
                colonne.supprimeTache(tache);
            if(colonne.getTitre().equals(newCol))
                colonne.ajouteTache(tache);
        }
    }
}
