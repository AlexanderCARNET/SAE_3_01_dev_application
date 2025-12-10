package Ollert;

import donnees.Tache;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {

    @Test
    void testCreationTache() {
        Date d = new Date();
        Tache t = new Tache("Titre", "Desc", 5, d);

        assertEquals("Titre", t.getTitre());
        assertEquals("Desc", t.getDescription());
        assertEquals(5, t.getDuree());
        assertEquals(d, t.getDateDebut());
        assertTrue(t.getSousTaches().isEmpty());
    }

    
}
