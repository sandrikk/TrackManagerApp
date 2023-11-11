package lists;

import java.util.Comparator;

public class SortedList <T extends Comparable<T>> implements List<T> {

    private DoublyNode<T> head = null;
    private DoublyNode<T> tail = null;
    private int currentSize = 0;

    @Override
    public boolean add(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);
        currentSize++;

        if (head == null) {
            // The list is empty, so set the new node as both head and tail
            head = newNode;
            tail = newNode;
            return true;
        }

        // Find the correct position to insert the new element
        DoublyNode<T> current = head;
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
    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;

    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    public DoublyNode<T> getNodeAtIndex(int index) {
        assert index < currentSize : "Index out of bounds";
        DoublyNode<T> nodeAtIndex = head;
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
        int index = 0;
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1; // Element not found
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index> currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        DoublyNode<T> linkAtIndex = getNodeAtIndex(index);
        return linkAtIndex.data;
    }

    @Override
    public boolean remove(T node) {
        if (node == null) {
            return false;
        }
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(node)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // Removing the head
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // Removing the tail
                }
                currentSize--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }
        DoublyNode<T> toRemove = getNodeAtIndex(index);
        if (toRemove.prev != null) {
            toRemove.prev.next = toRemove.next;
        } else {
            head = toRemove.next; // Removing the head
        }
        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
            tail = toRemove.prev; // Removing the tail
        }
        currentSize--;
        return true;
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
