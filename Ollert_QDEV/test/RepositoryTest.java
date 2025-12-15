import donnees.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

class RepositoryTest {

    Modele m;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        Repository.creerFichier();
        //.creation d'un modele de test
        m = new Modele();

        //ajout de l'archive dans le modele
        Archive a = new Archive();
        a.ajouterTache(new Tache("tache1","description1", 2, new Date(11)));
        m.setArchive(a);

        //ajout de colonnes dans le modele
        ArrayList<Colonne> colonnes = new ArrayList<Colonne>();
        Colonne c1 = new Colonne("colonne1");
        Colonne c2 = new Colonne("colonne2");
        c1.ajouteTache(new Tache("tache11","description11", 2, new Date(11)));
        c1.ajouteTache(new Tache("tache2","description2", 2, new Date(11)));
        colonnes.add(c1);
        colonnes.add(c2);
        m.setColonnes(colonnes);
    }

    @AfterEach
    void tearDown() throws IOException, ClassNotFoundException {
        Repository.supprimerFichier();
    }

    @Test
    public void test_saveLoadOK() throws IOException, ClassNotFoundException {
        Repository r = Repository.getInstance();
        r.saveAll(m);

        Archive a = r.loadArchive();
        ArrayList<Colonne> c = r.loadColonnes();

        //test de la recuperation du bon
        assertEquals(1, a.getTaches().size());
        assertEquals("tache1", a.getTaches().getFirst().getTitre());

        //test de la recupereation de la bonne liste de colonnes
        assertEquals(2, c.size());
        assertEquals("colonne1",c.getFirst().getTitre());
        assertEquals("colonne2",c.get(1).getTitre());

        assertEquals("tache11",c.getFirst().getListe().get(0).getTitre());
        assertEquals("tache2",c.getFirst().getListe().get(1).getTitre());
        assertEquals("description11",c.getFirst().getListe().get(0).getDescription());
        assertEquals("description2",c.getFirst().getListe().get(1).getDescription());
        assertEquals(0,c.get(1).getListe().size());

    }
}