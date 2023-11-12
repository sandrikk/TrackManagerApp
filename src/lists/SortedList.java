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
            head = newNode;
            tail = newNode;
            return true;
        }

        DoublyNode<T> current = head;
        while (current != null && ((Comparable<T>) element).compareTo(current.data) > 0) {
            current = current.next;
        }

        if (current == head) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (current == null) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
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
        return -1;
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
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
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
            head = toRemove.next;
        }
        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
            tail = toRemove.prev;
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
                return middleElement;
            } else if (compareResult < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return null;
    }

}
