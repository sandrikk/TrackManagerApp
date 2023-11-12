package assignment;

import model.Station;
import model.Track;
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
        // Draw stations
        for (Map.Entry<String, Point> stationEntry : stations.entrySet()) {
            Point point = stationEntry.getValue();
            g.fillOval(point.x - 5, point.y - 5, 10, 10);
            g.drawString(stationEntry.getKey(), point.x - 5, point.y - 5);
        }
        // Draw connections
        for (Map.Entry<String, Map<String, Integer>> connectionEntry : connections.entrySet()) {
            String fromStation = connectionEntry.getKey();
            for (String toStation : connectionEntry.getValue().keySet()) {
                Point from = stations.get(fromStation);
                Point to = stations.get(toStation);

                // Only draw the line if both points are not null
                if (from != null && to != null) {
                    g.drawLine(from.x, from.y, to.x, to.y);
                }
            }
        }
    }

    private static double calculateScaleFactor(List<Point> allPoints, int canvasWidth, int canvasHeight) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Point point : allPoints) {
            if (point.x < minX) minX = point.x;
            if (point.x > maxX) maxX = point.x;
            if (point.y < minY) minY = point.y;
            if (point.y > maxY) maxY = point.y;
        }

        double rangeX = maxX - minX;
        double rangeY = maxY - minY;

        double scaleX = canvasWidth / rangeX;
        double scaleY = canvasHeight / rangeY;

        return Math.min(scaleX, scaleY); // Use the smaller scale factor to fit everything on the canvas
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
        return new Point((int) (lng * 10), (int) (lat * 10));
    }


}

