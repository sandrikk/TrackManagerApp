import graphs.MatrixGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestMatrixGraph {
    private MatrixGraph<String> directedGraph;
    private MatrixGraph<String> undirectedGraph;
    private MatrixGraph<String> graph;

    @BeforeEach
    public void setup() {
        directedGraph = new MatrixGraph<>(true, "A", "B", "C");
        undirectedGraph = new MatrixGraph<>(false, "A", "B", "C");

        graph = new MatrixGraph<>(false, "A", "B", "C", "D");
        graph.connect("A", "B");
        graph.connect("A", "C");
        graph.connect("B", "D");
        graph.connect("C", "D");
    }

    @Test
    public void testIsDirected() {
        Assertions.assertTrue(directedGraph.isDirected());
        Assertions.assertFalse(undirectedGraph.isDirected());
    }

    @Test
    public void testConnect() {
        directedGraph.connect("A", "B");
        // Assuming isConnected is implemented in MatrixGraph
        Assertions.assertTrue(directedGraph.isConnected("A", "B"));

        // For undirected graph, check both ways
        undirectedGraph.connect("A", "B");
        Assertions.assertTrue(undirectedGraph.isConnected("A", "B"));
        Assertions.assertTrue(undirectedGraph.isConnected("B", "A"));
    }

    @Test
    public void testBreadthFirstTraversal() {
        List<String> expectedOrder = List.of("A", "B", "C", "D");
        List<String> actualOrder = new ArrayList<>();

        // Redirect System.out to capture the output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // Perform the traversal
        graph.breadthFirst("A");

        // Reset the System.out to its original
        System.setOut(originalOut);

        // Convert the output stream to a single string
        String[] nodes = bos.toString().split(System.lineSeparator());
        actualOrder.addAll(Arrays.asList(nodes));

        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    public void testDepthFirstTraversal() {
        // Connect vertices in the order that will result in the expected DFS order
        directedGraph.connect("A", "C");
        directedGraph.connect("C", "D");
        directedGraph.connect("D", "B");

        // Expected order should match the actual DFS order produced by your graph implementation
        List<String> expectedOrder = List.of("A", "C", "D", "B");
        List<String> actualOrder = new ArrayList<>();

        // Redirect System.out to capture the output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // Perform the traversal
        directedGraph.depthFirst("A");

        // Reset the System.out to its original
        System.setOut(originalOut);

        // Convert the output stream to a single string
        String[] nodes = bos.toString().split(System.lineSeparator());
        actualOrder.addAll(Arrays.asList(nodes));

        Assertions.assertEquals(expectedOrder, actualOrder, "Depth-first traversal order should match expected order.");
    }
}
