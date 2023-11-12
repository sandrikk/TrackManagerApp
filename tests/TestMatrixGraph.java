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
        graph = new MatrixGraph<>(false, "A", "B", "C", "D");
        graph.connect("A", "B");
        graph.connect("A", "C");
        graph.connect("B", "D");
        graph.connect("C", "D");
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
        // Initialize the graph with all necessary vertices
        graph = new MatrixGraph<>(true, "A", "B", "C", "D");

        // Connect vertices to reflect the expected DFS traversal order
        graph.connect("A", "B");
        graph.connect("B", "C");
        graph.connect("C", "D");

        // This is the expected DFS order for the graph defined above
        List<String> expectedOrder = List.of("A", "B", "C", "D");
        List<String> actualOrder = new ArrayList<>();

        // Redirect System.out to capture the output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // Perform the traversal
        graph.depthFirst("A");

        // Reset the System.out to its original
        System.setOut(originalOut);

        // Convert the output stream to a single string
        String[] nodes = bos.toString().split(System.lineSeparator());
        actualOrder.addAll(Arrays.asList(nodes));

        // Assert that the actual order matches the expected order
        Assertions.assertEquals(expectedOrder, actualOrder, "Depth-first traversal order should match expected order.");
    }

    @Test
    public void testDepthFirstTraversalComplexGraph() {
        // Initialize a more complex graph structure
        graph = new MatrixGraph<>(true, "A", "B", "C", "D", "E", "F", "G");

        // Connect vertices to create a graph that branches and merges
        graph.connect("A", "B");
        graph.connect("A", "C");
        graph.connect("B", "D");
        graph.connect("B", "E");
        graph.connect("C", "F");
        graph.connect("E", "F");
        graph.connect("F", "G");

        // Define the expected order of traversal, depending on your DFS algorithm's specifics
        // This is a typical DFS pre-order traversal result for the above graph
        List<String> expectedOrder = List.of("A", "B", "D", "E", "F", "G", "C");

        List<String> actualOrder = new ArrayList<>();

        // Redirect System.out to capture the output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // Perform the traversal starting from vertex "A"
        graph.depthFirst("A");

        // Reset the System.out to its original
        System.setOut(originalOut);

        // Convert the output stream to a single string
        String[] nodes = bos.toString().split(System.lineSeparator());
        actualOrder.addAll(Arrays.asList(nodes));

        // Assert that the actual order matches the expected order
        Assertions.assertEquals(expectedOrder, actualOrder, "Depth-first traversal order should match expected order for a complex graph.");
    }
}
