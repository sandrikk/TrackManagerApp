import maps.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestHashMap {

    private HashMap<String, Integer> hashMap;

    @BeforeEach
    public void setUp() {
        hashMap = new HashMap<>();
    }

    @Test
    public void testPutWhenEmptyShouldPass() {
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        hashMap.put("key3", 3);

        assertEquals(3, hashMap.getSize());
    }

    @Test
    public void testPutWhenKeyAlreadyExistsInTheBucketShouldPass() {
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        hashMap.put("key3", 3);

        // Act: Update the value for an existing key
        hashMap.put("key3", 4);

        // Assert: Verify that the value for the key has been updated
        assertEquals(Integer.valueOf(4), hashMap.get("key3"));
    }

    @Test
    public void testIsEmptyWhenEmptyShouldPass() {
        assertEquals(0, hashMap.getSize());
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void testAddNullNodeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            hashMap.put("key1", null);
        });
    }

    @Test
    public void testGetInvalidKeyThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            hashMap.get("key1");
        });
    }

    @Test
    public void testGetKeyShouldPass() {
        hashMap.put("key1", 1);
        hashMap.put("key2", 2);
        hashMap.put("key3", 3);
        Assertions.assertEquals(1, hashMap.get("key1"));
    }
}
