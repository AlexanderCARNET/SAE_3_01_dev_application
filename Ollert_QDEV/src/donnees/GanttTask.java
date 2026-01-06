package donnees;

import java.util.Date;

public class GanttTask {

    private final TacheComposite tache;
    private final Date debut;
    private final Date fin;

    public GanttTask(TacheComposite tache, Date debut, Date fin) {
        this.tache = tache;
        this.debut = debut;
        this.fin = fin;
    }

    public TacheComposite getTache() {
        return tache;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }
}
