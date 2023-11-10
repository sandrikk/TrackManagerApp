package utils;

import graphs.WeightedMatrixGraph;
import model.Station;
import model.Track;
import utils.CsvReader;

import java.util.*;

public class Astar {

    private static WeightedMatrixGraph<String> connectionGraph;
    private static HashMap<String, Station> map;
    private static ArrayList<Track> connections;
    private static ArrayList<Station> stations;

    public Astar() {
        map = new HashMap<>();
        connections = new ArrayList<>();
        stations = new ArrayList<>();

        readStationsFromCSV();

        String[] stationCodes = stations.stream()
                .map(Station::getCode)
                .toArray(String[]::new);
        connectionGraph = new WeightedMatrixGraph<>(false, stationCodes);

        readTracksFromCSV();

    }


    // A helper class to store station and its estimated total cost (f-score)
    private class AstarNode implements Comparable<AstarNode> {
        String station;
        double fScore;
        AstarNode cameFrom;

        AstarNode(String station, double fScore) {
            this.station = station;
            this.fScore = fScore;
            this.cameFrom = null;
        }

        @Override
        public int compareTo(AstarNode other) {
            return Double.compare(this.fScore, other.fScore);
        }
    }

    public List<String> astar(String startStation, String goalStation) {
        if (!map.containsKey(startStation.toLowerCase()) || !map.containsKey(goalStation.toLowerCase())) {
            return null; // Invalid stations provided
        }


        // Priority queue to store stations to be explored based on their estimated total cost
        PriorityQueue<AstarNode> openSet = new PriorityQueue<>();

        // Set to keep track of stations that have been visited
        HashSet<String> closedSet = new HashSet<>();

        // Map to track the best-known cost to reach each station
        HashMap<String, Double> gScore = new HashMap<>();

        // Initialize gScore for all stations as infinity (except start station as 0)
        for (String station : map.keySet()) {
            gScore.put(station, Double.POSITIVE_INFINITY);
        }
        gScore.put(startStation, 0.0);

        // Add the start station to the open set with its estimated total cost (f-score)
        openSet.add(new AstarNode(startStation, 0.0));

        // Main A* loop
        while (!openSet.isEmpty()) {
            // Get the station with the lowest estimated total cost
            AstarNode current = openSet.poll();

            if (current.station.equals(goalStation)) {
                // Found the goal station, reconstruct and return the path
                return reconstructPath(current);
            }

            closedSet.add(current.station);

            for (String neighbor : connectionGraph.getNeighbors(current.station)) {
                if (closedSet.contains(neighbor)) {
                    // Skip stations that have already been visited
                    continue;
                }

                // Calculate tentative gScore for this neighbor station
                double tentativeGScore = gScore.get(current.station) + connectionGraph.getWeight(current.station, neighbor);

                if (tentativeGScore < gScore.get(neighbor)) {
                    // Found a better path to this neighbor station
                    gScore.put(neighbor, tentativeGScore);

                    // Calculate the heuristic function h() (you can use a distance estimation here)
                    double hScore = estimateDistance(neighbor, goalStation);

                    // Add the neighbor to the open set with its estimated total cost (f-score)
                    openSet.add(new AstarNode(neighbor, tentativeGScore + hScore));
                }
            }
        }

        // No path found
        return null;
    }

    public double estimateDistance(String node, String goal) {
        if (node.equals(goal)) {
            return 0;
        }

        Station nodeStation = map.get(node.toLowerCase());
        Station goalStation = map.get(goal.toLowerCase());


        double dx = nodeStation.getGeoLat() - goalStation.getGeoLat();
        double dy = nodeStation.getGeoLng() - goalStation.getGeoLng();

        return Math.sqrt(dx * dx + dy * dy);
    }




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
            //map.put(newStation.getCode(), newStation);
            map.put(newStation.getCode().toLowerCase(), newStation);


        }
    }

    // Reconstruct the path from the goal station to the start station
    private List<String> reconstructPath(AstarNode goal) {
        List<String> path = new ArrayList<>();
        AstarNode current = goal;

        while (current != null) {
            path.add(0, current.station);
            current = current.cameFrom;
        }

        return path;
    }

    public void printMapContents() {
        for (Map.Entry<String, Station> entry : map.entrySet()) {
            String stationCode = entry.getKey();
            Station station = entry.getValue();
            System.out.println("Station Code: " + stationCode);
            System.out.println("Station Information: " + station);
        }
    }

    public static void readTracksFromCSV() {
        CsvReader csvReader = new CsvReader("./resources/tracks.csv", "^[a-z]+,[a-z]+,\\d+,\\d+,\\d+$");
        List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            String firstStationCode = fields[1];
            String secondStationCode = fields[2];
            int distance = Integer.parseInt(fields[3]);

            if (map.containsKey(firstStationCode.toLowerCase()) && map.containsKey(secondStationCode.toLowerCase())) {
                connectionGraph.connect(firstStationCode, secondStationCode, distance);
                connections.add(new Track(firstStationCode, secondStationCode, distance));
            }

        }
    }


}
