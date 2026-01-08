import donnees.Colonne;
import donnees.Gantt;
import donnees.Modele;
import donnees.Tache;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModeleTest {

    @Test
    /**
     * ajout d'une nouvelle colonne dans un modele vide de colonnes
     */
    public void test_ajoutNouvelleColonne_OK(){
        Modele m = new Modele();
        m.ajouterColonne(new Colonne("test"));

        assertEquals(1, m.getColonnes().size());
        assertEquals(m.getColonnes().getFirst().getTitre(),"test");
    }

    @Test
    /**
     * test pour voi si on peut ajouter au maximum que 5 colonnes
     */
    public void test_ajoutNouvelleColonne_superieur_au_maximum(){

        Modele m = new Modele();

        for(int i = 0; i < m.getNB_MAX_COLONNES(); i++){
            m.ajouterColonne(new Colonne("test"));
        }
        Colonne enTrop = new Colonne("enTrop");
        m.ajouterColonne(enTrop);

        assertEquals(5, m.getColonnes().size());
        assertEquals(false, m.getColonnes().contains(enTrop));
    }


    @Test
    /**
     * modification d'un nom de colonne avec un nom quelconque
     */
    public void test_modification_nom_colonne_OK(){
        Modele m = new Modele();
        Colonne c = new Colonne("test");
        m.ajouterColonne(c);

        m.modifierNomColonne(c, "nouveauNomColonne");

        assertEquals("nouveauNomColonne", m.getColonnes().getFirst().getTitre());
    }


    @Test
    /**
     * modification d'un nom avec une chaine vide
     */
    public void test_modification_nom_colonne_vide(){
        Modele m = new Modele();
        Colonne c = new Colonne("test");
        m.ajouterColonne(c);

        m.modifierNomColonne(c, "");

        assertEquals("test", m.getColonnes().getFirst().getTitre());
    }

    @Test
    /**
     * suppression d'une colonne qui contient une tache
     */
    public void test_supressionColonne_contenant_tache(){
        Modele m = new Modele();
        Colonne c = new Colonne("test");
        m.ajouterColonne(c);
        m.ajouterTache(c,"titre1", "description",2, new Date());

        m.supprimerColonne(c);

        assertEquals(0, m.getColonnes().size());
        assertEquals(1, m.getArchive().getTaches().size());
    }

    @Test
    /**
     * test de deplacement d'une tache d'une colonne a une autre
     */
    public void test_deplacerTache_OK(){
        Modele m = new Modele();
        Colonne c1 = new Colonne("test1");
        Colonne c2 = new Colonne("test2");
        m.ajouterColonne(c1);
        m.ajouterColonne(c2);
        m.ajouterTache(c1,"titre1", "description",2, new Date());
        Tache t = m.getTaches().getFirst();

        m.deplacerTache(t, "test2", "test1");

        assertEquals(1, m.getColonnes().get(1).getListe().size());
        assertEquals(0, m.getColonnes().get(0).getListe().size());
    }


    @Test
    /**
     * On desarchive une tache quand une colonne existe
     */
    public void test_desarchiver_tache_OK(){
        Modele m = new Modele();
        Colonne c = new Colonne("test");
        m.ajouterColonne(c);
        m.ajouterTache(c,"titre1", "description",2, new Date());
        m.gestionArchive(m.getColonnes().getFirst().getListe().getFirst());

        m.desarchiverTache(m.getArchive().getTaches().getFirst());


        assertEquals(1, m.getColonnes().size());
        assertEquals(1, m.getColonnes().get(0).getListe().size());
        assertEquals(0,m.getArchive().getTaches().size());
    }

    @Test
    /**
     * On desarchive une tache quand aucune colonne n'est créer
     */
    public void test_desarchiver_tache_aucune_colonne(){
        Modele m = new Modele();
        Colonne c = new Colonne("test");
        m.ajouterColonne(c);
        m.ajouterTache(c,"titre1", "description",2, new Date());
        m.supprimerColonne(c);

        m.desarchiverTache(m.getArchive().getTaches().getFirst());

        assertEquals(0, m.getColonnes().size());
        assertEquals(1, m.getArchive().getTaches().size());
    }

    @Test
    /**
     * test de la methode pour selectionner toutes les taches des colonnes pour le diagramme de Gantt
     */
    public void test_selectionnerTout_OK(){
        Modele m = new Modele();
        Colonne c1 = new Colonne("test1");
        Colonne c2 = new Colonne("test2");
        m.ajouterColonne(c1);
        m.ajouterColonne(c2);
        m.ajouterTache(c1,"titre1", "description",2, new Date());
        m.ajouterTache(c1,"titre3", "description",2, new Date());
        m.ajouterTache(c2,"titre2", "description",2, new Date());

        m.selectionnerTout();

        Gantt g = m.getGantt();
        List<Tache> selection = g.getSelection();

        assertEquals(3,selection.size());
        assertEquals(m.getColonnes().getFirst().getListe().size()+m.getColonnes().get(1).getListe().size(), selection.size() );





    }
}
