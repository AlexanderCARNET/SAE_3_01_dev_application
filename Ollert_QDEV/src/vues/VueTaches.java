package vues;

import donnees.Modele;

public class VueTaches implements Observateur{

    private Modele model;

    private StrategieModeAffichage modeAffichage;

    public VueTaches(Modele mod) {
        this.model = mod;
    }

    public void setModeAffichage(StrategieModeAffichage mode){
        this.modeAffichage=mode;
    }

    @Override
    public void actualiser(){
        this.modeAffichage.genererAffichage(this.model);
    }
}
