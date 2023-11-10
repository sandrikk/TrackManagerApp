package assignment;

import heap.MinHeap;
import lists.DoublyLinkedList;
import lists.SortedList;
import maps.HashMap;
import model.Station;
import model.Track;
import utils.Astar;
import utils.CsvReader;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    static DoublyLinkedList<Station> stationsDoubly = new DoublyLinkedList<>();
    static ArrayList<Station> stationsArrayList = new ArrayList<>();
    static ArrayList<Track> tracksArrayList = new ArrayList<>();
    static SortedList<Station> sortedStations = new SortedList<>();
    static HashMap<String, Station> stationHashMap = new HashMap<>();
    public static void main(String[] args) {
        // Call the method to read stations from CSV
        readStationsFromCSV();
        readTracksFromCSV();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name (linear search)");
            System.out.println("2. Search for a station by code (hash table)");
            System.out.println("3. Search for a station by name (binary search)");
            System.out.println("5. A* algorithm");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {

                case 1:
                    System.out.print("Insert name to search: ");
                    String nameToSearch = scanner.nextLine();

                    // linear
                    Station foundStationByName = ListUtils.linearSearch(stationsDoubly.getHead(), station -> station.getName().equalsIgnoreCase(nameToSearch));

                    if (foundStationByName != null) {
                        System.out.println("Found station: " + foundStationByName);
                    } else {
                        System.out.println("Station not found.");
                    }

                    break;

                case 2:
                    System.out.print("Insert stations code: ");
                    String inputCode = scanner.nextLine();
                    System.out.println(inputCode);
                    Station foundStationByCode = stationHashMap.get(inputCode); // Retrieve foundStationByCode from the table
                    if (foundStationByCode != null) {
                        // Station found
                        System.out.println(foundStationByCode);
                    } else {
                        // Station with the specified code not found
                        System.out.println("Station not found");
                    }
                    break;

                case 3:
                    System.out.print("Insert name to search: ");
                    String nameToSearchSorted = scanner.nextLine();

                    // Create a custom Comparator for comparing Station objects by name
                    Comparator<Station> nameComparator = new Comparator<Station>() {
                        @Override
                        public int compare(Station station1, Station station2) {
                            return station1.getName().compareTo(station2.getName());
                        }
                    };

                    // Use binary search with the custom nameComparator
                    Station key = new Station(0, "AA", 0, nameToSearchSorted, "", "Fiction", "", 0.0, 0.0);
                    Station foundStationInSortedStations = sortedStations.binarySearch(nameComparator, key);

                    if (foundStationInSortedStations != null) {
                        System.out.println("Found station: " + foundStationInSortedStations);
                    } else {
                        System.out.println("Station not found.");
                    }
                    break;


                case 4:
                    MinHeap<Integer> minHeap = new MinHeap<>(Integer.class, 10);
                    minHeap.push(1);
                    minHeap.push(2);
                    minHeap.push(3);
                    minHeap.push(12);
                    minHeap.push(98);
                    minHeap.push(37);
                    System.out.println(minHeap.graphViz());
                    break;

                case 5:
                    System.out.print("Enter the code of the first station: ");
                    String startStationCode = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the code of the second station: ");
                    String goalStationCode = scanner.nextLine().toLowerCase();

                    // Implement A* algorithm to find the shortest path
                    List<String> shortestPath = astarPath(startStationCode, goalStationCode);
                    System.out.println("Shortest path: " + shortestPath);
                    break;

                case 6:
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


    public static void readStationsFromCSV() {
        //CsvReader csvReader = new CsvReader("./resources/stations.csv", "^(\\\\d+),([A-Z]+),(\\\\d+),\\\"([^\\\"]+)\\\",\\\"([^\\\"]+)?\\\",\\\"([^\\\"]+)?\\\",\\\"([^\\\"]+)?\\\",([A-Z]+),([a-zA-Z0-9\\\\-]+),([A-Z]+),([0-9.]+),([0-9.]+)$");
        //CsvReader csvReader = new CsvReader("./resources/stations.csv", "[0-9]+,[A-Za-z]+,[0-9]+,"[^"]*",'[^']*'s-[A-Za-z]+,[A-Za-z]+-[A-Za-z]+,[A-Za-z]+,[A-Za-z]+,[0-9]*\.[0-9]+,[0-9]*\.[0-9]+");
        CsvReader csvReader = new CsvReader("./resources/stations.csv", "");


        List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            int id = Integer.parseInt(fields[0]);
            String code = fields[1];
            int uic = Integer.parseInt(fields[2]);
            String name =  fields[3];
            String slug = fields[6];
            String country = fields[7];
            String type = fields[8];
            double geoLat = Double.parseDouble(fields[9]);
            double geoLng = Double.parseDouble(fields[10]);

            Station newStation = new Station(id, code, uic, name, slug, country, type, geoLat, geoLng);
            stationsDoubly.add(newStation);
            sortedStations.add(newStation);
            stationHashMap.put(newStation.getCode() ,newStation);
            stationsArrayList.add(newStation);

        }
    }

    public static void readTracksFromCSV() {
        CsvReader csvReader = new CsvReader("./resources/tracks.csv", "^[a-z]+,[a-z]+,\\d+,\\d+,\\d+$");

        List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            String firstStation = fields[1];
            String secondStation = fields[2];
            int distance = Integer.parseInt(fields[3]);
            Track newTrack = new Track(firstStation, secondStation, distance);

            tracksArrayList.add(newTrack);
        }

    }
    public static List<String> astarPath(String startStationCode, String goalStationCode) {
        // Print the content of stationHashMap for debugging
        System.out.println("stationHashMap: " + stationHashMap);

        // Find the corresponding stations for the provided station codes
        Station startStation = stationHashMap.get(startStationCode);
        Station goalStation = stationHashMap.get(goalStationCode);

        // Print the station codes for debugging
        System.out.println("startStationCode: " + startStationCode);
        System.out.println("goalStationCode: " + goalStationCode);

        if (startStation != null && goalStation != null) {
            // Create an utils.Astar instance
            Astar astar = new Astar();
            astar.printMapContents();

            // Find the shortest path using A* algorithm
            List<String> shortestPath = astar.astar(startStation.getCode(), goalStation.getCode());

            return shortestPath;
        } else {
            return null; // Invalid station codes provided
        }
    }


}
