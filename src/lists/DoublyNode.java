package lists;

public class DoublyNode<T> {
    DoublyNode<T> prev;
    DoublyNode<T> next;
    T data;

    public DoublyNode(T data) {
        this.data = data;
    }

    public DoublyNode<T> getPrev() {
        return prev;
    }

    public DoublyNode<T> getNext() {
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
