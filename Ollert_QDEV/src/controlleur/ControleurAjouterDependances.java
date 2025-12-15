package controlleur;

import donnees.TacheComposite;

public class ControleurAjouterDependances {

    private final TacheComposite tacheCible;

    public ControleurAjouterDependances(TacheComposite tacheCible) {
        this.tacheCible = tacheCible;
    }

    public boolean ajouterDependance(TacheComposite dependance) {

        if (dependance == null) return false;
        if (dependance == tacheCible) return false;
        if (tacheCible.getDependances().contains(dependance)) return false;

        tacheCible.ajouterDependances(dependance);
        return true;
    }
}
