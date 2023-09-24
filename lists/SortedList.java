package lists;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Predicate;

public class SortedList <T extends Comparable<T>> implements List<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int currentSize = 0;

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

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
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        currentSize++;

        if (head == null) {
            // The list is empty, so set the new node as both head and tail
            head = newNode;
            tail = newNode;
            return true;
        }

        // Find the correct position to insert the new element
        Node<T> current = head;
        while (current != null && ((Comparable<T>) element).compareTo(current.data) > 0) {
            current = current.next;
        }

        if (current == head) {
            // Insert at the beginning
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (current == null) {
            // Insert at the end
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            // Insert in the middle
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        return true;
    }


    @Override
    public void add(int index, T element) {

    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public void clear() {

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
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index> currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        Node<T> linkAtIndex = getNodeAtIndex(index);
        return linkAtIndex.data;
    }

    @Override
    public T search(Predicate<T> condition) {
        return binarySearch(condition);
    }

    private T binarySearch(Predicate<T> condition) {
        Node<T> current = head;
        int left = 0;
        int right = currentSize - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            Node<T> middleNode = getNodeAtIndex(middle);

            if (condition.test(middleNode.data)) {
                return middleNode.data;
            }

            if (condition.test(current.data)) {
                return current.data;
            }

            int compareResult = condition.test(middleNode.data) ? 0 : middleNode.data.compareTo(current.data);

            if (compareResult < 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
                current = middleNode;
            }
        }

        return null; // Not found
    }

}
