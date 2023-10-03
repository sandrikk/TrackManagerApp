import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trees.BinarySearchTree;

public class TestBinarySearchTree {
    private BinarySearchTree<Integer> tree;

    @BeforeEach
    public void setup() {
        tree = new BinarySearchTree<>();
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(tree.isEmpty());
        tree.add(5);
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    public void testAddAndGetSize() {
        Assertions.assertEquals(0, tree.getSize());
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        Assertions.assertEquals(7, tree.getSize());
    }

    @Test
    public void testAddAndContains() {
        Assertions.assertFalse(tree.contains(5));
        tree.add(5);
        Assertions.assertTrue(tree.contains(5));
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        Assertions.assertTrue(tree.contains(2));
        Assertions.assertTrue(tree.contains(8));
        Assertions.assertFalse(tree.contains(9));
    }

    @Test
    public void testGetHeight() {
        Assertions.assertEquals(0, tree.getHeight());
        tree.add(5);
        Assertions.assertEquals(1, tree.getHeight());
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);
        Assertions.assertEquals(3, tree.getHeight());
    }
}

