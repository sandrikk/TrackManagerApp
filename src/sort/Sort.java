package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sort {
    // Generic merge sort for a list of items
    public <T> void mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, mid));
        List<T> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        merge(list, left, right, comparator);
    }

    // Helper method to merge two sorted lists
    public <T> void merge(List<T> list, List<T> left, List<T> right, Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                list.set(index++, left.get(leftIndex++));
            } else {
                list.set(index++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            list.set(index++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            list.set(index++, right.get(rightIndex++));
        }
    }

    public <T> void selectionSort(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            // Assume the minimum is the first element
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                // Find the minimum element in the unsorted part of the list
                if (comparator.compare(list.get(j), list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                T temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }


}
