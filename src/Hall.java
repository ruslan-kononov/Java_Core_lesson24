import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.TreeMap;

public class Hall implements Comparable<Hall>, Serializable {

    private static final long serialVersionUID = 123L;
    private String name;
    private TreeMap<Days, Schedule> schedules;

    public Hall(String name) {
        this.name = name;
        this.schedules = new TreeMap<>();
        for (Days d : Days.values()) {
            schedules.put(d, new Schedule());
        }
    }

    public String getName() {
        return name;
    }

    public TreeMap<Days, Schedule> getSchedules() {
        return schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return Objects.equals(name, hall.name) &&
                Objects.equals(schedules, hall.schedules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, schedules);
    }

    @Override
    public String toString() {
        return "Hall " + "'" + name + '\'' +
                ", schedules: " + schedules;
    }

    @Override
    public int compareTo(Hall o) {
        String a=this.name;
        String b=o.getName();
        String[] array1 = {a.toLowerCase(),b.toLowerCase()};
        String[] array2 = {a.toLowerCase(),b.toLowerCase()};
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2)){
            return -1;
        }
        else{
            return 1;
        }
    }
}
