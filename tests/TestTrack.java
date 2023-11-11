import model.Track;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;

public class TestTrack {
    @Test
    void testValidTrackCreation() {
        Track track = new Track("StationA", "StationB", 100);
        Assertions.assertNotNull(track);
        Assertions.assertEquals("StationA", track.getFirstStation());
        Assertions.assertEquals("StationB", track.getSecondStation());
        Assertions.assertEquals(100, track.getDistance());
    }

    @Test
    void testTrackWithNullFirstStation() {
        AssertionError exception = assertThrows(AssertionError.class, () -> new Track(null, "StationB", 100));
        Assertions.assertTrue(exception.getMessage().contains("Please provide an actual first station"));
    }

    @Test
    void testTrackWithBlankFirstStation() {
        AssertionError exception = assertThrows(AssertionError.class, () -> new Track(" ", "StationB", 100));
        Assertions.assertTrue(exception.getMessage().contains("The first station is blank"));
    }

    @Test
    void testTrackWithNullSecondStation() {
        AssertionError exception = assertThrows(AssertionError.class, () -> new Track("StationA", null, 100));
        Assertions.assertTrue(exception.getMessage().contains("Please provide an actual second station"));
    }

    @Test
    void testTrackWithBlankSecondStation() {
        AssertionError exception = assertThrows(AssertionError.class, () -> new Track("StationA", " ", 100));
        Assertions.assertTrue(exception.getMessage().contains("The second station is blank"));
    }

    @Test
    public void testToString() {
        Track track = new Track("StationA", "StationB", 120);
        String expected = "Track{firstStation='StationA', secondStation='StationB', distance=120}";
        String actual = track.toString();
        Assertions.assertEquals(expected, actual, "The toString method should return the correct string representation");
    }
}
