package utils;

import lists.Node;

import java.util.function.Predicate;

public class ListUtils {
    public static <T> T linearSearch(Node<T> head, Predicate<T> condition) {
        Node<T> current = head;
        while (current != null) {
            if (condition.test(current.getData())) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
}
