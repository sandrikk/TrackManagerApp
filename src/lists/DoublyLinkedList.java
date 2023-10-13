package lists;

public class DoublyLinkedList<T> implements List<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int currentSize = 0;

    @Override
    public boolean add(T node) {
        Node<T> newNode = new Node<>(node);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        currentSize++;
        return false;
    }

    @Override
    public void add(int index, T node) throws ArrayIndexOutOfBoundsException {
        if (node == null)
            throw new NullPointerException();
        if (index > currentSize - 1)
            throw new ArrayIndexOutOfBoundsException();
        Node<T> linkNode = new Node<>(node);
        currentSize++;
        if (index == 0) {
            linkNode.next = head;
            head = linkNode;
            return;
        }
        Node<T> insertAt = getNodeAtIndex(index - 1);
        linkNode.next = insertAt.next;
        insertAt.next = linkNode;
    }

    @Override
    public boolean contains(T node) {
        return find(head, 0, node) > -1;
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index > currentSize - 1)
            throw new ArrayIndexOutOfBoundsException();
        Node<T> linkAtIndex = getNodeAtIndex(index);
        return linkAtIndex.data;
    }

    @Override
    public int indexOf(T node) {
        return find(head, 0, node);
    }

    @Override
    public boolean remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index > currentSize - 1)
            throw new ArrayIndexOutOfBoundsException();
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> toChange = getNodeAtIndex(index - 1);
            Node<T> toRemove = getNodeAtIndex(index);
            toChange.next = toRemove.next;
            toRemove.next = null;
        }
        currentSize--;
        return true;
    }

    @Override
    public boolean remove(T node) {
        if (node == null)
            throw new NullPointerException();
        int i = indexOf(node);
        if (i == -1)
            return false;
        return remove(i);
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void clear() {
        head.disconnect();
        currentSize = 0;
        head = null;
        tail = null;
    }

    private Node<T> getNodeAtIndex(int index) {
        assert index < currentSize : "Index out of bounds";
        Node<T> nodeAtIndex = head;
        if (index < currentSize / 2) {
            for (int i = 0; i < index; i++) {
                nodeAtIndex = nodeAtIndex.next;
            }
        } else {
            nodeAtIndex = tail;
            for (int i = currentSize - 1; i > index; i--) {
                nodeAtIndex = nodeAtIndex.prev;
            }
        }
        return nodeAtIndex;
    }

    private int find(Node<T> t, int step, T data) {
        if (data == null)
            throw new NullPointerException();
        if (t.data.equals(data)) {
            return step;
        }
        if (t.next == null) {
            return -1;
        }
        return find(t.next, ++step, data);
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }
}