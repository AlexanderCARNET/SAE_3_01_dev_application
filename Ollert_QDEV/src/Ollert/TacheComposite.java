package Ollert;

import java.io.Serializable;
import java.util.Date;

public abstract class TacheComposite implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titre;
    private String description;
    private int duree;
    private Date dateDebut;


    public TacheComposite(String titre, String description, int duree, Date dateDebut) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;
    }

}
