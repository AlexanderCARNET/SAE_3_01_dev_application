package vues;

import donnees.Tache;



import java.util.List;

public class VueTaches implements Observateur{

    private List<Tache> taches;

    private StrategieModeAffichage modeAffichage;

    public VueTaches(List<Tache> taches) {
        super();
        this.taches = taches;
    }

    public void setModeAffichage(StrategieModeAffichage mode){
        this.modeAffichage=mode;
    }

    @Override
    public void actualiser(List<Tache> taches){
        this.modeAffichage.genererAffichage(taches);
    }
}
