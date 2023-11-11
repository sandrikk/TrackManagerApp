import lists.DoublyNode;
import lists.RecList;
import lists.SortedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class TestSortedList {
    private SortedList<Integer> listOfNumbers;

    @BeforeEach
    public void setup() {
        listOfNumbers = new SortedList<>();
    }

    @Test
    public void testAddShouldMaintainOrder() {
        listOfNumbers.add(3);
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        Assertions.assertEquals(1, listOfNumbers.get(0));
        Assertions.assertEquals(2, listOfNumbers.get(1));
        Assertions.assertEquals(3, listOfNumbers.get(2));
    }

    @Test
    public void testIsEmptyOnNewList() {
        Assertions.assertTrue(listOfNumbers.isEmpty());
    }

    @Test
    public void testSizeAfterAddingElements() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        Assertions.assertEquals(2, listOfNumbers.size());
    }

    @Test
    public void testClearShouldEmptyList() {
        listOfNumbers.add(1);
        listOfNumbers.clear();
        Assertions.assertTrue(listOfNumbers.isEmpty());
    }

    @Test
    public void testGetOnEmptyListThrowsException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> listOfNumbers.get(0));
    }

    @Test
    public void testIndexOfOnEmptyList() {
        int index = listOfNumbers.indexOf(1);
        Assertions.assertEquals(-1, index, "Index should be -1 because the list is empty.");
    }

    @Test
    public void testIndexOfSingleElementList() {
        listOfNumbers.add(1);
        int index = listOfNumbers.indexOf(1);
        Assertions.assertEquals(0, index, "Index should be 0 because it's the only element in the list.");
    }

    @Test
    public void testIndexOfFirstElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        int index = listOfNumbers.indexOf(1);
        Assertions.assertEquals(0, index, "Index should be 0 because the element is at the beginning of the list.");
    }

    @Test
    public void testIndexOfLastElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        int index = listOfNumbers.indexOf(3);
        Assertions.assertEquals(2, index, "Index should be 2 because the element is at the end of the list.");
    }

    @Test
    public void testIndexOfMiddleElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        int index = listOfNumbers.indexOf(2);
        Assertions.assertEquals(1, index, "Index should be 1 because the element is in the middle of the list.");
    }

    @Test
    public void testIndexOfNonExistentElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        int index = listOfNumbers.indexOf(4);
        Assertions.assertEquals(-1, index, "Index should be -1 because the element does not exist in the list.");
    }

    @Test
    public void testRemoveByIndex() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.remove(0);
        Assertions.assertEquals(1, listOfNumbers.size());
        Assertions.assertEquals(2, listOfNumbers.get(0));
    }

    @Test
    public void testRemoveByElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.remove(Integer.valueOf(1));
        Assertions.assertEquals(1, listOfNumbers.size());
        Assertions.assertEquals(2, listOfNumbers.get(0));
    }

    @Test
    public void testRemoveNull() {
        listOfNumbers.add(1);
        boolean result = listOfNumbers.remove(null);
        Assertions.assertFalse(result, "Removing null should return false.");
    }

    @Test
    public void testRemoveFromEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfNumbers.remove(0);
        });
    }

    @Test
    public void testRemoveWithNegativeIndex() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfNumbers.remove(-1);
        });
    }

    @Test
    public void testRemoveValidElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        boolean result = listOfNumbers.remove(1); // removing the element at index 1
        Assertions.assertTrue(result);
        Assertions.assertEquals(2, listOfNumbers.size());
        Assertions.assertFalse(listOfNumbers.contains(2));
    }

    @Test
    public void testRemoveFirstElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.remove(0);
        Assertions.assertEquals(Integer.valueOf(2), listOfNumbers.get(0));
    }

    @Test
    public void testRemoveMiddleElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.remove(1);
        Assertions.assertEquals(Integer.valueOf(3), listOfNumbers.get(1));
    }

    @Test
    public void testRemoveLastElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.remove(2); // Assuming 0-based indexing
        Assertions.assertEquals(Integer.valueOf(2), listOfNumbers.get(1));
    }

    @Test
    public void testRemoveNonExistentElementByValue() {
        listOfNumbers.add(1);
        boolean result = listOfNumbers.remove(Integer.valueOf(2));
        Assertions.assertFalse(result, "Removing a non-existent element should return false.");
    }

    @Test
    public void testRemoveOnlyElementByIndex() {
        listOfNumbers.add(1);
        boolean result = listOfNumbers.remove(0); // Index of the first and only element is 0
        Assertions.assertTrue(result, "Removing the only element should return true.");
        Assertions.assertTrue(listOfNumbers.isEmpty(), "The list should be empty after removing the only element.");
    }

    @Test
    public void testRemoveOnlyElementByValue() {
        listOfNumbers.add(1);
        boolean result = listOfNumbers.remove(Integer.valueOf(1)); // Removing by value
        Assertions.assertTrue(result, "Removing the only element should return true.");
        Assertions.assertTrue(listOfNumbers.isEmpty(), "The list should be empty after removing the only element.");
    }

    @Test
    public void testRemoveMiddleNode() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        boolean result = listOfNumbers.remove(Integer.valueOf(2));
        Assertions.assertTrue(result, "Middle node should be removed.");
        Assertions.assertEquals(2, listOfNumbers.size(), "List size should be decremented.");
        Assertions.assertEquals(Integer.valueOf(1), listOfNumbers.get(0), "First element should be 1.");
        Assertions.assertEquals(Integer.valueOf(3), listOfNumbers.get(1), "Second element should be 3.");
    }

    @Test
    public void testGetNodeAtIndexOutOfBounds() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listOfNumbers.get(listOfNumbers.size()); // Index is out of bounds
        });
    }

    @Test
    public void testGetNodeAtIndexCloserToHead() {
        // Given
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.add(4);
        listOfNumbers.add(5);

        // When
        DoublyNode<Integer> nodeAtIndexOne = listOfNumbers.getNodeAtIndex(1); // Closer to head

        // Then
        Assertions.assertNotNull(nodeAtIndexOne, "Node at index should not be null.");
        Assertions.assertEquals(Integer.valueOf(2), nodeAtIndexOne.getData(), "Node at index 1 should have the value 2.");
    }

    @Test
    public void testGetNodeAtIndexCloserToTail() {
        // Given
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.add(4);
        listOfNumbers.add(5);

        // When
        DoublyNode<Integer> nodeAtIndexThree = listOfNumbers.getNodeAtIndex(3); // Closer to tail

        // Then
        Assertions.assertNotNull(nodeAtIndexThree, "Node at index should not be null.");
        Assertions.assertEquals(Integer.valueOf(4), nodeAtIndexThree.getData(), "Node at index 3 should have the value 4.");
    }

    @Test
    public void testContainsElementReturnsTrue() {
        // Given
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);

        // When
        boolean result = listOfNumbers.contains(2);

        // Then
        Assertions.assertTrue(result, "List should contain the element 2.");
    }

    @Test
    public void testContainsElementReturnsFalseForNonExistentElement() {
        // Given
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);

        // When
        boolean result = listOfNumbers.contains(4);

        // Then
        Assertions.assertFalse(result, "List should not contain the element 4.");
    }

    @Test
    public void testContainsNullReturnsFalse() {
        // Given
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);

        // When
        boolean result = listOfNumbers.contains(null);

        // Then
        Assertions.assertFalse(result, "List should return false for null element search.");
    }

    //binary
    @Test
    public void testBinarySearchFindsElement() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Integer result = listOfNumbers.binarySearch(Integer::compareTo, 2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testBinarySearchKeyFound() {
        // Assuming the list is already sorted
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.add(4);
        listOfNumbers.add(5);

        Comparator<Integer> comparator = Integer::compareTo;
        Integer key = 3;

        Integer result = listOfNumbers.binarySearch(comparator, key);
        Assertions.assertEquals(key, result, "Binary search should find the key.");
    }

    @Test
    public void testBinarySearchKeyNotFoundLeft() {
        // Assuming the list is already sorted
        listOfNumbers.add(10);
        listOfNumbers.add(20);
        listOfNumbers.add(30);
        listOfNumbers.add(40);
        listOfNumbers.add(50);

        Comparator<Integer> comparator = Integer::compareTo;
        Integer key = 5; // This key would be to the left of the first element if it were in the list

        Integer result = listOfNumbers.binarySearch(comparator, key);
        Assertions.assertNull(result, "Binary search should not find the key.");
    }

    @Test
    public void testBinarySearchKeyNotFoundRight() {
        // Assuming the list is already sorted
        listOfNumbers.add(10);
        listOfNumbers.add(20);
        listOfNumbers.add(30);
        listOfNumbers.add(40);
        listOfNumbers.add(50);

        Comparator<Integer> comparator = Integer::compareTo;
        Integer key = 55; // This key would be to the right of the last element if it were in the list

        Integer result = listOfNumbers.binarySearch(comparator, key);
        Assertions.assertNull(result, "Binary search should not find the key.");
    }
}
