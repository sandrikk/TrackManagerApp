package lists;
import model.Station;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListOld<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public ListOld() {
        head = null;
        size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void remove(T element) {
        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {
            if (current.data.equals(element)) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    head = current.next;
                }
                size--;
                return; // Found and removed the element
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {

            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public Station searchByName(String name) {
        Node<T> current = head;
        while (current != null) {

            if (current.data instanceof Station station) {
                if (station.getName().equals(name)) {
                    System.out.println(station.getName());
                    System.out.println(name);
                    return station;
                }
            }
            current = current.next;
        }
        return null;
    }
}
