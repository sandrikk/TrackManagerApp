import maps.MyHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        Assertions.assertEquals(3, myHashMap.getSize());
    }

    @Test
    public void testPutWhenKeyAlreadyExistsInTheBucketShouldPass() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);
        myHashMap.put("key3", 3);


        myHashMap.put("key3", 4);


        Assertions.assertEquals(Integer.valueOf(4), myHashMap.get("key3"));
    }

    @Test
    public void testIsEmptyWhenEmptyShouldPass() {
        Assertions.assertEquals(0, myHashMap.getSize());
        Assertions.assertTrue(myHashMap.isEmpty());
    }

    @Test
    public void testAddNullNodeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> myHashMap.put("key1", null));
    }

    @Test
    public void testGetInvalidKeyThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> myHashMap.get("key1"));
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
        Assertions.assertThrows(NullPointerException.class, () -> myHashMap.get("nonExistingKey"));
    }

    @Test
    void testGetNullKey() {
        Assertions.assertThrows(NullPointerException.class, () -> myHashMap.get(null));
    }

    @Test
    void testGetNonExistingKeyThrowsException() {
        myHashMap.put("key1", 1);
        Assertions.assertThrows(NullPointerException.class, () -> myHashMap.get("nonExistingKey"));
    }

    @Test
    void testGetFromEmptyMapThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> myHashMap.get("anyKey"));
    }

    @Test
    public void testGetKeyNotInAnyBucketThrowsException() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);

        String nonExistentKey = "key3";
        Assertions.assertThrows(NullPointerException.class,
                () -> myHashMap.get(nonExistentKey),
                "Key not found in the bucket."
        );
    }

    @Test
    public void testGetKeyNotAtHeadOfBucket() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);

        Integer value = myHashMap.get("key2");
        Assertions.assertEquals(Integer.valueOf(2), value);
    }

    @Test
    public void testContainsWithExistingKey() {
        myHashMap.put("key1", 1);
        Assertions.assertTrue(myHashMap.contains("key1"));
    }

    @Test
    public void testContainsWithNonExistingKey() {
        myHashMap.put("key1", 1);
        Assertions.assertFalse(myHashMap.contains("key2"));
    }

    @Test
    public void testRemoveExistingKey() {
        myHashMap.put("key1", 1);
        myHashMap.remove("key1");
        Assertions.assertFalse(myHashMap.contains("key1"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        myHashMap.put("key1", 1);
        myHashMap.remove("key2");
        Assertions.assertTrue(myHashMap.contains("key1"));
    }

    @Test
    public void testRemoveKeyFromEmptyBucket() {
        myHashMap.remove("key1");
        Assertions.assertFalse(myHashMap.contains("key1"));
    }

    @Test
    public void testRemoveKeyThatIsNotAtHeadOfBucket() {
        myHashMap.put("key1", 1);
        myHashMap.put("key2", 2);
        myHashMap.put("key3", 3);
        myHashMap.remove("key2");
        Assertions.assertFalse(myHashMap.contains("key2"));
        Assertions.assertTrue(myHashMap.contains("key1"));
        Assertions.assertTrue(myHashMap.contains("key3"));
    }

    @Test
    public void testRemoveOnlyKeyInBucket() {
        myHashMap.put("key1", 1);
        myHashMap.remove("key1");
        Assertions.assertEquals(0, myHashMap.getSize());
    }

    @Test
    public void testContainsKeyInEmptyMap() {
        Assertions.assertFalse(myHashMap.contains("key1"));
    }
}
