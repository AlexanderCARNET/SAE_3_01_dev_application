package vues;

import donnees.*;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.DefaultStringConverter;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class VueListes extends VBox implements StrategieModeAffichage{

    private TableView<Tache> table;
    private Map<TacheComposite, String> colonnes = new HashMap<>();
    private ObservableList<String> nomColonnes = FXCollections.observableArrayList();
    private Modele model;

    private static VueListes instance;

    public VueListes() {
        super(10);
        this.setPadding(new Insets(10));

        this.table = new TableView<>();
        this.table.setEditable(true);
        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(table, Priority.ALWAYS);

        creeTable();

        Button addTache = new Button("Ajouter une tache");
        addTache.setMaxWidth(150);
        addTache.setOnAction( e -> this.model.gestionAjout());

        this.table.setRowFactory(ms -> {
            TableRow<Tache> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem archiverTache = new MenuItem("Archiver");
            MenuItem modifierTache = new MenuItem("Modifier");

            modifierTache.setOnAction(event -> {
                Tache tache = row.getItem();
                this.model.gestionModification(tache);
            });

            archiverTache.setOnAction(event -> {
                Tache tache = row.getItem();
                this.model.gestionArchive(tache);
            });

            contextMenu.getItems().addAll(archiverTache, modifierTache);

            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(contextMenu)
            );

            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()
                        && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Tache tache = row.getItem();
                    new PopupDetailsTache().display((TacheComposite) tache);
                }
            });


            return row;
        });

        this.getChildren().addAll(table, addTache);
    }

    private void creeTable(){
        TableColumn<Tache,String> colTache = new TableColumn<>("Tache");
        colTache.setCellValueFactory(new PropertyValueFactory<>("titre"));

        TableColumn<Tache,String> colCol = new TableColumn<>("Colonne");
        colCol.setCellValueFactory(cellData -> {
            Tache tache = cellData.getValue();
            String colonne = colonnes.get(tache);
            return new SimpleStringProperty(colonne);
        });

        colCol.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), nomColonnes));

        colCol.setOnEditCommit(event -> {
            String newCol = event.getNewValue();
            String oldCol = event.getOldValue();
            Tache tache = event.getRowValue();

            this.colonnes.put(tache, newCol);

            this.model.deplacerTache(tache, newCol, oldCol);
        });

        colCol.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tache, String> colDate = new TableColumn<>("Date debut");
        colDate.setCellValueFactory(cellData -> {
            TacheComposite tache = cellData.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(tache.getDateDebut());
            return new SimpleStringProperty(date);
        });
        colDate.setStyle("-fx-alignment: CENTER;");

        TableColumn<Tache, Integer> colDuree = new TableColumn<>("Duree");
        colDuree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        colDuree.setStyle("-fx-font-size: 15px; -fx-alignment: CENTER;");

        TableColumn<Tache, Integer> colDependence = new TableColumn<>("Dependences");
        colDependence.setCellValueFactory( cellData -> {
            TacheComposite tache = cellData.getValue();
            int n = tache.getDependances().size();
            return new SimpleObjectProperty<>(n);
        });
        colDependence.setStyle("-fx-font-size: 15px; -fx-alignment: CENTER;");

        this.table.getColumns().addAll(colTache,colCol,colDate,colDuree, colDependence);
    }

    @Override
    public Pane genererAffichage(Modele model) {
        this.model = model;
        this.colonnes.clear();
        this.nomColonnes.clear();

        ObservableList<Tache> taches = FXCollections.observableArrayList();

        for (Colonne colonne : model.getColonnes()) {
            String nom = colonne.getTitre();

            this.nomColonnes.add(nom);
            for(Tache tache : colonne.getListe()){
                taches.add(tache);

                this.colonnes.put(tache, nom);
            }
        }

        this.table.setItems(taches);

        this.table.refresh();
        return this;
    }

    public static VueListes getInstance(){
        if(instance == null){
            instance = new VueListes();
        }
        return instance;
    }
}
