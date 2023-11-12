package assignment;

import utils.CsvReader;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Drawing extends JPanel {
    private static final Map<String, Point> stations = new HashMap<>();
    private static final Map<String, Map<String, Integer>> connections = new HashMap<>();

    public Drawing() {
        readStationsFromCSV();
        readTracksFromCSV();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the minimum Y to move the map up
        int minY = stations.values().stream().mapToInt(p -> p.y).min().orElse(0);
        int yOffset = minY; // Move up by the minimum Y value

        // Draw stations
        for (Map.Entry<String, Point> stationEntry : stations.entrySet()) {
            Point point = stationEntry.getValue();
            // Apply yOffset to move the map up
            g.fillOval(point.x - 5, point.y - 5 - yOffset, 5, 5);
            //g.drawString(stationEntry.getKey(), point.x - 5, point.y - 20 - yOffset); // Adjust y for text to prevent overlap with the dot
        }

        // Set the color for the connections
        g.setColor(Color.RED);

        // Draw connections
        for (Map.Entry<String, Map<String, Integer>> connectionEntry : connections.entrySet()) {
            String fromStation = connectionEntry.getKey();
            Point from = stations.get(fromStation);
            if (from != null) {
                for (String toStation : connectionEntry.getValue().keySet()) {
                    Point to = stations.get(toStation);
                    if (to != null) {
                        // Apply yOffset to move the map up
                        g.drawLine(from.x, from.y - yOffset, to.x, to.y - yOffset);
                    }
                }
            }
        }

        // Reset the color for any further painting
        g.setColor(Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Drawing());
                frame.setSize(800, 800);
                frame.setLocationRelativeTo(null); // Center on screen
                frame.setVisible(true);
            }
        });
    }

    public static void readStationsFromCSV() {
        CsvReader csvReader = new CsvReader("./resources/stations.csv", "");
        java.util.List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            String code = fields[1];
            double geoLat = Double.parseDouble(fields[9]);
            double geoLng = Double.parseDouble(fields[10]);

            Point point = convertGeoToScreen(geoLat, geoLng);
            stations.put(code.toLowerCase(), point);
        }
    }

    public static void readTracksFromCSV() {
        CsvReader csvReader = new CsvReader("./resources/tracks.csv", "^[a-z]+,[a-z]+,\\d+,\\d+,\\d+$");
        List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            String firstStationCode = fields[0].toLowerCase(); // Convert to lowercase
            String secondStationCode = fields[1].toLowerCase(); // Convert to lowercase
            int distance = Integer.parseInt(fields[2]);

            // Ensure the station codes in connections are in lower case
            connections.putIfAbsent(firstStationCode, new HashMap<>());
            connections.get(firstStationCode).put(secondStationCode, distance);
        }
    }

    private static Point convertGeoToScreen(double lat, double lng) {
        // You need a real implementation here
        return new Point((int) (lng * 20), (int) (lat * 20));
    }


}

