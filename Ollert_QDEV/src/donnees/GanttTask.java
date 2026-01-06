package donnees;

import java.util.Date;

public class GanttTask {

    private final Tache tache;
    private final Date debut;
    private final Date fin;

    public GanttTask(Tache tache, Date debut, Date fin) {
        this.tache = tache;
        this.debut = debut;
        this.fin = fin;
    }

    public Tache getTache() {
        return tache;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }
}
