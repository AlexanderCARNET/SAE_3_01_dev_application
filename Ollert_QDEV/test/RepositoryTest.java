import donnees.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

class RepositoryTest {
    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        Repository.creerFichier();
    }

    @AfterEach
    void tearDown() throws IOException, ClassNotFoundException {
        Repository.supprimerFichier();
    }


}