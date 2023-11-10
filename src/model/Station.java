package model;

public class Station implements Comparable<Station> {
    private final int id, uic;
    private final String code, slug, country, type;
    private final String name;
    private final double geoLat, geoLng;


    public Station(int id, String code, int uic, String name, String slug, String country, String type, double geoLat, double geoLng) {
        //assert id > 0 : "Please provide a positive number";
        this.id = id;
        assert code != null : "Please provide an actual code";
        this.code = code;
        assert !this.code.isBlank() : "The code is blank. Please provide an actual code";
        this.uic = uic;
        assert name != null : "Please provide an actual short name";
        this.name = name;
        assert !this.name.isBlank() : "The short name is blank. Please provide an actual short name";
        this.slug = slug;
        assert country != null : "Please provide an actual country code";
        this.country = country.toUpperCase();
        assert !this.country.isBlank() : "The country code is blank. Please provide an actual country code.";
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;


    }

    @Override
    public int compareTo(Station other) {
        return this.name.compareTo(other.name);
    }


    public double getGeoLat() {
        return geoLat;
    }

    public String getCode() {
        return code;
    }

    public double getGeoLng() {
        return geoLng;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Station " + name + " with code " + code + " (" + geoLat + ", " + geoLng + ")";
    }

}
