package vues;

import donnees.Colonne;
import donnees.Tache;



import java.util.List;

public class VueTaches implements Observateur{

    private List<Colonne> colonnes;

    private StrategieModeAffichage modeAffichage;

    public VueTaches(List<Colonne> colonnes) {
        super();
        this.colonnes = colonnes;
    }

    public void setModeAffichage(StrategieModeAffichage mode){
        this.modeAffichage=mode;
    }

    @Override
    public void actualiser(){
        this.modeAffichage.genererAffichage(this.colonnes);
    }
}
