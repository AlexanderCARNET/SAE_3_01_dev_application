package vues;

import donnees.Tache;

import javafx.collections.FXCollections;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class VueListes extends TableView<Tache> implements StrategieModeAffichage{

    public VueListes() {
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Tache,String> colTache = new TableColumn<>("Tache");
        colTache.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<Tache,String> colCol = new TableColumn<>("Colonne");
        colCol.setCellValueFactory(new PropertyValueFactory<>("colonne"));
        colCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tache, Date> colDate = new TableColumn<>("Date debut");
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDate.setStyle("-fx-alignment: CENTER;");
        colDate.setCellFactory(column -> new TableCell<Tache,  Date>() {
            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                }
                else {
                    setText(item.toString());
                }
            }
        });

        TableColumn<Tache, Integer> colDuree = new TableColumn<>("Duree");
        colDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colDuree.setStyle("-fx-font-size: 15px; -fx-alignment: CENTER;");
        colDuree.setCellFactory(column -> new TableCell<Tache, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item + " jours");
                }
            }
        });

        this.getColumns().addAll(colTache,colCol,colDate,colDuree);

    }

    @Override
    public void genererAffichage(){

    }
}
