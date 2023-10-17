package trees;

public class BinaryNode<T extends Comparable<T>> {
    T data;
    BinaryNode<T> left, right, parent;

    public BinaryNode(T data) {
        this.data = data;
        left = right = parent = null;
    }

    public T getData() {
        return data;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public BinaryNode<T> getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    public boolean isParent() {
        return parent != null;
    }

    public int getSize() {
        if (data == null) return 0;

        int leftSize = (left != null) ? left.getSize() : 0;
        int rightSize = (right != null) ? right.getSize() : 0;
        return 1 + leftSize + rightSize;
    }

    public void add(T newData) {
        int comparison = newData.compareTo(data);

        if (comparison <= 0) {
            if (left == null) {
                left = new BinaryNode<>(newData);
            } else {
                left.add(newData);
            }
        } else {
            if (right == null) {
                right = new BinaryNode<>(newData);
            } else {
                right.add(newData);
            }
        }
    }

    public int getHeight() {
        int leftHeight = (left != null) ? left.getHeight() : 0;
        int rightHeight = (right != null) ? right.getHeight() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

}
