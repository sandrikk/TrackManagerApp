package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Station {
    private final int id, uic;
    private final String code, slug, country, type;
    private final String nameShort, nameMedium, nameLong;
    private final double geoLat, geoLng;

    private static final ArrayList<Station> stations = readStationsFromCSV();



    public Station(int id, String code, int uic, String nameShort, String nameMedium, String nameLong, String slug, String country, String type, double geoLat, double geoLng) {
        assert id>0 : "Please provide a positive number";
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
        //assert this.country.length() >=3 : "The country code is too big. Please provide an actual country code.";
        assert !this.country.isBlank(): "The country code is blank. Please provide an actual country code.";
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
    }


    public static ArrayList<Station> readStationsFromCSV() {
        ArrayList<Station> stations = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("./resources/stations.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //headings
        scanner.nextLine();

        while (scanner.hasNext()) {
            String rec = scanner.nextLine();
            String[] fields = rec.split(",");
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

    public static void printStations() {
        for (Station s: getStations()) {
            System.out.println(s);
        }
    }

    public static ArrayList<Station> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "model.Station{" +
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


