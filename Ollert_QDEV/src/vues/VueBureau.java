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
        this.setStyle("-fx-background-color: #ffffff; -fx-padding: 10;");

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
            styliserBoutonPrincipal(ajoutColonne);

            this.getChildren().add(ajoutColonne);
        }

        return this;
    }

    private VBox genererColonne(Colonne c, Modele modele){
        //creation du conteneur principal
        VBox res = new VBox();
        res.setSpacing(8);
        styliserColonne(res);

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
        styliserBoutonPrincipal(BajoutTache);

        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PopupAddTache.display(modele, c);
            }
        };
        BajoutTache.addEventHandler(ActionEvent.ACTION,handler);
        Button BSupprimerColonne = new Button("X");
        BSupprimerColonne.setMinHeight(25);
        BSupprimerColonne.setMaxHeight(25);
        BSupprimerColonne.setMinWidth(VueBureau.LARGEUR_COLONNE/4.0);
        styliserBoutonAction(BSupprimerColonne, "#d9534f");

        EventHandler<ActionEvent> handlerSupp = new ControleurSupprimerColonne(modele, c);
        BSupprimerColonne.addEventHandler(ActionEvent.ACTION, handlerSupp);



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
        scrollPane.setStyle("-fx-background-color: transparent;");
        vBox.setStyle("-fx-background-color: transparent;");


        dnd.activerDropColonne(scrollPane, c);

        //ajout des taches dans le conteneur qui ets dans le scrollPane qui contient les taches
        for (Tache t : c.getListe()) {
            Label labelTache = genererTache(t);
            dnd.activerDragTache(labelTache, t);
            StackPane stack = new StackPane();
            stack.getChildren().add(labelTache);
            //bouton archiver
            Button BArchiver = new Button("X");
            BArchiver.setPrefWidth(this.LARGEUR_COLONNE/4);
            BArchiver.setPrefHeight(this.LARGEUR_COLONNE/4);
            stack.setAlignment(BArchiver, Pos.BOTTOM_RIGHT);
            styliserBoutonAction(BArchiver, "#d9534f");

            BArchiver.addEventHandler(ActionEvent.ACTION, new ControleurArchiverTache(modele, t));

            //bouton modifier
            Button Bmodifier = new Button("M");
            Bmodifier.setPrefWidth(this.LARGEUR_COLONNE/4);
            Bmodifier.setPrefHeight(this.LARGEUR_COLONNE/4);
            stack.setAlignment(Bmodifier, Pos.BOTTOM_LEFT);
            styliserBoutonAction(Bmodifier, "#0275d8");

            Bmodifier.setOnAction(e->{modele.gestionModification(t);});

            stack.getChildren().addAll(Bmodifier,BArchiver);
            vBox.getChildren().add(stack);
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
        res.getChildren().addAll(titre,BajoutTache,scrollPane, BSupprimerColonne);
        return res;
    }

    private Label genererTache(TacheComposite t){
        Label res = new Label(t.getTitre());
        res.setMaxHeight(50);
        res.setMinHeight(50);
        res.setMaxWidth(200);
        res.setAlignment(Pos.CENTER);

        styliserCarte(res);
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


    private void styliserColonne(VBox col) {
        col.setStyle(
                "-fx-background-color: #f7f7f7;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10;" +
                        "-fx-border-color: #d0d0d0;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;"
        );
    }

    private void styliserBoutonPrincipal(Button b) { // gros bouton + (ajouter)
        b.setStyle(
                "-fx-background-color: #2b2b2b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );
        b.setOnMouseEntered(e -> b.setStyle(
                "-fx-background-color: #3a3a3a;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        ));
        b.setOnMouseExited(e -> b.setStyle(
                "-fx-background-color: #2b2b2b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        ));
    }

    private void styliserBoutonAction(Button b, String bg) {
        b.setStyle(
                "-fx-background-color: " + bg + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 2 6;"
        );
        b.setOnMouseEntered(e -> b.setStyle(
                "-fx-background-color: derive(" + bg + ", -15%);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 2 6;"
        ));
        b.setOnMouseExited(e -> b.setStyle(
                "-fx-background-color: " + bg + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 2 6;"
        ));
    }

    private void styliserCarte(Label carte) {
        carte.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #d0d0d0;" +
                        "-fx-border-radius: 10;" +
                        "-fx-padding: 8;"
        );
    }

}
