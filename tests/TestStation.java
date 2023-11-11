
import model.Station;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStation {
    @Test
    public void testConstructorStoresDataCorrectly() {
        // Given
        int id = 1;
        String code = "a";
        int uic = 1;
        String name = "StationName";
        String slug = "stationname";
        String country = "NL";
        String type = "TypeA";
        double geoLat = 1.2;
        double geoLng = 1.1;

        // When
        Station station = new Station(id, code, uic, name, slug, country, type, geoLat, geoLng);

        // Then
        Assertions.assertNotNull(station);
        Assertions.assertEquals(code, station.getCode());
        Assertions.assertEquals(name, station.getName());
        Assertions.assertEquals(geoLat, station.getGeoLat());
        Assertions.assertEquals(geoLng, station.getGeoLng());
    }

    @Test
    public void testCompareTo() {
        // Given
        Station station1 = new Station(1, "a", 1, "AlphaStation", "alpha", "NL", "TypeA", 1.2, 1.1);
        Station station2 = new Station(2, "b", 2, "BetaStation", "beta", "NL", "TypeB", 2.3, 2.2);

        // When & Then
        Assertions.assertTrue(station1.compareTo(station2) < 0, "station1 should be less than station2");
        Assertions.assertTrue(station2.compareTo(station1) > 0, "station2 should be greater than station1");
        Assertions.assertEquals(0, station1.compareTo(station1), "station1 should be equal to itself");

        // When comparing two stations with the same name
        Station station3 = new Station(3, "c", 3, "AlphaStation", "alpha", "NL", "TypeA", 3.4, 3.3);

        // Then
        Assertions.assertEquals(0, station1.compareTo(station3), "station1 should be equal to station3");
    }

    @Test
    public void testToString() {
        // Given
        Station station = new Station(1, "ST001", 101, "Central Station", "central-station", "CountryX", "Main", 52.379189, 4.899431);

        // Expected string representation of the Station object
        String expected = "Station Central Station with code ST001 (52.379189, 4.899431)";

        // When
        String actual = station.toString();

        // Then
        Assertions.assertEquals(expected, actual, "The toString method should return the correct string representation");
    }



}
