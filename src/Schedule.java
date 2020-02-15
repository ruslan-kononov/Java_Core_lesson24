import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 123L;
    private Set<Seance> seances;

    public Schedule() {
        this.seances = new TreeSet<>();
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public boolean checkIfNoConflict(Seance otherSeance) {
        if (!seances.isEmpty()) {
            Iterator<Seance> it = seances.iterator();
            while (it.hasNext()) {
                Seance current = it.next();
                if (current.compareTo(otherSeance) == 0) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return seances + ";";
    }
}
