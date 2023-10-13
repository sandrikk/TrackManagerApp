package lists;

import java.util.Comparator;

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
    public boolean remove(T node) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }

    public T binarySearch(Comparator<T> comparator, T key) {
        int left = 0;
        int right = currentSize - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            T middleElement = get(middle);

            int compareResult = comparator.compare(middleElement, key);

            if (compareResult == 0) {
                return middleElement; // Found a match
            } else if (compareResult < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null; // Not found
    }







}
