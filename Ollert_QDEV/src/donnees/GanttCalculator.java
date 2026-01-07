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

    private TacheComposite trouverRacine(TacheComposite tache) {
        if (tache.getDependances().isEmpty()) {
            return tache;
        }
        return trouverRacine(tache.getDependances().get(0));
    }


    public List<GanttTask> generer(List<Tache> taches) {
        debutCalcule.clear();

        Map<TacheComposite, List<GanttTask>> groupes = new LinkedHashMap<>();

        for (Tache t : taches) {
            Date debut = calculerDateDebut(t);
            Date fin = new Date(debut.getTime() + t.getDuree() * 24L * 60 * 60 * 1000);

            TacheComposite racine = trouverRacine(t);

            groupes.computeIfAbsent(racine, k -> new ArrayList<>()).add(new GanttTask(t, debut, fin));
        }

        List<GanttTask> result = new ArrayList<>();
        for (List<GanttTask> groupe : groupes.values()) {
            result.addAll(groupe);
        }

        return result;
    }


}










