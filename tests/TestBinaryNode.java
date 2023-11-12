import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trees.BinaryNode;

public class TestBinaryNode {
    private BinaryNode<Integer> rootNode;
    private BinaryNode<Integer> childNode;

    @BeforeEach
    public void setup() {
        rootNode = new BinaryNode<>(5);
        childNode = new BinaryNode<>(3);
        rootNode.setLeft(childNode);
        childNode.setParent(rootNode);
    }

    @Test
    public void testGetParent() {
        Assertions.assertEquals(rootNode, childNode.getParent());
    }

    @Test
    public void testIsLeafForLeafNode() {
        BinaryNode<Integer> leafNode = new BinaryNode<>(7);
        Assertions.assertTrue(leafNode.isLeaf());
    }

    @Test
    public void testIsLeafForNonLeafNode() {
        Assertions.assertFalse(rootNode.isLeaf());
    }

    @Test
    public void testIsParentForNodeWithParent() {
        Assertions.assertTrue(childNode.isParent());
    }

    @Test
    public void testIsParentForRootNode() {
        Assertions.assertFalse(rootNode.isParent());
    }
}

