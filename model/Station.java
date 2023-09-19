package model;

import lists.DoublyLinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Station {
    private final int id, uic;
    private final String code, slug, country, type;
    private final String nameShort, nameMedium, nameLong;
    private final double geoLat, geoLng;

    private static final DoublyLinkedList<Station> stations = readStationsFromCSV();

    public Station(int id, String code, int uic, String nameShort, String nameMedium, String nameLong, String slug, String country, String type, double geoLat, double geoLng) {
        assert id > 0 : "Please provide a positive number";
        this.id = id;
        assert code != null : "Please provide an actual code";
        this.code = code;
        assert !this.code.isBlank() : "The code is blank. Please provide an actual code";
        this.uic = uic;
        assert nameShort != null : "Please provide an actual short name";
        this.nameShort = nameShort;
        assert !this.nameShort.isBlank() : "The short name is blank. Please provide an actual short name";
        assert nameMedium != null : "Please provide an actual medium name";
        this.nameMedium = nameMedium;
        assert !this.nameMedium.isBlank() : "The medium name is blank. Please provide an actual medium name";
        assert nameLong != null : "Please provide an actual long name";
        this.nameLong = nameLong;
        assert !this.nameLong.isBlank() : "The long name is blank. Please provide an actual long name";
        this.slug = slug;
        assert country != null : "Please provide an actual country code";
        this.country = country.toUpperCase();
        assert !this.country.isBlank() : "The country code is blank. Please provide an actual country code.";
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
    }

    public static DoublyLinkedList<Station> readStationsFromCSVold() {
        DoublyLinkedList<Station> stations = new DoublyLinkedList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("./resources/stations.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // headings
        scanner.nextLine();

        while (scanner.hasNext()) {
            String rec = scanner.nextLine();
            String[] fields = rec.split(",");

            // Remove double quotes from fields
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].startsWith("\"") && fields[i].endsWith("\"")) {
                    fields[i] = fields[i].substring(1, fields[i].length() - 1);
                }
            }

            Station newStation = new Station(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    Integer.parseInt(fields[2]),
                    fields[3],
                    fields[4],
                    fields[5],
                    fields[6],
                    fields[7],
                    fields[8],
                    Double.parseDouble(fields[9]),
                    Double.parseDouble(fields[10]));
            stations.add(newStation);
        }

        return stations;
    }

    public static DoublyLinkedList<Station> readStationsFromCSV() {
        DoublyLinkedList<Station> stations = new DoublyLinkedList<>();
        CsvReader csvReader = new CsvReader("./resources/stations.csv");
        List<String[]> csvData = csvReader.readCsv();

        for (String[] fields : csvData) {
            int id = Integer.parseInt(fields[0]);
            String code = fields[1];
            int uic = Integer.parseInt(fields[2]);
            String nameShort =  fields[3];
            String nameMedium = fields[4];
            String nameLong = fields[5];
            String slug = fields[6];
            String country = fields[7];
            String type = fields[8];
            Double geoLat = Double.parseDouble(fields[9]);
            Double geoLgn = Double.parseDouble(fields[10]);

            Station newStation = new Station(id, code, uic, nameShort, nameMedium, nameLong, slug, country, type, geoLat, geoLgn);
            stations.add(newStation);
        }

        return stations;
    }

    public static DoublyLinkedList<Station> getStations() {
        return stations;
    }

    public String getName() {
        return nameShort;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", uic=" + uic +
                ", nameShort='" + nameShort + '\'' +
                ", nameMedium='" + nameMedium + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", slug='" + slug + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", geoLat=" + geoLat +
                ", geoLng=" + geoLng +
                '}';
    }
}
