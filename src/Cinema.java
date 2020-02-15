import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Cinema implements Serializable{

    private static final long serialVersionUID = 123L;
    private TreeMap<Days, Schedule> schedules;
    private ArrayList<Movie> moviesLibrary;
    private Time open;
    private Time close;

    public Cinema(Time open, Time close) throws FileNotFoundException {
        this.schedules = new TreeMap<>();
        this.moviesLibrary = new ArrayList<>();
        this.open = open;
        this.close = close;
    }

    public TreeMap<Days, Schedule> getSchedules() {
        return schedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public Time getOpen() {
        return open;
    }

    public Time getClose() {
        return close;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    public void setOpenHours() {
        setOpen(enterTime());
        System.out.println("Opening time: " + getOpen());
        setClose(enterTime());
        System.out.println("Closing time: " + getClose());
    }

    public void addMovie() throws InterruptedException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter movie's title: ");
        String title = input.nextLine().strip();
        System.out.println("Enter movie's duration:");
        System.out.print("number of hours: ");
        int hours = input.nextInt();
        System.out.print("number of minutes: ");
        int minutes = input.nextInt();
        moviesLibrary.add(new Movie(title, new Time(hours, minutes)));
    }

    public void addSeance() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the day you want to choose: ");
        String day = input.nextLine().strip();
        List<Days> listDays = Arrays.asList(Days.values());
        Optional<Days> chosenDay = listDays.stream().filter(d -> d.toString().equalsIgnoreCase(day)).findFirst();
        if (!chosenDay.isEmpty()) {
            Seance seance = null;
            System.out.print("Enter title of a film: ");
            String movieName = input.nextLine().strip();
            for (Movie m : moviesLibrary) {
                if (m.getTitle().equalsIgnoreCase(movieName)) {
                    Time seanceTime = enterTime();
                    seance = new Seance(m, seanceTime);
                    if (seanceTime.compareTo(open) == 1 && seance.getEndTime().compareTo(close) == -1) {
                        if (schedules.get(chosenDay.get()).checkIfNoConflict(seance)) {
                            schedules.get(chosenDay.get()).getSeances().add(seance);
                            System.out.println("Updated timeline for " + chosenDay.get());
                            schedules.get(chosenDay.get()).getSeances().forEach(System.out::println);
                        } else {
                            System.out.println("Sorry, there is another film running at that time!");
                        }
                    } else {
                        System.out.println("The time you chose does not fit cinema's opening hours");
                    }
                }
            }
            if (seance == null) {
                System.out.println("There is no movie running with the title \'" + movieName + "\'.");
            }
        } else {
            System.out.println("Check the spelling!");
        }
    }

    public void removeMovie() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Choose a movie you want to remove from the system by entering its name.");
        System.out.println("All current movies: ");
        moviesLibrary.forEach(System.out::println);
        System.out.print("Movie's name: ");
        String movieName = input.nextLine().strip();
        Optional<Movie> movieToDelete = moviesLibrary.stream()
                .filter(n -> n.getTitle().equalsIgnoreCase(movieName)).findFirst();
        if (movieToDelete.isEmpty()) {
            System.out.println("There is no movie with the title \'" + movieName + "\' in the library.");
        } else {
            System.out.println("Movie \'" + movieToDelete.get().getTitle() + "\' has been removed.");
            moviesLibrary.remove(movieToDelete.get());
        }
    }

    public Time enterTime() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the time (use format 00:00): ");
        String time = input.next().strip();
        String[] arrTime = time.split(":");
        int hour = Integer.parseInt(arrTime[0]);
        int min = Integer.parseInt(arrTime[1]);
        return (new Time(hour, min));
    }

    public void removeSeance() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the day you want to choose: ");
        String day = input.nextLine().strip();
        List<Days> listDays = Arrays.asList(Days.values());
        Optional<Days> chosenDay = listDays.stream().filter(d -> d.toString().equalsIgnoreCase(day)).findFirst();
        if (!chosenDay.isEmpty()) {
            System.out.println("Enter the time of the seance you want to remove:");
            Time seanceTime = enterTime();
            Optional<Seance> seanceToRemove = schedules.get(chosenDay.get()).getSeances().stream().filter(d -> d.getStartTime().equals(seanceTime)).findFirst();
            if (!seanceToRemove.isEmpty()) {
                schedules.get(chosenDay.get()).getSeances().remove(seanceToRemove.get());
                List<Seance> list = schedules.get(chosenDay.get()).getSeances().stream().sorted().collect(Collectors.toList());
                if (list.isEmpty()) {
                    System.out.println("There are no seances for today");
                } else {
                    list.stream().forEach(System.out::println);
                }
            }
        }
    }

    public void printSeancesByDay() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the day you want to choose: ");
        String day = input.nextLine().strip();
        List<Days> listDays = Arrays.asList(Days.values());
        Optional<Days> chosenDay = listDays.stream().filter(d -> d.toString().equalsIgnoreCase(day)).findFirst();
        if (!chosenDay.isEmpty()) {
            List<Seance> list = schedules.get(chosenDay.get()).getSeances().stream().sorted().collect(Collectors.toList());
            if (list.isEmpty()) {
                System.out.println("There are no seances for today yet!");
            } else {
                list.stream().forEach(System.out::println);
            }
        }
    }

    public void displaySchedule(){
        for (Days d : Days.values()){
            System.out.println(d);
            if(!schedules.get(d).getSeances().isEmpty()){
                schedules.get(d).getSeances().forEach(System.out::println);
            }else{
                System.out.println("No seances this day.");
            }
            System.out.println();
        }
    }

    public void fillTreeMap() {
        for (Days d : Days.values()) {
            schedules.put(d, new Schedule());
        }
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "schedules=" + schedules +
                ", moviesLibrary=" + moviesLibrary +
                ", open=" + open +
                ", close=" + close +
                '}';
    }
}


