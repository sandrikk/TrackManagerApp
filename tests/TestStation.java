
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class TestStation {
    @Test
    public void testConstructorStoreDataCorrectly() {
        Station s = new Station(1, "a", 1, "a", "a", "a", "a", "a", "a", 1.2, 1.1);
        assertNotNull(s);

    }

    @BeforeEach
    public void createDeventerStation() {

    }
}
