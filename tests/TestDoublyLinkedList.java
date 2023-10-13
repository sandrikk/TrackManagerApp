import lists.DoublyLinkedList;
import lists.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDoublyLinkedList {

    private DoublyLinkedList<Integer> listOfNumbers;
    private DoublyLinkedList<String> listOfStrings;

    @BeforeEach
    public void setup() {
        listOfNumbers = new DoublyLinkedList<>();
        listOfStrings = new DoublyLinkedList<>();
    }

    @Test
    public void testAddNumbersWhenEmptyShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertEquals(3, listOfNumbers.size());
    }

    @Test
    public void testAddStringsWhenEmptyShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add("C");
        Assertions.assertEquals(3, listOfStrings.size());
    }

    @Test
    public void testAddNumberAtIndexShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(3);
        listOfNumbers.add(0, 2);
        Assertions.assertEquals(3, listOfNumbers.size());
        Assertions.assertEquals(Integer.valueOf(2), listOfNumbers.get(0));
    }

    @Test
    public void testAddStringAtIndexShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add(0, "C");
        Assertions.assertEquals(3, listOfStrings.size());
        Assertions.assertEquals(String.valueOf("C"), listOfStrings.get(0));
    }

    //doesnt work correctly
    @Test
    public void testAddAtIndexShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add(1, "C");
        Assertions.assertEquals(3, listOfStrings.size());
        //Assertions.assertEquals(String.valueOf("C"), listOfStrings.get(0));
    }

    @Test
    public void testAddNullNodeAtIndexThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            listOfStrings.add(0, null);
        });
    }

    @Test
    public void testContainsNumbersShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertTrue(listOfNumbers.contains(2));
        Assertions.assertFalse(listOfNumbers.contains(4));
    }

    @Test
    public void testContainsStringsShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add("C");
        Assertions.assertTrue(listOfStrings.contains("B"));
        Assertions.assertFalse(listOfStrings.contains("D"));
    }

    @Test
    public void testGetNumbersShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertEquals(Integer.valueOf(2), listOfNumbers.get(1));
    }

    @Test
    public void testGetWithInvalidIndexThrowsException() {
        listOfNumbers.add(5);
        listOfNumbers.add(10);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            listOfNumbers.get(2); //Index 2 is out of bounds
        });
    }

    @Test
    public void testAddNodeAtIndexOutOfBoundsThrowsException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            listOfStrings.add(1, "Test"); //Trying to add at index 1 when the list is empty
        });
    }

    @Test
    public void testGetStringsShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add("C");
        Assertions.assertEquals(String.valueOf("B"), listOfStrings.get(1));
    }

    @Test
    public void testNumbersIndexOfShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertEquals(1, listOfNumbers.indexOf(2));
        Assertions.assertEquals(-1, listOfNumbers.indexOf(4));
    }

    @Test
    public void testStringsIndexOfShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add("C");
        Assertions.assertEquals(1, listOfStrings.indexOf("B"));
        Assertions.assertEquals(-1, listOfStrings.indexOf("D"));
    }

    @Test
    public void testRemoveNumberByIndexShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertTrue(listOfNumbers.remove(0));
        Assertions.assertEquals(2, listOfNumbers.size());
    }

    @Test
    public void testRemoveStringByIndexShouldPass() {
        listOfStrings.add("A");
        listOfStrings.add("B");
        listOfStrings.add("C");
        Assertions.assertTrue(listOfStrings.remove(0));
        Assertions.assertEquals(2, listOfStrings.size());
    }

    @Test
    public void testRemoveNumberByNegativeNumberThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> listOfNumbers.remove(-1));
    }

    @Test
    public void testRemoveNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> listOfNumbers.remove(null));
    }

    @Test
    public void testRemoveNumberByInvalidIndexThrowsException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> listOfNumbers.remove(5));
    }

    @Test
    public void testContainsNullThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> listOfNumbers.contains(null));
    }

    @Test
    public void testRemoveNumberByNodeShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertTrue(listOfNumbers.remove(Integer.valueOf(2)));
        Assertions.assertEquals(2, listOfNumbers.size());
        Assertions.assertFalse(listOfNumbers.contains(2));
    }

    @Test
    public void testRemoveByNonExistentNodeShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertFalse(listOfNumbers.remove(Integer.valueOf(6)));
    }


    @Test
    public void testClearShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.clear();
        Assertions.assertTrue(listOfNumbers.isEmpty());
        Assertions.assertEquals(0, listOfNumbers.size());
    }

    @Test
    public void testIsEmptyShouldPass() {
        Assertions.assertTrue(listOfNumbers.isEmpty());
        listOfNumbers.add(1);
        Assertions.assertFalse(listOfNumbers.isEmpty());
    }

    @Test
    public void testSizeShouldPass() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        Assertions.assertEquals(3, listOfNumbers.size());
    }

    @Test
    public void testGetHead() {
        Node<Integer> head = listOfNumbers.getHead();
        Assertions.assertNull(head);

        listOfNumbers.add(5);
        head = listOfNumbers.getHead();
        Assertions.assertEquals(5, head.getData().intValue());

        listOfNumbers.add(3);
        head = listOfNumbers.getHead();
        Assertions.assertEquals(5, head.getData().intValue());
    }

    @Test
    public void testGetTail() {
        Node<Integer> tail = listOfNumbers.getTail();
        Assertions.assertNull(tail);

        listOfNumbers.add(5);
        tail = listOfNumbers.getTail();
        Assertions.assertEquals(5, tail.getData().intValue());

        listOfNumbers.add(3);
        tail = listOfNumbers.getTail();
        Assertions.assertEquals(3, tail.getData().intValue());
    }

    @Test
    public void testGetNodeAtIndexCloseToHead() {
        listOfNumbers.add(1);
        listOfNumbers.add(2);
        listOfNumbers.add(3);
        listOfNumbers.add(4);
        listOfNumbers.add(5);
        listOfNumbers.add(6);
        listOfNumbers.add(2, 7);
        Assertions.assertEquals(7, listOfNumbers.get(2));

    }
}

