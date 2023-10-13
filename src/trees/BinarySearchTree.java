package trees;

public class BinarySearchTree<T extends Comparable<T>> {
    protected BinaryNode<T> root;

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            return root.getSize();
        }
    }

    public int getHeight() {
        if (isEmpty()) {
            return 0;
        } else {
            return root.getHeight();
        }
    }

    public void add(T data) {
        if (isEmpty()) {
            root = new BinaryNode<>(data);
        } else {
            root.add(data);
        }
    }

    public boolean contains(T data) {
        return contains(root, data);
    }

    private boolean contains(BinaryNode<T> node, T data) {
        if (node == null) {
            return false; // Element not found
        }

        int comparison = data.compareTo(node.data);

        if (comparison == 0) {
            return true; // Element found
        } else if (comparison < 0) {
            return contains(node.left, data); // Search in the left subtree
        } else {
            return contains(node.right, data); // Search in the right subtree
        }
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private BinaryNode<T> delete(BinaryNode<T> node, T data) {
        if (node == null) {
            throw new IllegalArgumentException("Element not found");
        }

        int comparison = data.compareTo(node.data);

        if (comparison < 0) {
            // Data is smaller, search in the left subtree
            node.left = delete(node.left, data);
        } else if (comparison > 0) {
            // Data is larger, search in the right subtree
            node.right = delete(node.right, data);
        } else {
            // Data found, this is the node to delete
            if (node.left == null && node.right == null) {
                // Case 1: Deleting a leaf (no children)
                return null;
            } else if (node.left == null) {
                // Case 2: Deleting a node with one child (right child)
                return node.right;
            } else if (node.right == null) {
                // Case 2: Deleting a node with one child (left child)
                return node.left;
            } else {
                // Case 3: Deleting a node with two children
                // Replace the node with the smallest node in the right subtree
                BinaryNode<T> smallestRight = findMin(node.right);
                node.data = smallestRight.data;
                node.right = delete(node.right, smallestRight.data);
            }
        }
        return node;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}

