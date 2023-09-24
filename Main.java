/*
import model.Station;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
           // Station.printStations();
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name (linear)");
            System.out.println("2. Search for a station by name (binary)");
            System.out.println("3. Search for a station by code");
            System.out.println("4. Sort stations");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.print("Insert name to search: ");
                    String nameToSearch = scanner2.nextLine();

                    // linear
                    Station foundStation = Station.getStations().search(station -> station.getName().equalsIgnoreCase(nameToSearch));

                    if (foundStation != null) {
                        System.out.println("Found station: " + foundStation);
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;

                case 2:
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.print("Insert stations code: ");
                    String code = scanner3.nextLine();
                    Station station = Station.getStationByCode(code); // Replace "ABC123" with the actual station code you want to retrieve
                    if (station != null) {
                        // Station found
                        System.out.println(station);
                    } else {
                        // Station with the specified code not found
                        System.out.println("Station not found");
                    }
                    break;


                case 3:

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

 */

import model.Station;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Station.printStations();
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name (linear)");
            System.out.println("2. Search for a station by code");
            System.out.println("3.");
            System.out.println("4.");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Insert name to search: ");
                    String nameToSearch = scanner.nextLine();

                    // linear
                    Station foundStation = Station.getStations().search(station -> station.getName().equalsIgnoreCase(nameToSearch));

                    if (foundStation != null) {
                        System.out.println("Found station: " + foundStation);
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;

                case 2:
                    System.out.print("Insert stations code: ");
                    String code = scanner.nextLine();
                    Station station = Station.getStationByCode(code); // Replace "ABC123" with the actual station code you want to retrieve
                    if (station != null) {
                        // Station found
                        System.out.println(station);
                    } else {
                        // Station with the specified code not found
                        System.out.println("Station not found");
                    }
                    break;

                case 3:
                    System.out.print("Insert name to search: ");
                    String nameToSearch2 = scanner.nextLine();

                    // linear
                    Station foundStation2 = Station.getSortedStations().search(station2 -> station2.getName().equalsIgnoreCase(nameToSearch2));

                    if (foundStation2 != null) {
                        System.out.println("Found station: " + foundStation2);
                    } else {
                        System.out.println("Station not found.");
                    }
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

