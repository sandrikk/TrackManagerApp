package utils;

import graphs.WeightedMatrixGraph;
import model.Station;
import model.Track;

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
