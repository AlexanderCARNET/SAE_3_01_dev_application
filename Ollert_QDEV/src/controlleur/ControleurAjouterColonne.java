package controlleur;
import donnees.Colonne;
import donnees.Modele;

import java.util.ArrayList;

public class ControleurAjouterColonne {

    private final Modele modele;

    public ControleurAjouterColonne(Modele m){
        this.modele = m;
    }

    public boolean ajouterColonne(String titre){
        if(titre == null || titre.isBlank()){
            return false;
        }
        Colonne c = new Colonne(titre);
        modele.ajouterColonne(c);

        return true;
    }


}
