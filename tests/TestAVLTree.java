import trees.AVLTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trees.BinaryNode;

import static org.junit.Assert.assertEquals;

public class TestAVLTree {
    private AVLTree<Integer> avlTree;

    @BeforeEach
    public void setUp() {
        avlTree = new AVLTree<>();
    }

    @Test
    public void testAdd_BalancedAfterSingleRightRotation() {
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(1); // Triggers a single right rotation

        BinaryNode<Integer> root = avlTree.getRoot();
        Assertions.assertEquals(Integer.valueOf(2), root.getData());
        Assertions.assertEquals(Integer.valueOf(1), root.getLeft().getData());
        Assertions.assertEquals(Integer.valueOf(3), root.getRight().getData());
    }

    @Test
    public void testAdd_BalancedAfterRightLeftRotation() {
        // This sequence should cause a right-left rotation.
        avlTree.add(3);
        avlTree.add(1);
        avlTree.add(2); // The addition of 2 should trigger the rotation

        BinaryNode<Integer> root = avlTree.getRoot();
        // After the rotation, the root should be 2, with 1 and 3 as children.
        Assertions.assertEquals(Integer.valueOf(2), root.getData(), "Root should be 2 after rotation");
        Assertions.assertEquals(Integer.valueOf(1), root.getLeft().getData(), "Left child should be 1");
        Assertions.assertEquals(Integer.valueOf(3), root.getRight().getData(), "Right child should be 3");
    }

    @Test
    public void testAdd_BalancedAfterSingleLeftRotation() {
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3); // Triggers a single left rotation

        BinaryNode<Integer> root = avlTree.getRoot();
        Assertions.assertEquals(Integer.valueOf(2), root.getData());
        Assertions.assertEquals(Integer.valueOf(1), root.getLeft().getData());
        Assertions.assertEquals(Integer.valueOf(3), root.getRight().getData());
    }


    @Test
    public void testAdd_NoRotationNeeded() {
        avlTree.add(2);
        avlTree.add(1);
        avlTree.add(3); // Balanced already, no rotation needed

        BinaryNode<Integer> root = avlTree.getRoot();
        Assertions.assertEquals(Integer.valueOf(2), root.getData());
        Assertions.assertEquals(Integer.valueOf(1), root.getLeft().getData());
        Assertions.assertEquals(Integer.valueOf(3), root.getRight().getData());
    }

    @Test
    public void testAdd_BalancedAfterLeftRightRotation() {
        avlTree.add(3);
        avlTree.add(1);
        avlTree.add(2); // Triggers a left-right rotation

        BinaryNode<Integer> root = avlTree.getRoot();
        // Add more detailed assertions to check the structure of the tree
        Assertions.assertNotNull(root, "Root should not be null");
        Assertions.assertEquals(Integer.valueOf(2), root.getData(), "Root should be 2 after left-right rotation");
        Assertions.assertNotNull(root.getLeft(), "Left child should not be null");
        Assertions.assertEquals(Integer.valueOf(1), root.getLeft().getData(), "Left child should be 1");
        Assertions.assertNotNull(root.getRight(), "Right child should not be null");
        Assertions.assertEquals(Integer.valueOf(3), root.getRight().getData(), "Right child should be 3");
    }

    @Test
    void testToGraphVizAVLTree() {
        // Add elements to the AVL tree
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(1); // This should cause a right rotation

        // Expected GraphViz representation of the AVL tree after the above insertions
        String expectedGraphViz = "diGraph AVLTree {\n" +
                "    2 -> 1;\n" +
                "    2 -> 3;\n" +
                "}\n";

        // Generate the GraphViz representation of the AVL tree
        String actualGraphViz = avlTree.toGraphVizAVLTree();

        // Assert that the actual GraphViz representation matches the expected one
        Assertions.assertEquals(expectedGraphViz, actualGraphViz);
    }

}
