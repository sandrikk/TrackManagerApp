import lists.RecList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRecList {
    private RecList<Integer> listOfNumbers;

    @BeforeEach
    public void setup() {
        listOfNumbers = new RecList<>();
    }

    @Test
    public void testAddAndGetData() {
        listOfNumbers.add(10);
        Assertions.assertEquals(10, listOfNumbers.getData(), "The data should be 10");
    }

    @Test
    public void testAddNullShouldThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            listOfNumbers.add(null);
        }, "Adding null should throw IllegalArgumentException");
    }

    @Test
    public void testIsEmptyOnNewList() {
        Assertions.assertTrue(listOfNumbers.isEmpty(), "New list should be empty");
    }

    @Test
    public void testIsEmptyAfterAdding() {
        listOfNumbers.add(10);
        Assertions.assertFalse(listOfNumbers.isEmpty(), "List should not be empty after adding an element");
    }

    @Test
    public void testGetCountOnNewList() {
        Assertions.assertEquals(0, listOfNumbers.getCount(), "New list should have a count of 0");
    }

    @Test
    public void testGetCountAfterAdding() {
        listOfNumbers.add(10);
        Assertions.assertEquals(1, listOfNumbers.getCount(), "List should have a count of 1 after adding an element");
        listOfNumbers.add(20);
        Assertions.assertEquals(2, listOfNumbers.getCount(), "List should have a count of 2 after adding another element");
    }

    @Test
    public void testContainsWithPresentData() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertTrue(listOfNumbers.contains(2), "The list should contain the number 2");
    }

    @Test
    public void testContainsWithAbsentData() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        Assertions.assertFalse(listOfNumbers.contains(4), "The list should not contain the number 4");
    }

    @Test
    public void testContainsWithNull() {
        listOfNumbers.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> listOfNumbers.contains(null), "Should throw IllegalArgumentException when searching for null");
    }

    @Test
    public void testToStringWhenNotEmpty() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        String expected = "{1, {2, {3, []}}}";
        Assertions.assertEquals(expected, listOfNumbers.toString(), "The toString method should return the correct string representation");
    }

    @Test
    public void testToStringWhenEmpty() {
        String expected = "[]";
        Assertions.assertEquals(expected, listOfNumbers.toString(), "The toString method should return '[]' for an empty list");
    }

}