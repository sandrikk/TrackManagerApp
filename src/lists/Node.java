package lists;

public class Node<T> {
    Node<T> prev;
    Node<T> next;
    T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }

    public void disconnect() {
        if (next != null) {
            next.prev = null;
            next.disconnect();
        }
        next = null;
    }
}
