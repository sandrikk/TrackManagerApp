package lists;
import java.util.function.Predicate;

public class DoublyLinkedList<T> implements List<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int currentSize = 0;


    class Node<E> {
        DoublyLinkedList<E>.Node<E> next;
        E data;

        public Node(E data) {
            this.data = data;
        }

        public void disconnect() {
            if (next != null) {
                next.disconnect();
            }
            next = null;
        }

        public E getData() {
            return data;
        }
    }

    @Override
    public boolean add(T t) {
        Node<T> node = new Node<>(t);
        if (head == null)  {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        currentSize++;
        return false;
    }

    private Node<T> getLinkAtIndex(int index) {
        assert index < currentSize : "Index out of bounds";
        Node<T> linkAtIndex = head;
        for (int i =0; i < index; i ++) {
            linkAtIndex = linkAtIndex.next;
        }
        return linkAtIndex;
    }

    @Override
    public void add(int index, T element) throws ArrayIndexOutOfBoundsException {
        if (element == null) throw new NullPointerException();
        if (index> currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        Node<T> linkElement = new Node<>(element);
        currentSize++;
        if (index == 0) {
            linkElement.next = head;
            head = linkElement;
            return;
        }
        Node<T> insertAt = getLinkAtIndex(index-1);
        linkElement.next = insertAt.next;
        insertAt.next = linkElement;
    }

    @Override
    public void clear() {
        head.disconnect();
        currentSize = 0;
        head = null;
        tail = null;
    }

    private int find(Node<T> e, int step, T data) {
        if (data == null) throw new NullPointerException();
        if (e.data.equals(data)) {
            return step;
        }
        if (e.next == null) {
            return -1;
        }
        return find(e.next,++step,data);
    }

    @Override
    public boolean contains(T element) {
        return find(head, 0, element) > -1;
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index> currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        Node<T> linkAtIndex = getLinkAtIndex(index);
        return linkAtIndex.data;
    }

    @Override
    public int indexOf(T element) {
        return find(head,0,element) ;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean remove(int index) throws ArrayIndexOutOfBoundsException {
        if (index > currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> toChange = getLinkAtIndex(index - 1);
            Node<T> toRemove = getLinkAtIndex(index);
            toChange.next = toRemove.next;
            toRemove.next = null;
        }
        currentSize--;
        return true;
    }

    @Override
    public boolean remove(T element) {
        if (element == null) throw new NullPointerException();
        int i = indexOf(element);
        if (i == -1) return false;
        return remove(i);
    }

    @Override
    public T set(int index, T element) throws ArrayIndexOutOfBoundsException {
        if (element == null) throw new NullPointerException();
        if (index > currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        Node<T> linkToChange = getLinkAtIndex(index);
        T returnData = linkToChange.data;
        linkToChange.data = element;
        return returnData;
    }

    @Override
    public int size() {
        return currentSize;
    }

    public Node<T> getHead() {
        return head;
    }


    // linear search unsorted
    public T search(Predicate<T> condition) {
        Node<T> current = head;
        while (current != null) {
            if (condition.test(current.data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

}
