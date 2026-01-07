package donnees;
import exception.MaxColonneException;
import vues.Observateur;
import vues.PopupAddTache;
import vues.PopupEditTache;

import java.io.Serializable;
import java.util.*;


public class Modele implements Serializable {

    private ArrayList<Colonne> colonnes;
    private final int NB_MAX_COLONNES = 5;
    private  transient ArrayList<Observateur> observateurs = new ArrayList<>();
    private Archive archive;
    private Gantt gantt;

    public Modele(){
        this.colonnes = new ArrayList<Colonne>();
        this.archive = new Archive();
        this.gantt = new Gantt();
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

        if (dependance.dependsOn(cible)) {
            return false;
        }

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
        for(Tache t : c.getListe()){
            this.archive.ajouterTache(t);
        }
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

    public void deplacerTache(Tache tache, String newCol, String oldCol){
        for(Colonne colonne: this.getColonnes()){
            if(colonne.getTitre().equals(oldCol))
                colonne.supprimeTache(tache);
            if(colonne.getTitre().equals(newCol))
                colonne.ajouteTache(tache);
        }

        System.out.println("\n");
        for(Colonne colonne: this.getColonnes()){
            if(colonne.getTitre().equals(oldCol) || colonne.getTitre().equals(newCol)){
                System.out.println(colonne.getTitre());
                for(TacheComposite task : colonne.getListe()){
                    System.out.println(task.getTitre());
                }
            }
        }
    }

    public void gestionAjout(){
        if(this == null || this.getColonnes().isEmpty()){
            //throw new ;

            System.out.println("You dont have a model or the colonnes are empty.");
            return;
        }

        Colonne col = this.getColonnes().get(0);

        PopupAddTache.display(this, col);
    }

    public void gestionArchive(Tache tache){
        for(Colonne col : this.getColonnes()){
            if(col.getListe().contains(tache)){
                col.supprimeTache(tache);
                break;
            }
        }

        this.gantt.deselectionner(tache);

        this.archive.ajouterTache(tache);
        this.notifier();
    }


    public void gestionModification(Tache tache){
        if(this == null || this.getColonnes().isEmpty()){
            //throw new ;

            System.out.println("You dont have a model or the colonnes are empty.");
            return;
        }

        PopupEditTache.display(this, tache);
    }

    public void desarchiverTache(Tache tache){
        if(this.archive.contains(tache)){
            this.archive.supprimeTache(tache);
            this.notifier();
        }
        this.colonnes.getFirst().ajouteTache(tache);
    }

    public void supprimerArchiverTache(Tache tache){
        if(this.archive.contains(tache)){
            this.archive.supprimeTache(tache);
            this.notifier();
        }
    }

    public boolean remplacerDependances(TacheComposite cible, List<TacheComposite> nouvelles) {
        if (cible == null) return false;
        if (nouvelles == null) nouvelles = List.of();

        Set<TacheComposite> uniques = new LinkedHashSet<>(nouvelles);

        for (TacheComposite dep : uniques) {
            if (dep == null) return false;
            if (dep == cible) return false;
            if (dep.dependsOn(cible)) return false;
        }

        cible.clearDependances();
        for (TacheComposite dep : uniques) {
            cible.ajouterDependance(dep);  
        }

        notifier();
        return true;
    }


    public List<Tache> getTaches(){
        List<Tache> taches = new ArrayList<>();
        for(Colonne col : this.getColonnes()){
            for(Tache tache : col.getListe()){
                taches.add(tache);
            }
        }
        return taches;
    }

    public Gantt getGantt(){
        return gantt;
    }
}