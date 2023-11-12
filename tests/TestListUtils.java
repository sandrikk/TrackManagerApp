import lists.DoublyNode;
import org.junit.jupiter.api.Test;
import utils.ListUtils;

import static org.junit.jupiter.api.Assertions.*;

public class TestListUtils {

    @Test
    public void testLinearSearchFindsElement() {
        // Setup - create a doubly linked list with some test data
        DoublyNode<String> head = new DoublyNode<>("first");
        DoublyNode<String> second = new DoublyNode<>("second");
        DoublyNode<String> third = new DoublyNode<>("third");
        head.setNext(second);
        second.setNext(third);
        second.setPrev(head);
        third.setPrev(second);

        // Execute - attempt to find the 'second' element
        String result = ListUtils.linearSearch(head, "second"::equalsIgnoreCase);

        // Verify - check that the correct element was found
        assertEquals("second", result);
    }

    @Test
    public void testLinearSearchDoesNotFindElement() {
        // Setup - create a doubly linked list with some test data
        DoublyNode<String> head = new DoublyNode<>("first");
        DoublyNode<String> second = new DoublyNode<>("second");
        DoublyNode<String> third = new DoublyNode<>("third");
        head.setNext(second);
        second.setNext(third);
        second.setPrev(head);
        third.setPrev(second);

        // Execute - attempt to find a non-existent element
        String result = ListUtils.linearSearch(head, "fourth"::equalsIgnoreCase);

        // Verify - check that the result is null, as the element does not exist
        assertNull(result);
    }
}
