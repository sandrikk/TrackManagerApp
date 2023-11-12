
package heap;

import java.lang.reflect.Array;

public class MinHeap<T extends Comparable<T>> {
    private T[] items;
    private int size;

    public MinHeap(Class<T> clazz, int initialSize) {
        this.items = (T[]) Array.newInstance(clazz, initialSize);
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return items[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T data) {
        if (size == items.length) {
            remap();
        }
        items[size] = data;
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

    private void swap(int a, int b) {
        T temp = items[a];
        items[a] = items[b];
        items[b] = temp;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return items.length;
    }

    public String graphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("diGraph MinHeap {\n");

        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // If left child exists, add a connection to it
            if (left < size) {
                sb.append("    ").append(items[i]).append(" -> ").append(items[left]).append(";\n");
            }

            // If right child exists, add a connection to it
            if (right < size) {
                sb.append("    ").append(items[i]).append(" -> ").append(items[right]).append(";\n");
            }
        }

        sb.append("}\n");
        return sb.toString();
    }

    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }


}




