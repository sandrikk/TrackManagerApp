import maps.MyHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestHashMap {

    private MyHashMap<String, Integer> myHashMap;

    @BeforeEach
    public void setUp() {

        myHashMap = new MyHashMap<>();
    }

    @Test
    public void testPutWhenEmptyShouldPass() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);
        myHashMap.put("key3", 3);

        assertEquals(3, myHashMap.getSize());
    }

    @Test
    public void testPutWhenKeyAlreadyExistsInTheBucketShouldPass() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);
        myHashMap.put("key3", 3);

        // Act: Update the value for an existing key
        myHashMap.put("key3", 4);

        // Assert: Verify that the value for the key has been updated
        assertEquals(Integer.valueOf(4), myHashMap.get("key3"));
    }

    @Test
    public void testIsEmptyWhenEmptyShouldPass() {
        assertEquals(0, myHashMap.getSize());
        assertTrue(myHashMap.isEmpty());
    }

    @Test
    public void testAddNullNodeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            myHashMap.put("key1", null);
        });
    }

    @Test
    public void testGetInvalidKeyThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            myHashMap.get("key1");
        });
    }

    @Test
    public void testGetKeyShouldPass() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);
        myHashMap.put("key3", 3);
        Assertions.assertEquals(1, myHashMap.get("key1"));
    }

    @Test
    void testGetExistingKey() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);
        Assertions.assertEquals(1, myHashMap.get("key1"));
        Assertions.assertEquals(2, myHashMap.get("key2"));
    }

    @Test
    void testGetNonExistingKey() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            myHashMap.get("nonExistingKey");
        });
    }

    @Test
    void testGetNullKey() {
        // Assuming that the hash method properly handles null keys
        Assertions.assertThrows(NullPointerException.class, () -> {
            myHashMap.get(null);
        });
    }

    @Test
    void testGetNonExistingKeyThrowsException() {
        myHashMap.put("key1", 1); // Add a key to ensure the map is not empty
        Assertions.assertThrows(NullPointerException.class, () -> {
            myHashMap.get("nonExistingKey"); // This key was never added, so should throw
        });
    }

    @Test
    void testGetFromEmptyMapThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            myHashMap.get("anyKey"); // Map is empty, any key should throw
        });
    }
}
