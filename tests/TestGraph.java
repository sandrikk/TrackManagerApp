import graphs.WeightedMatrixGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        // Test directed graph
        Assertions.assertEquals(1.0, directedGraph.getWeight("A", "B"));
        Assertions.assertEquals(Double.POSITIVE_INFINITY, directedGraph.getWeight("B", "A")); // Should be infinity for unconnected direction

        // Test undirected graph
        Assertions.assertEquals(1.0, undirectedGraph.getWeight("A", "B"));
        Assertions.assertEquals(1.0, undirectedGraph.getWeight("B", "A")); // Should be same as A to B
    }



}
