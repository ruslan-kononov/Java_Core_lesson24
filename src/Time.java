import java.io.Serializable;
import java.util.Objects;

public class Time implements Serializable,Comparable<Time> {
    private int hour;
    private int min;

    public Time(int hour,int min) {
        if (min>=60){
            hour+=min/60;
            min=min%60;
            if(hour>23){
                hour%=24;
            }
        }else if(hour>23){
            hour%=24;
        }
        if(hour<0 ||min<0){
            throw new IncorrectTimeException("Sorry, you entered invalid time!");
        }
        this.hour = hour;
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour &&
                min == time.min;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, min);
    }

    @Override
    public String toString() {
        return  hour + ":" + (min<10?"0"+min:min);
    }

    @Override
    public int compareTo(Time o) {
        if(this.getHour()<o.getHour()){
            return -1;
        }else if(this.getHour()>o.getHour()){
            return 1;
        }else{
            if(this.getMin()<o.getMin()){
                return -1;
            }else if(this.getMin()>o.getMin()){
                return 1;
            }else{
                return 0;
            }
        }
    }
}
