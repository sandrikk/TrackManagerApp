package trees;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    // Additional methods specific to AVL Tree can be added here

    @Override
    public void add(T data) {
        // Implement AVL-specific insertion logic
        super.add(data); // Call the add method from the parent class
        // Perform AVL balancing operations
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

        //Left Heavy
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        //Right Heavy
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    //Get the balance factor of a node (height of left subtree - height of right subtree)
    private int getBalanceFactor(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = (node.left != null) ? node.left.getHeight() : -1;
        int rightHeight = (node.right != null) ? node.right.getHeight() : -1;
        return leftHeight - rightHeight;
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
