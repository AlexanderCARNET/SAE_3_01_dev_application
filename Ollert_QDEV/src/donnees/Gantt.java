package donnees;

import java.util.ArrayList;
import java.util.List;

public class Gantt {
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
