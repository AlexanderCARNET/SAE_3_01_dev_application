package vues;

public class VueTaches implements Observateur{

    private StrategieModeAffichage modeAffichage;

    public void setModeAffichage(StrategieModeAffichage mode){
        this.modeAffichage=mode;
    }

    public void actualiser(){

    }
}
