package lists;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Predicate;

public class DoublyLinkedList<T> implements List<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int currentSize = 0;


    class Node<E> {
        Node<E> prev;
        Node<E> next;
        E data;

        public Node(E data) {
            this.data = data;
        }

        public void disconnect() {
            if (next != null) {
                next.prev = null;
                next.disconnect();
            }
            next = null;
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
            node.prev = tail;
            tail = node;
        }
        currentSize++;
        return false;
    }


    private Node<T> getNodeAtIndex(int index) {
        assert index < currentSize : "Index out of bounds";
        Node<T> nodeAtIndex = head;
        //If the index is closer to the head of the list its more efficient to start from the head and move forward
        if (index < currentSize / 2) {
            for (int i = 0; i < index; i++) {
                nodeAtIndex = nodeAtIndex.next;
            }

        //Else the index is closer to the tail of the list, it's more efficient to start from the tail and move backward.
        } else {
            nodeAtIndex = tail;
            for (int i = currentSize - 1; i > index; i--) {
                nodeAtIndex = nodeAtIndex.prev;
            }
        }
        return nodeAtIndex;
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
        Node<T> insertAt = getNodeAtIndex(index-1);
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
        Node<T> linkAtIndex = getNodeAtIndex(index);
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
            Node<T> toChange = getNodeAtIndex(index - 1);
            Node<T> toRemove = getNodeAtIndex(index);
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
        Node<T> linkToChange = getNodeAtIndex(index);
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



    /*

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

     */


}
