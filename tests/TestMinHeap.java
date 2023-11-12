import heap.MinHeap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestMinHeap {
    private MinHeap<Integer> minHeapIntegers;
    private MinHeap<String> minHeapStrings;

    @BeforeEach
    public void setup() {
        minHeapIntegers = new MinHeap<>(Integer.class, 5);
        minHeapStrings = new MinHeap<>(String.class, 5);

    }

    @Test
    void testIsEmptyWhenEmptyShouldReturnTrue() {
        // integers
        Assertions.assertEquals(0, minHeapIntegers.getSize());
        Assertions.assertTrue(minHeapIntegers.isEmpty());

        // strings
        Assertions.assertEquals(0, minHeapStrings.getSize());
        Assertions.assertTrue(minHeapStrings.isEmpty());
    }

    @Test
    void testIsEmptyAfterPushAndPopShouldReturnTrue() {
        // integers
        minHeapIntegers.push(5);
        minHeapIntegers.pop();
        assertTrue(minHeapIntegers.isEmpty());
        assertEquals(0, minHeapIntegers.getSize());

        // strings
        minHeapIntegers.push(5);
        minHeapIntegers.pop();
        assertTrue(minHeapIntegers.isEmpty());
        assertEquals(0, minHeapIntegers.getSize());
    }

    @Test
    void testPushAndPeekWithIntegersShouldPass() {
        minHeapIntegers.push(5);
        minHeapIntegers.push(3);
        minHeapIntegers.push(7);
        assertEquals((Integer) 3, minHeapIntegers.peek());
    }

    @Test
    void testPushAndPeekWithStringsShouldPass() {
        minHeapStrings.push("apple");
        minHeapStrings.push("banana");
        minHeapStrings.push("orange");
        assertEquals("apple", minHeapStrings.peek());
    }

    @Test
    void testRemapWhenCapacityExceededResizeArrayWithIntegersShouldPass() {
        minHeapIntegers.push(1);
        minHeapIntegers.push(2);
        minHeapIntegers.push(3);
        minHeapIntegers.push(4);
        minHeapIntegers.push(5);
        minHeapIntegers.push(6);

        assertEquals(10, minHeapIntegers.getCapacity());
    }

    @Test
    void testRemapWhenCapacityExceededResizeArrayWithStringsShouldPass() {
        minHeapStrings.push("apple");
        minHeapStrings.push("banana");
        minHeapStrings.push("orange");
        minHeapStrings.push("pineapple");
        minHeapStrings.push("kiwi");
        minHeapStrings.push("lemon");

        assertEquals(10, minHeapStrings.getCapacity());
    }

    @Test
    void testPopWhenIsEmptyReturnNull() {
        // integers
        assertTrue(minHeapIntegers.isEmpty());
        assertNull(minHeapIntegers.pop());

        // strings
        assertTrue(minHeapStrings.isEmpty());
        assertNull(minHeapStrings.pop());
    }

    @Test
    void testPeekWhenIsEmptyReturnNull() {
        // integers
        assertTrue(minHeapIntegers.isEmpty());
        assertNull(minHeapIntegers.peek());

        // strings
        assertTrue(minHeapStrings.isEmpty());
        assertNull(minHeapStrings.peek());
    }

    @Test
    void testPopWhenIsNotEmptyWithIntegersShouldPass() {
        minHeapIntegers.push(0);
        minHeapIntegers.push(1);
        minHeapIntegers.push(2);
        minHeapIntegers.pop();

        assertEquals(minHeapIntegers.getSize(), 2);

    }

    @Test
    void testPopWhenIsNotEmptyWithStringsShouldPass() {
        minHeapStrings.push("apple");
        minHeapStrings.push("banana");
        minHeapStrings.push("orange");
        minHeapStrings.pop();

        assertEquals(minHeapStrings.getSize(), 2);

    }
    @Test
    void testPercolateUpWithIntegersShouldPass() {
        minHeapIntegers.push(21);
        minHeapIntegers.push(26);
        minHeapIntegers.push(32);
        minHeapIntegers.push(24);
        minHeapIntegers.push(31);
        minHeapIntegers.push(65);
        minHeapIntegers.push(16);
        minHeapIntegers.push(19);
        minHeapIntegers.push(68);
        minHeapIntegers.push(13);

        assertEquals(13, (int) minHeapIntegers.peek());
        assertEquals(10, minHeapIntegers.getSize());

    }

    @Test
    void testPercolateDownWithIntegersShouldPass() {
        minHeapIntegers.push(21);
        minHeapIntegers.push(26);
        minHeapIntegers.push(32);
        minHeapIntegers.push(24);
        minHeapIntegers.push(13);
        minHeapIntegers.push(31);
        minHeapIntegers.push(65);
        minHeapIntegers.push(16);
        minHeapIntegers.push(19);
        minHeapIntegers.push(68);

        minHeapIntegers.pop();

        assertEquals(16, (int) minHeapIntegers.peek());
        assertEquals(9, minHeapIntegers.getSize());
    }

    @Test
    public void testGraphViz() {
        // Given
        MinHeap<Integer> minHeap = new MinHeap<>(Integer.class, 10);
        minHeap.push(10);
        minHeap.push(20);
        minHeap.push(5);
        minHeap.buildHeap();

        // When
        String graphVizOutput = minHeap.graphViz();

        // Then
        String expectedOutput =
                "diGraph MinHeap {\n" +
                        "    5 -> 20;\n" +
                        "    5 -> 10;\n" +
                        "}\n";

        Assertions.assertEquals(expectedOutput, graphVizOutput, "The graphViz output should match the expected graph representation.");
    }












}
