package assignment;

import graphs.WeightedMatrixGraph;
import heap.MinHeap;
import lists.DoublyLinkedList;
import lists.SortedList;
import maps.MyHashMap;
import model.Station;
import model.Track;
import sort.Sort;
import trees.AVLTree;
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
    static AVLTree<String> stationAVLTree = new AVLTree<>();


    public static void main(String[] args) {
        readStationsFromCSV();

        // Call the method to read stations from CSV
        String[] stationCodes = stationsArrayList.stream()
                .map(Station::getCode)
                .toArray(String[]::new);
        connectionGraph = new WeightedMatrixGraph<>(true, stationCodes);


        readTracksFromCSV();
        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Search for a station by name (linear search)");
            System.out.println("2. Search for a station by code (hash table)");
            System.out.println("3. Search for a station by name (binary search)");
            System.out.println("4. Sort an ArrayList of connections by length (mergesort)");
            System.out.println("5. Sort an ArrayList of connections by length (selectionsort");
            System.out.println("6. A* algorithm");
            System.out.println("7. Dijkstra algorithm");
            System.out.println("8. MCST algorithm - Prim");
            System.out.println("9. Print AVL tree with stations");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                    String inputCode = scanner.nextLine().toLowerCase();
                    System.out.println(inputCode);
                    Station foundStationByCode = stationMyMap.get(inputCode);
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

                    Comparator<Track> trackComparator = Comparator.comparingInt(Track::getDistance);
                    Sort sort = new Sort();

                    sort.mergeSort(tracksArrayList, trackComparator);
                    for (Track track : tracksArrayList) {
                        System.out.println(track);
                    }

                    break;
                case 5:
                    System.out.println("Sorting tracks by length using selection sort...");

                    Comparator<Track> trackDistanceComparator = Comparator.comparingInt(Track::getDistance);
                    Sort sortInstance = new Sort();

                    sortInstance.selectionSort(tracksArrayList, trackDistanceComparator);
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
                    // Get coordinates for the rectangle
                    System.out.print("Enter the latitude of the first corner: ");
                    double lat1 = scanner.nextDouble();
                    System.out.print("Enter the longitude of the first corner: ");
                    double lon1 = scanner.nextDouble();
                    System.out.print("Enter the latitude of the opposite corner: ");
                    double lat2 = scanner.nextDouble();
                    System.out.print("Enter the longitude of the opposite corner: ");
                    double lon2 = scanner.nextDouble();

                    // Create a subgraph with stations within the rectangle
                    WeightedMatrixGraph<String> subgraph = createSubgraphForRectangle(connectionGraph, lat1, lon1, lat2, lon2);

                    // Find the Minimum Spanning Tree of the subgraph
                    List<GraphUtils.Edge<String>> mstEdges = GraphUtils.findMinimumSpanningTreePrim(subgraph);

                    double totalLength = 0;
                    System.out.println("Minimum Track Connections:");
                    for (GraphUtils.Edge<String> edge : mstEdges) {
                        totalLength += edge.weight();
                        System.out.println(edge);
                    }

                    System.out.println("Total Track Length: " + totalLength);
                    break;
                case 9:
                    System.out.println(stationAVLTree.toGraphVizAVLTree());
                case 10:
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
        CsvReader csvReader = new CsvReader("./resources/stations.csv", "^(\\d+),([A-Z]+),([\\d]+),([^,]+),([^,]+),([^,]+),([\\w-]+),([A-Z]+),([\\w-]+),([\\-\\d.]+),([\\-\\d.]+)$");


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
            stationAVLTree.add(newStation.getCode());
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

            if (stationMap.containsKey(firstStationCode) && stationMap.containsKey(secondStationCode)) {
                connectionGraph.connect(firstStationCode, secondStationCode, distance);
                tracksArrayList.add(newTrack);

            }

        }

    }

    private static boolean isWithinRectangle(double lat, double lon, double lat1, double lon1, double lat2, double lon2) {
        return Math.min(lat1, lat2) <= lat && lat <= Math.max(lat1, lat2) &&
                Math.min(lon1, lon2) <= lon && lon <= Math.max(lon1, lon2);
    }

    private static WeightedMatrixGraph<String> createSubgraphForRectangle(WeightedMatrixGraph<String> originalGraph, double lat1, double lon1, double lat2, double lon2) {
        List<String> stationsInRectangle = new ArrayList<>();

        // Filter stations within the rectangle
        for (String stationCode : stationMap.keySet()) {
            Station station = stationMap.get(stationCode);
            if (isWithinRectangle(station.getGeoLat(), station.getGeoLng(), lat1, lon1, lat2, lon2)) {
                stationsInRectangle.add(stationCode);
            }
        }

        // Create a subgraph with these stations
        WeightedMatrixGraph<String> subgraph = new WeightedMatrixGraph<>(originalGraph.isDirected(), stationsInRectangle.toArray(new String[0]));

        // Add edges based on the original graph
        for (String station1 : stationsInRectangle) {
            for (String station2 : stationsInRectangle) {
                if (!station1.equals(station2) && originalGraph.isConnected(station1, station2)) {
                    double weight = originalGraph.getWeight(station1, station2);
                    subgraph.connect(station1, station2, weight);
                }
            }
        }

        return subgraph;
    }

}
