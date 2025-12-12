
import donnees.Tache;
import donnees.TacheComposite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {

    private Tache tache;
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date();
        tache = new Tache("Titre", "Description", 5, date);
    }

    @Test
    void testCreationTache() {
        assertEquals("Titre", tache.getTitre());
        assertEquals("Description", tache.getDescription());
        assertEquals(5, tache.getDuree());
        assertEquals(date, tache.getDateDebut());
        assertTrue(tache.getSousTaches().isEmpty());
    }

    @Test
    void testModifierTache() {
        Date d2 = new Date();

        boolean ok = tache.modifierTache("Nouveau", "Modif", 10, d2);

        assertTrue(ok);
        assertEquals("Nouveau", tache.getTitre());
        assertEquals("Modif", tache.getDescription());
        assertEquals(10, tache.getDuree());
        assertEquals(d2, tache.getDateDebut());
    }

    @Test
    void testModifierTacheInvalide() {
        assertFalse(tache.modifierTache(null, "x", 1, new Date()));

        assertFalse(tache.modifierTache("ok", "x", -1, new Date()));

        assertFalse(tache.modifierTache("ok", "x", 1, null));
    }

    @Test
    void testAjouterSousTache() {
        Tache enfant = new Tache("Fils", "Desc", 2, new Date());

        tache.ajouterSousTache(enfant);

        assertEquals(1, tache.getSousTaches().size());
        assertSame(enfant, tache.getSousTaches().get(0));
    }

    @Test
    void testSupprimerSousTache() {
        Tache enfant = new Tache("Fils", "Desc", 2, new Date());
        tache.ajouterSousTache(enfant);

        tache.supprimerSousTache(enfant);

        assertTrue(tache.getSousTaches().isEmpty());
    }



    @Test
    void testSetSousTaches() {
        Tache c1 = new Tache("C1", "D1", 1, new Date());
        Tache c2 = new Tache("C2", "D2", 1, new Date());

        List<TacheComposite> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        tache.setSousTaches(list);

        assertEquals(2, tache.getSousTaches().size());
        assertSame(c1, tache.getSousTaches().get(0));
        assertSame(c2, tache.getSousTaches().get(1));
    }
}
