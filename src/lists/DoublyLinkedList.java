package lists;

public class DoublyLinkedList<T> implements List<T> {

    private DoublyNode<T> head = null;
    private DoublyNode<T> tail = null;
    private int currentSize = 0;

    @Override
    public boolean add(T node) {
        DoublyNode<T> newNode = new DoublyNode<>(node);
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

    public void add(int index, T node) throws ArrayIndexOutOfBoundsException {
        if (node == null)
            throw new NullPointerException();
        if (index > currentSize - 1)
            throw new ArrayIndexOutOfBoundsException();
        DoublyNode<T> linkNode = new DoublyNode<>(node);
        currentSize++;
        if (index == 0) {
            linkNode.next = head;
            head = linkNode;
            return;
        }
        DoublyNode<T> insertAt = getNodeAtIndex(index - 1);
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
        DoublyNode<T> linkAtIndex = getNodeAtIndex(index);
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
            DoublyNode<T> toChange = getNodeAtIndex(index - 1);
            DoublyNode<T> toRemove = getNodeAtIndex(index);
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

    private DoublyNode<T> getNodeAtIndex(int index) {
        assert index < currentSize : "Index out of bounds";
        DoublyNode<T> nodeAtIndex = head;
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

    private int find(DoublyNode<T> t, int step, T data) {
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

    public DoublyNode<T> getHead() {
        return head;
    }

    public void setHead(DoublyNode<T> head) {
        this.head = head;
    }

    public DoublyNode<T> getTail() {
        return tail;
    }
}