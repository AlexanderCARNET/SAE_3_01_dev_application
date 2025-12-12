package donnees;
import vues.Observateur;

import java.util.ArrayList;

public class Modele {

    private int NB_MAX_COLONNES;
    private ArrayList<Observateur> observateurs;
    private Archive archive;

    public void notifier(){
        for(Observateur o : observateurs){
            o.actualiser();
        }
    }

    public void ajouterObservateur(Observateur o){
        this.observateurs.add(o);
    }

    public void supprimerObservateur(Observateur o){
        this.observateurs.remove(o);
    }


}