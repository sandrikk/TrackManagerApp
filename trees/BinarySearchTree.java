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

    public int size() {

        if (isEmpty()) {
            return 0;
        } else {
            return root.getSize();
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

    /*

    public void add(T data) {
        root = insert(root, data);
    }

    private BinaryNode<T> insert(BinaryNode<T> node, T data) {
        if (node == null) {
            //Create a new node if the tree is empty or we've reached a leaf node
            return new BinaryNode<>(data);
        }

        //Compare the data and decide whether to insert in the left or right subtree
        int comparison = data.compareTo(node.data);

        if (comparison <= 0) {
            //Insert into the left subtree if data is less than or equal to current node's data
            node.left = insert(node.left, data);
        } else {
            //Insert into the right subtree if data is greater
            node.right = insert(node.right, data);
        }

        return node;
    }

     */

}

