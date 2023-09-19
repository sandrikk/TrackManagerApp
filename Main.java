import model.Station;
import model.Track;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
           // Station.printStations();
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name");
            System.out.println("2. Add a new station");
            System.out.println("3. Remove a station");
            System.out.println("4. List all stations");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.print("Insert name to search: ");
                    String nameToSearch = scanner2.nextLine();
                    Station foundStation = Station.getStations().search(station -> station.getName().equalsIgnoreCase(nameToSearch));


                    if (foundStation != null) {
                        System.out.println("Found station: " + foundStation);
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;
                case 2:
                    // Implement the add functionality here
                    break;
                case 3:
                    // Implement the remove functionality here
                    break;
                case 4:
                    // Implement the list functionality here
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
