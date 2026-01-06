package vues;

import controlleur.*;
import donnees.Colonne;
import donnees.Modele;
import donnees.Tache;
import donnees.TacheComposite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class VueBureau extends HBox implements StrategieModeAffichage {

    private static final int LARGEUR_COLONNE = 100;
    private static final double HAUTEUR_COLONNE = 400;
    private ControleurDragAndDrop dnd;
    private Colonne colonneEnModif;

    private static VueBureau instance;


    public VueBureau() {
        this.setSpacing(20);
    }

    public static VueBureau getInstance(){
        if(instance == null){
            instance = new VueBureau();
        }
        return instance;
    }

    @Override
    public Pane genererAffichage(Modele modele) {
        this.getChildren().clear();

        dnd = new ControleurDragAndDrop(modele);

        for(Colonne c :modele.getColonnes()){
            this.getChildren().add(genererColonne(c, modele));
        }

        //creation du controleur pour ajouter une colonne
        if(modele.getColonnes().size()< modele.getNB_MAX_COLONNES()){
            Button ajoutColonne = new Button("+");
            ajoutColonne.setMinHeight(25);
            ajoutColonne.setMaxHeight(25);
            ajoutColonne.setMinWidth(VueBureau.LARGEUR_COLONNE);
            EventHandler<ActionEvent> controleur = new ControleurAjouterColonne(modele);
            ajoutColonne.addEventHandler(ActionEvent.ACTION,controleur);
            this.getChildren().add(ajoutColonne);
        }

        return this;
    }

    private VBox genererColonne(Colonne c, Modele modele){
        //creation du conteneur principal
        VBox res = new VBox();
        res.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5), new BorderWidths(1))));
        res.setMaxHeight(VueBureau.HAUTEUR_COLONNE);

        //creation du conteneur qui va contenir les taches
        VBox vBox = new VBox();
        vBox.setPrefHeight(VueBureau.LARGEUR_COLONNE);
        vBox.setSpacing(10);



        //creation du controleur pour ajouter une tache a la colonne
        Button BajoutTache = new Button("+");
        BajoutTache.setMinHeight(25);
        BajoutTache.setMaxHeight(25);
        BajoutTache.setMinWidth(VueBureau.LARGEUR_COLONNE);
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PopupAddTache.display(modele, c);
            }
        };
        BajoutTache.addEventHandler(ActionEvent.ACTION,handler);



        //creation du scrollPane pour les taches
        ScrollPane scrollPane = new ScrollPane();
        //masquer les barres de scroll
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true); //pour adapter la taille des l'objet qu'il contient
        scrollPane.setContent(vBox);
        scrollPane.setMaxWidth(VueBureau.LARGEUR_COLONNE);
        scrollPane.setMinWidth(VueBureau.LARGEUR_COLONNE);
        scrollPane.setMinHeight(VueBureau.HAUTEUR_COLONNE);
        scrollPane.setMaxHeight(VueBureau.HAUTEUR_COLONNE);

        dnd.activerDropColonne(scrollPane, c);

        //ajout des taches dans le conteneur qui ets dans le scrollPane qui contient les taches
        for (Tache t : c.getListe()) {
            Label labelTache = genererTache(t);
            dnd.activerDragTache(labelTache, t);
            vBox.getChildren().add(labelTache);
        }

        //creation du titre de la colonne
        Control titre;
        if(c.equals(this.colonneEnModif)){
            titre = new TextField(c.getTitre());
            titre.addEventHandler(KeyEvent.KEY_PRESSED,new ControleurConfirmeTitreColonne(this,modele, c));
        }else{
            titre = new Label(c.getTitre());
            titre.addEventHandler(MouseEvent.MOUSE_CLICKED, new ControleurChangeTitreColonne(this, c, modele));
        }

        //ajout du titre et du scrollPane dans le conteneur principal
        res.getChildren().addAll(titre,BajoutTache,scrollPane);
        return res;
    }

    private Label genererTache(TacheComposite t){
        Label res = new Label(t.getTitre());
        res.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5), new BorderWidths(1))));
        res.setMaxHeight(50);
        res.setMinHeight(50);
        res.setMaxWidth(200);
        res.setAlignment(Pos.CENTER);

        res.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                new PopupDetailsTache().display(t);
            }
        });



        return res;
    }

    public void setColonneEnModif(Colonne colonneEnModif) {
        this.colonneEnModif = colonneEnModif;
    }
}
