import sort.Sort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestSort {

    private Sort sort;
    private Comparator<Integer> comparator;

    @BeforeEach
    public void setUp() {
        sort = new Sort();
        comparator = Integer::compareTo;
    }

    @Test
    public void testMergeSort() {
        List<Integer> numbers = new ArrayList<>(List.of(4, 3, 2, 1));
        sort.mergeSort(numbers, comparator);
        assertTrue(isSorted(numbers, comparator), "The list should be sorted after merge sort.");
    }

    @Test
    public void testSelectionSort() {
        List<Integer> numbers = new ArrayList<>(List.of(4, 3, 2, 1));
        sort.selectionSort(numbers, comparator);
        assertTrue(isSorted(numbers, comparator), "The list should be sorted after selection sort.");
    }

    @Test
    public void mergeWhenLeftListIsExhausted() {
        Sort sort = new Sort();
        Comparator<Integer> comparator = Integer::compareTo;

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 7, 8, 9));
        List<Integer> left = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> right = new ArrayList<>(List.of(7, 8, 9));

        sort.merge(list, left, right, comparator);

        // The merged list should be sorted
        assertIterableEquals(List.of(1, 2, 3, 7, 8, 9), list);
    }

    @Test
    public void mergeWithLeftElementLessThanOrEqualToRight() {
        Sort sort = new Sort();
        Comparator<Integer> comparator = Integer::compareTo;

        List<Integer> list = new ArrayList<>(List.of(1, 3, 5, 2, 4, 6));
        List<Integer> left = new ArrayList<>(List.of(1, 3, 5));
        List<Integer> right = new ArrayList<>(List.of(2, 4, 6));

        sort.merge(list, left, right, comparator);

        // The merged list should be sorted
        assertIterableEquals(List.of(1, 2, 3, 4, 5, 6), list);
    }

    // Helper method to check if the list is sorted
    private <T> boolean isSorted(List<T> list, Comparator<T> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

}

