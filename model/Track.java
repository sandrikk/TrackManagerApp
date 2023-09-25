package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Track {
    private final Station firstStation;
    private final Station secondStation;
    private final double distance;
    private final static ArrayList<Track> tracks = readTracksFromCSV();

    public Track(Station firstStation, Station secondStation) {
        assert firstStation != null : "Please provide an actual first station";
        this.firstStation = firstStation;
        //assert !this.firstStation.isBlank() : "The first station is blank. Please provide an actual station";
        assert secondStation != null : "Please provide an actual second station";
        this.secondStation = secondStation;
        //assert !this.secondStation.isBlank() : "The second station is blank. Please provide an actual station";
        assert !this.secondStation.equals(this.firstStation);
        this.distance = calculateDistance(firstStation, secondStation);
    }

    public static ArrayList<Track> readTracksFromCSV() {
        ArrayList<Track> tracks = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("./resources/tracks.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // headings
        scanner.nextLine();

        while (scanner.hasNext()) {
            String rec = scanner.nextLine();
            String[] fields = rec.split(",");
            Track newTrack = new Track(
                    fields[0],
                    fields[1]);
            tracks.add(newTrack);
        }

        return tracks;
    }

    public static void printTracks() {
        for (Track t: getTracks()) {
            System.out.println(t);
        }
    }

    public static ArrayList<Track> getTracks() {
        return tracks;
    }

    private double calculateDistance(Station station1, Station station2) {

        return Math.sqrt(Math.pow(station2.getGeoLat() - station1.getGeoLat(), 2) + Math.pow(station2.getGeoLng() - station1.getGeoLng(), 2));
    }

    @Override
    public String toString() {
        return "Track{" +
                "firstStation='" + firstStation + '\'' +
                ", secondStation='" + secondStation + '\'' +
                '}';
    }
}
