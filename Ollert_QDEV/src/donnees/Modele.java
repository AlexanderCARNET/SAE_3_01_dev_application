package donnees;
import vues.Observateur;

import java.util.ArrayList;

public class Modele {

    private ArrayList<Colonne> colonnes;
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

    public int getNB_MAX_COLONNES() {
        return NB_MAX_COLONNES;
    }

    public void setNB_MAX_COLONNES(int NB_MAX_COLONNES) {
        this.NB_MAX_COLONNES = NB_MAX_COLONNES;
    }

    public ArrayList<Observateur> getObservateurs() {
        return observateurs;
    }

    public void setObservateurs(ArrayList<Observateur> observateurs) {
        this.observateurs = observateurs;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public ArrayList<Colonne> getColonnes() {
        return colonnes;
    }
    
    public void ajouterColonne(Colonne c){
        this.colonnes.add(c);
    }

    public void supprimerColonne(Colonne c){
        this.colonnes.remove(c);
    }
}