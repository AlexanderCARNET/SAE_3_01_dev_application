package donnees;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gantt implements Serializable {
    private List<Tache> selection;

    public Gantt(){
        selection = new ArrayList<Tache>();
    }

    public List<Tache> getSelection() {
        return selection;
    }

    public void selectionner(Tache tache){
        if(!this.selection.contains(tache)){
            selection.add(tache);
        }
    }

    public void deselectionner(Tache tache){
        this.selection.remove(tache);
    }
}
