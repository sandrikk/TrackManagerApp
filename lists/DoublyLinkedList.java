package lists;


import java.util.function.Predicate;

public class DoublyLinkedList<E> implements List<E> {


    private LinkElement<E> head = null;
    private LinkElement<E> tail = null;
    private int currentSize = 0;


    class LinkElement<E> {
        DoublyLinkedList<E>.LinkElement<E> next;
        E data;

        public LinkElement(E data) {
            this.data = data;
        }

        public void disconnect() {
            if (next != null) {
                next.disconnect();
            }
            next = null;
        }
    }


    @Override
    public boolean add(E a) {
        LinkElement<E> linkElement = new LinkElement<>(a);
        if (head == null)  {
            head = linkElement;
            tail = linkElement;
        } else {
            tail.next = linkElement;
            tail = linkElement;
        }
        currentSize++;
        return false;
    }

    private LinkElement<E> getLinkAtIndex(int index) {
        assert index < currentSize : "Index out of bounds";
        LinkElement<E> linkAtIndex = head;
        for (int i =0; i < index; i ++) {
            linkAtIndex = linkAtIndex.next;
        }
        return linkAtIndex;
    }

    @Override
    public void add(int index, E element) throws ArrayIndexOutOfBoundsException {
        if (element == null) throw new NullPointerException();
        if (index> currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        LinkElement<E> linkElement = new LinkElement<>(element);
        currentSize++;
        if (index == 0) {
            linkElement.next = head;
            head = linkElement;
            return;
        }
        LinkElement<E> insertAt = getLinkAtIndex(index-1);
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


    /**
     *
     * recursive function to find an element in the list.
     *
     * @param e
     * @param step
     * @param data
     * @return
     *
     */
    private int find(LinkElement<E> e, int step, E data) {
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
    public boolean contains(E element) {
        if (find(head,0,element) > -1) return true;
        return false;
    }

    @Override
    public E get(int index) throws ArrayIndexOutOfBoundsException {
        if (index> currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        LinkElement<E> linkAtIndex = getLinkAtIndex(index);
        return linkAtIndex.data;
    }

    @Override
    public int indexOf(E element) {
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
            LinkElement<E> toChange = getLinkAtIndex(index - 1);
            LinkElement<E> toRemove = getLinkAtIndex(index);
            toChange.next = toRemove.next;
            toRemove.next = null;
        }
        currentSize--;
        return true;
    }

    @Override
    public boolean remove(E element) {
        if (element == null) throw new NullPointerException();
        int i = indexOf(element);
        if (i == -1) return false;
        return remove(i);
    }

    @Override
    public E set(int index, E element) throws ArrayIndexOutOfBoundsException {
        if (element == null) throw new NullPointerException();
        if (index > currentSize-1) throw  new ArrayIndexOutOfBoundsException();
        LinkElement<E> linkToChange = getLinkAtIndex(index);
        E returnData = linkToChange.data;
        linkToChange.data = element;
        return returnData;
    }

    @Override
    public int size() {
        return currentSize;
    }

    public E searchByName(String nameToSearch) {
        return null;
    }


    // linear search unsorted
    public E search(Predicate<E> condition) {
        LinkElement<E> current = head;
        while (current != null) {
            if (condition.test(current.data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }


}
