package vues;

import donnees.Tache;

public class AdaptateurColTache {
    private Tache tache;
    private String colonne;

    public AdaptateurColTache(Tache tache, String colonne){
        this.tache=tache;
        this.colonne=colonne;
    }

}
