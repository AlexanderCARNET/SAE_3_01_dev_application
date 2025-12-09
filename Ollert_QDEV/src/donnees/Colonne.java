package donnees;

import java.io.Serializable;
import java.util.ArrayList;

public class Colonne implements Serializable {
    private String titre;
    private ArrayList<TacheComposite> liste;
    private static final long serialVersionUID = 1L;


    public Colonne(String nom){
        this.liste = new ArrayList<TacheComposite>();
        this.titre = nom;
    }



}
