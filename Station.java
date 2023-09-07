import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Station {
    private final int id;
    private String code;
    private int uic;
    private String nameShort;
    private String nameMedium;
    private String nameLong;
    private String slug;
    private String country;
    private String type;
    private double geoLat;
    private double geoLng;

    private static ArrayList<Station> stations = readStationsFromCSV();



    public Station(int id, String code, int uic, String nameShort, String nameMedium, String nameLong, String slug, String country, String type, double geoLat, double geoLng) {
        assert id>0 : "Please provide a positive number";
        this.id = id;
        this.code = code;
        this.uic = uic;
        this.nameShort = nameShort;
        this.nameMedium = nameMedium;
        this.nameLong = nameLong;
        this.slug = slug;
        this.country = country;
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public static ArrayList<Station> readStationsFromCSV() {
        ArrayList<Station> stations = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("stations.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String headings = scanner.nextLine();

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
}


