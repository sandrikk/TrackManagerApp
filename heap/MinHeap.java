package heap;

import model.Station;

import java.lang.reflect.Array;

public class MinHeap<T extends Comparable<T>> {
    private T[] items;
    private int size;
    //private Comperator<T> comperator;

    public MinHeap(Class<T > clazz, int initialSize) {
        //this.items = (T[])new Object[initialSize];
        // no contains bc we dont need it
        this.items = (T[])Array.newInstance(clazz, initialSize);
    }

    public  T peek() {
        if (isEmpty()) {
            return null;
        }
        return  items[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {
        if (size == items.length) {
            remap();
        }
        items[size]=data;
        percolateUp(size);
        size++;
    }

    private void remap() {
        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), items.length * 2);
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T result = items[0];
        size--;
        items[0] = items[size];
        percolateDown(0);
        return result;
    }

    private void percolateDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallestIndex = index;

        if (leftChildIndex < size && items[leftChildIndex].compareTo(items[smallestIndex]) < 0) {
            smallestIndex = leftChildIndex;
        }

        if (rightChildIndex < size && items[rightChildIndex].compareTo(items[smallestIndex]) < 0) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            percolateDown(smallestIndex);
        }
    }

    private void percolateUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && items[index].compareTo(items[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void swap(int index, int parentIndex) {
        T temp = items[index];
        items[index] = items[parentIndex];
        items[parentIndex] = temp;
    }

    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
        //MinHeap test = new MinHeap<>(5);
        //MinHeap<Station> test = new MinHeap<>(Station.class, 5);
        //test.getSize();

        MinHeap<Integer> test = new MinHeap<>(Integer.class, 5);
        test.push(5);
        test.push(3);
        test.push(7);
        //test.push(1);
        test.push(17);
        test.push(18);
        test.push(19);
        test.push(13);

        System.out.println("Min Element: " + test.peek()); // Output: 1

        while (!test.isEmpty()) {
            System.out.println("Popped: " + test.pop());
        }

    }
}
