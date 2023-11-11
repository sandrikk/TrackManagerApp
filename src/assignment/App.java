package assignment;

import graphs.WeightedMatrixGraph;
import heap.MinHeap;
import lists.DoublyLinkedList;
import lists.SortedList;
import maps.MyHashMap;
import model.Station;
import model.Track;
import sort.Sort;
import utils.CsvReader;
import utils.GraphUtils;
import utils.ListUtils;

import java.util.*;

public class App {
    static DoublyLinkedList<Station> stationsDoubly = new DoublyLinkedList<>();
    static ArrayList<Station> stationsArrayList = new ArrayList<>();
    static ArrayList<Track> tracksArrayList = new ArrayList<>();
    private static WeightedMatrixGraph<String> connectionGraph;
    static SortedList<Station> sortedStations = new SortedList<>();
    static HashMap<String, Station> stationMap = new HashMap<>();
    static MyHashMap<String, Station> stationMyMap = new MyHashMap<>();


    public static void main(String[] args) {
        readStationsFromCSV();

        // Call the method to read stations from CSV
        String[] stationCodes = stationsArrayList.stream()
                .map(Station::getCode)
                .toArray(String[]::new);
        connectionGraph = new WeightedMatrixGraph<>(true, stationCodes);

        //System.out.println("Stations added to the graph: " + Arrays.toString(stationCodes));


        readTracksFromCSV();
        Scanner scanner = new Scanner(System.in);

        System.out.println(connectionGraph.toGraphViz());



        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name (linear search)");
            System.out.println("2. Search for a station by code (hash table)");
            System.out.println("3. Search for a station by name (binary search)");
            System.out.println("4. Sort an ArrayList of connections by length (mergesort");
            System.out.println("5. Sort an ArrayList of connections by length (selectionsort");
            System.out.println("6. A* algorithm");
            System.out.println("7. Dijkstra algorithm");
            System.out.println("8. Exit");

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
                    Station foundStationByCode = stationMyMap.get(inputCode); // Retrieve foundStationByCode from the table
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
                    System.out.println("Sorting tracks by length using merge sort...");

                    // Define a comparator for Track objects based on their distance
                    Comparator<Track> trackComparator = Comparator.comparingInt(Track::getDistance);

                    // Create an instance of your Sort class
                    Sort sort = new Sort();

                    // Perform merge sort on tracksArrayList using the defined comparator
                    sort.mergeSort(tracksArrayList, trackComparator);

                    // Optionally, print the sorted list to verify
                    for (Track track : tracksArrayList) {
                        System.out.println(track);
                    }

                    break;
                case 5:
                    System.out.println("Sorting tracks by length using selection sort...");

                    // Define a comparator for Track objects based on their distance
                    Comparator<Track> trackDistanceComparator = Comparator.comparingInt(Track::getDistance);

                    // Create an instance of the Sort class
                    Sort sortInstance = new Sort();

                    // Perform selection sort on tracksArrayList using the defined comparator
                    sortInstance.selectionSort(tracksArrayList, trackDistanceComparator);

                    // Optionally, print the sorted list to verify
                    for (Track track : tracksArrayList) {
                        System.out.println(track);
                    }

                    break;
                case 6:
                    System.out.print("Enter the code of the first station: ");
                    String startStationCode = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the code of the second station: ");
                    String goalStationCode = scanner.nextLine().toLowerCase();

                    if (stationMap.containsKey(startStationCode) && stationMap.containsKey(goalStationCode)) {
                        List<String> shortestPath = GraphUtils.findShortestPathAStar(connectionGraph, startStationCode, goalStationCode, stationMap);

                        if (shortestPath.isEmpty()) {
                            System.out.println("No path found between the given stations.");
                        } else {
                            System.out.println("Shortest path (A*): " + shortestPath);
                        }
                    } else {
                        System.out.println("One or both of the station codes are invalid.");
                    }
                    break;

                case 7:
                    System.out.print("Enter the code of the first station: ");
                    String startStationCodeDijkstra = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the code of the second station: ");
                    String goalStationCodeDijkstra = scanner.nextLine().toLowerCase();

                    if (stationMap.containsKey(startStationCodeDijkstra) && stationMap.containsKey(goalStationCodeDijkstra)) {
                        List<String> shortestPathDijkstra = GraphUtils.findShortestPathDijkstra(connectionGraph, startStationCodeDijkstra, goalStationCodeDijkstra);

                        if (shortestPathDijkstra.isEmpty()) {
                            System.out.println("No path found between the given stations.");
                        } else {
                            System.out.println("Shortest path (Dijkstra): " + shortestPathDijkstra);
                        }
                    } else {
                        System.out.println("One or both of the station codes are invalid.");
                    }
                    break;
                case 8:
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

            Station newStation = new Station(id, code.toLowerCase(), uic, name, slug, country, type, geoLat, geoLng);
            stationsDoubly.add(newStation);
            sortedStations.add(newStation);
            stationMyMap.put(newStation.getCode() ,newStation);
            stationMap.put(newStation.getCode(), newStation);
            stationsArrayList.add(newStation);


        }
    }

    public static void readTracksFromCSV() {
        CsvReader csvReader = new CsvReader("./resources/tracks.csv", "^[a-z]+,[a-z]+,\\d+,\\d+,\\d+$");

        List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            String firstStationCode = fields[0];
            String secondStationCode = fields[1];
            int distance = Integer.parseInt(fields[2]);
            Track newTrack = new Track(firstStationCode, secondStationCode, distance);

            //tracksArrayList.add(newTrack);
            //connectionGraph.connect(firstStation, secondStation, distance);

            // Debug: Check if station codes exist
            if (!stationMap.containsKey(firstStationCode.toLowerCase())) {
                System.out.println("Station first not found in map: " + firstStationCode);
            }
            if (!stationMap.containsKey(secondStationCode.toLowerCase())) {
                System.out.println("Station sec not found in map: " + secondStationCode);
            }

            if (stationMap.containsKey(firstStationCode) && stationMap.containsKey(secondStationCode)) {
                connectionGraph.connect(firstStationCode, secondStationCode, distance);
                tracksArrayList.add(newTrack);

                //System.out.println("Connection added: " + firstStationCode + " - " + secondStationCode + " with distance " + distance);

            }

     /*
                    MinHeap<Integer> minHeap = new MinHeap<>(Integer.class, 10);
                    minHeap.push(1);
                    minHeap.push(2);
                    minHeap.push(3);
                    minHeap.push(12);
                    minHeap.push(98);
                    minHeap.push(37);
                    System.out.println(minHeap.graphViz());
                    break;

                     */

        }

    }


}
