package trees;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    @Override
    public void add(T data) {
        root = add(data, root);
    }

    private BinaryNode<T> add(T data, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<>(data); // Insert new node.
        }

        int compareResult = data.compareTo(node.data);

        if (compareResult < 0) {
            node.left = add(data, node.left);
        } else if (compareResult > 0) {
            node.right = add(data, node.right);
        }

        // Balance the node if necessary
        return balance(node);
    }

    private BinaryNode<T> rotateRight(BinaryNode<T> node) {
        BinaryNode<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    // Rotate a node to the left
    private BinaryNode<T> rotateLeft(BinaryNode<T> node) {
        BinaryNode<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    // Balance a node based on its balance factor
    private BinaryNode<T> balance(BinaryNode<T> node) {
        int balanceFactor = getBalanceFactor(node);

        // Left Heavy situation
        if (balanceFactor > 1) {
            // Check for Left-Right case
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left); // First left rotation on the left child
            }
            return rotateRight(node); // Then right rotation on the node
        }

        // Right Heavy situation
        if (balanceFactor < -1) {
            // Check for Right-Left case
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right); // First right rotation on the right child
            }
            return rotateLeft(node); // Then left rotation on the node
        }

        return node; // Node is already balanced
    }

    //Get the balance factor of a node (height of left subtree - height of right subtree)
    public int getBalanceFactor(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = (node.left != null) ? node.left.getHeight() : -1;
        int rightHeight = (node.right != null) ? node.right.getHeight() : -1;
        return leftHeight - rightHeight;
    }


    public BinaryNode<T> getRoot() {
        return root; // assuming 'root' is the name of the root node in AVLTree
    }

    public String toGraphVizAVLTree() {
        StringBuilder sb = new StringBuilder();
        sb.append("diGraph AVLTree {\n");
        toGraphVizAVLTree(root, sb);
        sb.append("}\n");
        return sb.toString();
    }

    private void toGraphVizAVLTree(BinaryNode<T> node, StringBuilder sb) {
        if (node != null) {
            if (node.getLeft() != null) {
                sb.append("    ").append(node.getData()).append(" -> ").append(node.getLeft().getData()).append(";\n");
            }
            if (node.getRight() != null) {
                sb.append("    ").append(node.getData()).append(" -> ").append(node.getRight().getData()).append(";\n");
            }
            toGraphVizAVLTree(node.getLeft(), sb);
            toGraphVizAVLTree(node.getRight(), sb);
        }
    }

}
