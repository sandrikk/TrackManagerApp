package demo;

import heap.MinHeap;
import lists.DoublyLinkedList;
import lists.SortedList;
import maps.HashMap;
import model.Station;
import utils.CsvReader;
import utils.ListUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static DoublyLinkedList<Station> stations = new DoublyLinkedList<>();
    static SortedList<Station> sortedStations = new SortedList<>();
    static HashMap<String, Station> stationHashMap = new HashMap<>();
    public static void main(String[] args) {
        // Call the method to read stations from CSV
        readStationsFromCSV();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name (linear search)");
            System.out.println("2. Search for a station by code (hash table)");
            System.out.println("3. Search for a station by name (binary search)");
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
                    Station foundStationByName = ListUtils.linearSearch(stations.getHead(), station -> station.getName().equalsIgnoreCase(nameToSearch));

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

    // Move the CSV reading logic here
    public static void readStationsFromCSV() {
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
            stations.add(newStation);
            sortedStations.add(newStation);
            stationHashMap.put(newStation.getCode() ,newStation);
        }

        sortedStations.print();
    }
}
