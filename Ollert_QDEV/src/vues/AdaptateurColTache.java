package vues;

import donnees.Tache;

import java.util.Date;

public class AdaptateurColTache {
    private Tache tache;
    private String colonne;

    public AdaptateurColTache(Tache tache, String colonne){
        this.tache=tache;
        this.colonne=colonne;
    }

    public String getColonne(){
        return this.colonne;
    }

    public String getTitre(){
        return this.tache.getTitre();
    }

    public int getDuree(){
        return this.tache.getDuree();
    }

    public Date getDate(){
        return this.getDate();
    }
}
