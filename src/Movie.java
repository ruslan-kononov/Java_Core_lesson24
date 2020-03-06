import java.io.Serializable;
import java.util.Objects;

public class Movie implements Serializable,Comparable<Movie> {

    private static final long serialVersionUID = 123L;
    private String title;
    private Time duration;

    public Movie(String title, Time duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public Time getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(duration, movie.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration);
    }

    @Override
    public String toString() {
        return '\''+title + '\'' +
                ", duration: " + duration.getHour()+" h, " + duration.getMin()+" min;";
    }

    @Override
    public int compareTo(Movie o) {
        return 0;
    }
}
