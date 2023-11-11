package utils;

import lists.DoublyNode;

import java.util.function.Predicate;

public class ListUtils {
    public static <T> T linearSearch(DoublyNode<T> head, Predicate<T> condition) {
        DoublyNode<T> current = head;
        while (current != null) {
            if (condition.test(current.getData())) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
}
