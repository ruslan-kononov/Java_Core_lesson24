import java.io.Serializable;
import java.util.Objects;

public class Seance implements Comparable<Seance>, Serializable {

    private static final long serialVersionUID = 123L;
    private Movie movie;
    private Time startTime;
    private Time endTime;

    public Seance(Movie movie, Time startTime) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = new Time(startTime.getHour() + movie.getDuration().getHour(),
                startTime.getMin() + movie.getDuration().getMin()
        );
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return movie.equals(seance.movie) &&
                startTime.equals(seance.startTime) &&
                endTime.equals(seance.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, startTime, endTime);
    }

    @Override
    public String toString() {
        return "\'" + movie.getTitle() + "\'" +
                ", time: " + startTime +
                " - " + endTime + ";";
    }

    @Override
    public int compareTo(Seance o) {
        if (this.startTime.compareTo(o.endTime) == 1) {
            return 1;
        } else if (this.endTime.compareTo(o.startTime) == -1) {
            return -1;
        } else {
            return 0;
        }
    }
}
