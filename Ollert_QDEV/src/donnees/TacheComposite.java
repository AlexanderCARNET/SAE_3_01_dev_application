package donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TacheComposite implements Serializable {

    private static final long serialVersionUID = 1L;

    protected List<TacheComposite> sousTaches = new ArrayList<>();
    protected List<TacheComposite> dependances = new ArrayList<>();


    protected String titre;
    protected String description;
    protected int duree;
    protected Date dateDebut;


    public TacheComposite(String titre, String description, int duree, Date dateDebut) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;
    }

    public void ajouterSousTache(TacheComposite t) {
        sousTaches.add(t);
    }

    public void supprimerSousTache(TacheComposite t) {
        sousTaches.remove(t);
    }

    public void ajouterDependance(TacheComposite t) {
        dependances.add(t);
    }

    public void supprimerDependance(TacheComposite t) {
        dependances.remove(t);
    }

    public List<TacheComposite> getDependances() {
        return this.dependances;
    }

    public List<TacheComposite> getSousTaches() {
        return sousTaches;
    }

    public void setSousTaches(List<TacheComposite> sousTaches) {
        this.sousTaches = sousTaches;
    }

    public boolean modifierTache(String titre, String description, int duree, Date dateDebut) {

        if (titre == null) return false;
        if (duree < 0) return false;
        if (dateDebut == null) return false;

        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;

        return true;
    }






    public void archiver() {}

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
}
