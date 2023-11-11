import graphs.WeightedMatrixGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestGraph {
    private WeightedMatrixGraph<String> directedGraph;
    private WeightedMatrixGraph<String> undirectedGraph;

    @BeforeEach
    public void setup() {
        directedGraph = new WeightedMatrixGraph<>(true, "A", "B", "C");
        undirectedGraph = new WeightedMatrixGraph<>(false, "A", "B", "C");
    }

    @Test
    public void testIsDirected() {
        Assertions.assertTrue(directedGraph.isDirected());
        Assertions.assertFalse(undirectedGraph.isDirected());
    }

    @Test
    public void testGetNeighbors() {
        undirectedGraph.connect("A", "B", 1.0);
        undirectedGraph.connect("A", "C", 2.0);

        var neighbors = undirectedGraph.getNeighbors("A");

        Assertions.assertEquals(2, neighbors.size());
        Assertions.assertTrue(neighbors.contains("B"));
        Assertions.assertTrue(neighbors.contains("C"));
    }

    @Test
    public void testConnectAndWeight() {
        directedGraph.connect("A", "B", 1.0);
        undirectedGraph.connect("A", "B", 1.0);
        Assertions.assertEquals(1.0, directedGraph.getWeight("A", "B"));
        Assertions.assertEquals(Double.POSITIVE_INFINITY, directedGraph.getWeight("B", "A"));
        Assertions.assertEquals(1.0, undirectedGraph.getWeight("A", "B"));
        Assertions.assertEquals(1.0, undirectedGraph.getWeight("B", "A"));
    }

    @Test
    public void testToGraphViz() {
        // Given
        String[] vertices = {"A", "B", "C"};
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(true, vertices);
        graph.connect("A", "B", 1.0);
        graph.connect("B", "C", 2.0);

        String expected = "digraph WeightedMatrixGraph {\n" +
                "    A -> B [label=\"1.0\"];\n" +
                "    B -> C [label=\"2.0\"];\n" +
                "}\n";

        String actual = graph.toGraphViz();
        Assertions.assertEquals(expected, actual, "The toGraphViz method should return the correct GraphViz string representation");
    }

    @Test
    public void testGetAllVertices() {
        String[] vertices = {"A", "B", "C"};
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(true, vertices);
        List<String> expected = Arrays.asList(vertices);
        List<String> actual = graph.getAllVertices();
        Assertions.assertEquals(expected, actual, "The getAllVertices method should return all the graph vertices");
    }

    @Test
    public void testGetNeighborsVertexNotFound() {
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(false, "A", "B", "C");
        List<String> neighbors = graph.getNeighbors("D"); // "D" is not a vertex in the graph
        Assertions.assertTrue(neighbors.isEmpty(), "The getNeighbors method should return an empty list when the vertex is not found");
    }

    @Test
    public void testGetVertices() {
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(false, "A", "B", "C");
        String[] expectedVertices = {"A", "B", "C"};
        String[] actualVertices = graph.getVertices();
        Assertions.assertArrayEquals(expectedVertices, actualVertices, "The getVertices method should return the array of vertices used to initialize the graph");
    }

    @Test
    public void testGetSize() {
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(false, "A", "B", "C");
        int expectedSize = 3; // Since we added "A", "B", and "C"
        int actualSize = graph.getSize();
        Assertions.assertEquals(expectedSize, actualSize, "The getSize method should return the number of vertices in the graph");
    }

    @Test
    public void testIsEmptyWhenGraphHasNoVertices() {
        WeightedMatrixGraph<String> emptyGraph = new WeightedMatrixGraph<>(false);
        Assertions.assertTrue(emptyGraph.isEmpty(), "The graph should be considered empty when there are no vertices.");
    }

    @Test
    public void testIsEmptyWhenGraphHasVertices() {
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(false, "A", "B", "C");
        Assertions.assertFalse(graph.isEmpty(), "The graph should not be considered empty when there are vertices.");
    }

}
