package donnees;
import exception.MaxColonneException;
import vues.Observateur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Modele implements Serializable {

    private ArrayList<Colonne> colonnes;
    private final int NB_MAX_COLONNES = 5;
    private  transient ArrayList<Observateur> observateurs = new ArrayList<>();
    private Archive archive;

    public Modele(){
        this.colonnes = new ArrayList<Colonne>();
        this.archive = new Archive();
    }

    public void notifier(){
        for(Observateur o : observateurs){
            o.actualiser();
        }
    }

    public void ajouterObservateur(Observateur o){
        try {
            this.observateurs.add(o);
        }
        catch (Exception e){
            observateurs = new ArrayList<>();
            ajouterObservateur(o);
        }

    }

    public void supprimerObservateur(Observateur o){
        this.observateurs.remove(o);
    }

    public int getNB_MAX_COLONNES() {
        return NB_MAX_COLONNES;
    }

    public boolean ajouterDependance(TacheComposite cible, TacheComposite dependance) {

        if (cible == null || dependance == null) return false;
        if (cible == dependance) return false;
        if (cible.getDependances().contains(dependance)) return false;

        cible.ajouterDependance(dependance);
        notifier();
        return true;
    }

    public void deplacerTache(Tache tache, Colonne cible) {


        if (tache == null || cible == null) return;

        Colonne source = null;
        for (Colonne c : colonnes) {
            if (c.getListe().contains(tache)) {
                source = c;
                break;
            }
        }
        if (source == null || source == cible) return;
        source.getListe().remove(tache);
        cible.getListe().add(tache);
        notifier();
    }





    private Colonne trouverColonneDe(TacheComposite tache) {
        for (Colonne c : colonnes) {
            if (c.contient(tache)) {
                return c;
            }
        }
        return null;
    }




    public Tache ajouterTache(Colonne colonne, String titre, String description, int duree, Date dateDebut) {

        if (colonne == null) return null;
        if (titre == null || titre.isBlank()) return null;
        if (duree < 0) return null;
        if (dateDebut == null) return null;

        Tache tache = new Tache(titre, description, duree, dateDebut);
        colonne.ajouteTache(tache);

        notifier();
        return tache;
    }



    public boolean ajouterSousTache(TacheComposite parent, TacheComposite sousTacheExistante) {

        if (parent == null || sousTacheExistante == null) return false;

        parent.ajouterSousTache(sousTacheExistante);

        notifier();
        return true;
    }


    public void ajouterColonne(String titre) throws MaxColonneException{
        if(titre != null || !titre.isBlank()){
            if(this.colonnes.size() < this.NB_MAX_COLONNES) {
                Colonne c = new Colonne(titre);
                ajouterColonne(c);
            }
            else{
                new MaxColonneException();
                throw new MaxColonneException();
            }
        }

        notifier();

    }
    public void modifierTache(Tache tache, String titre, String description, int duree, Date dateDebut) {

        if (tache == null) return;
        if (titre == null || titre.isBlank()) return;
        if (duree < 0) return;
        if (dateDebut == null) return;

        tache.setTitre(titre);
        tache.setDescription(description);
        tache.setDuree(duree);
        tache.setDateDebut(dateDebut);

        this.notifier();
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

    public void setColonnes(ArrayList<Colonne> colonnes) {
        this.colonnes = colonnes;
    }

    public void modifierNomColonne(Colonne c, String nouveauNom){
        if(!(nouveauNom == null || nouveauNom.isBlank())){
            c.setTitre(nouveauNom);
        };
    }
}