package Ollert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TacheComposite implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titre;
    private String description;
    private int duree;
    private Date dateDebut;

    protected List<TacheComposite> sousTaches = new ArrayList<>();


    public TacheComposite(String titre, String description, int duree, Date dateDebut) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;
    }

    public void archiver(){

    }

    public void ajouterSousTache(TacheComposite t) {
        sousTaches.add(t);
    }


    public void save() {
    }

    public void update() {
    }

    public void delete() {
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

}
