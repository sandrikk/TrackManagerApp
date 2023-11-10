package model;

public class Track {
    private final String firstStation;
    private final String secondStation;
    private final int distance;

    public Track(String firstStation, String secondStation, int distance) {
        assert firstStation != null : "Please provide an actual first station";
        this.firstStation = firstStation;
        assert !this.firstStation.isBlank() : "The first station is blank. Please provide an actual station";
        assert secondStation != null : "Please provide an actual second station";
        this.secondStation = secondStation;
        assert !this.secondStation.isBlank() : "The second station is blank. Please provide an actual station";
        assert !this.secondStation.equals(this.firstStation);
        this.distance = distance;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public String getSecondStation() {
        return secondStation;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Track{" +
                "firstStation='" + firstStation + '\'' +
                ", secondStation='" + secondStation + '\'' +
                ", distance=" + distance +
                '}';
    }
}
