import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        Cinema cinema = new Cinema(new Time(8,0),new Time(23,59));
        cinema.fillTreeMap();
        System.out.println("Cinema \"Space\" welcomes you!");
        System.out.println("Our working hours: "+cinema.getOpen()+" - "+cinema.getClose());
        boolean flag = false;
        while (!flag) {
            try {
                Thread.sleep(1000);
                System.out.println();
                System.out.println("Please, select one of the options by entering the corresponding number:");
                Thread.sleep(1000);
                System.out.println("=================================");
                System.out.println("Add movie to the system - 1");
                System.out.println("Add seance to the system - 2");
                System.out.println("Remove movie from a system - 3");
                System.out.println("Remove seance from a system - 4");
                System.out.println("Display seances for a certain day - 5");
                System.out.println("Show movies' library - 6");
                System.out.println("Change working hours of the cinema - 7");
                System.out.println("Save changes to the file - 8");
                System.out.println("Get object from the file - 9");
                System.out.println("Display schedule for the week - 10");
                System.out.println("Exit the system - 11");
                System.out.println("=================================");
                System.out.print("Enter your option: ");
                int option = input.nextInt();
                switch (option) {
                    case 1:
                        cinema.addMovie();
                        break;
                    case 2:
                        cinema.addSeance();
                        break;
                    case 3:
                        cinema.removeMovie();
                        break;
                    case 4:
                        cinema.removeSeance();
                        break;
                    case 5:
                        cinema.printSeancesByDay();
                        break;
                    case 6:
                        System.out.println();
                        cinema.getMoviesLibrary().forEach(System.out::println);
                        break;
                    case 7:
                        cinema.setOpenHours();
                        break;
                    case 8:
                        SerializingMethods.serialize(cinema,new File("NewCinema.txt"));
                        break;
                    case 9:
                        cinema = (Cinema) SerializingMethods.deserialize(new File("NewCinema.txt"));
                        System.out.println();
                        System.out.println("Cinema \"Space\" welcomes you!");
                        System.out.println("Our working hours: "+cinema.getOpen()+" - "+cinema.getClose());
                        break;
                    case 10:
                        cinema.displaySchedule();
                        break;
                    case 11:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Sorry, entered invalid number. Try one more time!");
                }
            } catch (InputMismatchException | IOException e) {
                System.out.println("Sorry, you entered invalid data! Try one more time!");
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
