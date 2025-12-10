
import donnees.Colonne;
import donnees.Tache;
import donnees.TacheComposite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ColonneTest {

    private Colonne c;
    private Tache t;
    private Tache t2;
    private Tache t3;
    private ArrayList<TacheComposite> listeTest1;

    @BeforeEach
    public void setUp(){
        c = new Colonne("test");

        t = new Tache("titre1", "description1", 30, new Date());
        t2 = new Tache("titre2", "description2", 45, new Date());
        t3 = new Tache("titre3", "description3", 50, new Date());

        listeTest1 = new ArrayList<TacheComposite>();
    }

    @Test
    public void test_colonne_ajoutTache_UnSeulOK(){
        c.ajouteTache(t);
        listeTest1.add(t);

        assertEquals("pas la même liste", listeTest1, c.getListe());
    }

    @Test
    public void test_colonne_ajoutTache_PlusieursOK(){
        c.ajouteTache(t);
        c.ajouteTache(t2);
        listeTest1.add(t);
        listeTest1.add(t2);

        assertEquals("pas la même liste", listeTest1, c.getListe());
    }

    @Test
    public void test_colonne_ajoutTache_PasLeMemeOrdreDAjout(){
        c.ajouteTache(t);
        c.ajouteTache(t2);
        listeTest1.add(t2);
        listeTest1.add(t);

        assertNotEquals("c'est pas censé être la même liste", listeTest1, c.getListe());
    }

    @Test
    public void test_colonne_supprimeTache_OK(){
        c.ajouteTache(t);
        listeTest1.add(t);
        c.supprimeTache(t);

        assertTrue("la liste doit être vide", c.getListe().isEmpty());
    }

    @Test
    public void test_colonne_supprimeTacheInexistante(){
        c.ajouteTache(t);
        listeTest1.add(t);
        c.supprimeTache(t2);

        assertFalse("la liste doit pas être vide", c.getListe().isEmpty());
    }

    @Test
    public void test_colonne_supprimeTacheAuMilieu(){
        c.ajouteTache(t);
        c.ajouteTache(t2);
        c.ajouteTache(t3);
        listeTest1.add(t);
        listeTest1.add(t3);
        c.supprimeTache(t2);

        assertEquals("la liste n'est pas la même", c.getListe(), listeTest1);
    }







}
