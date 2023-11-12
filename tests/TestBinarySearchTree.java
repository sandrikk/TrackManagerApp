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

    @Test
    public void testDeleteLeafNode() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);

        tree.delete(2); // Deleting a leaf node
        Assertions.assertFalse(tree.contains(2));
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);

        tree.delete(5); // Deleting a node with two children
        Assertions.assertFalse(tree.contains(5));
        Assertions.assertTrue(tree.contains(4));
        Assertions.assertTrue(tree.contains(6));
    }

    @Test
    public void testDeleteNonExistentNode() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tree.delete(8);
        });
    }


    @Test
    public void testDeleteNodeWithOneChildRight() {
        tree.add(63);
        tree.add(80);
        tree.add(85);
        tree.add(23);
        tree.add(24);

        tree.delete(23); // Deleting a node with one child (right child)
        Assertions.assertFalse(tree.contains(23));
        Assertions.assertTrue(tree.contains(24));
    }

    @Test
    public void testDeleteNodeWithOneChildLeft() {
        tree.add(63);
        tree.add(80);
        tree.add(85);
        tree.add(24);
        tree.add(18);

        tree.delete(24); // Deleting a node with one child (left child)
        Assertions.assertFalse(tree.contains(24));
        Assertions.assertTrue(tree.contains(18));
    }

    @Test
    public void testToGraphViz() {
        tree.add(5);
        tree.add(3);
        tree.add(7);

        String expectedGraphViz = "diGraph BinarySearchTree {\n" +
                "    5 -> 3;\n" +
                "    5 -> 7;\n" +
                "}\n";

        Assertions.assertEquals(expectedGraphViz, tree.toGraphViz());
    }


}

