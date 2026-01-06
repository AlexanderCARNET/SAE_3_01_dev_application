package donnees;

import java.util.*;

public class GanttCalculator {

    private final Map<TacheComposite, Date> debutCalcule = new HashMap<>();


    public Date calculerDateDebut(TacheComposite tache) {

        if (debutCalcule.containsKey(tache)) {
            return debutCalcule.get(tache);
        }

        Date debut = tache.getDateDebut();

        for (TacheComposite dep : tache.getDependances()) {
            Date debutDep = calculerDateDebut(dep);
            Date finDep = new Date(debutDep.getTime() + dep.getDuree() * 24L * 60 * 60 * 1000);

            if (finDep.after(debut)) {
                debut = finDep;
            }
        }

        debutCalcule.put(tache, debut);
        return debut;
    }

}


