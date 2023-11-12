
import graphs.WeightedMatrixGraph;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GraphUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static utils.GraphUtils.findMinimumSpanningTreePrim;

class TestGraphUtils {
    private WeightedMatrixGraph<String> graph;
    private Map<String, Station> stationMap;
    private WeightedMatrixGraph<String> graphFromPresentation;

    @BeforeEach
    void setUp() {
        // Initialize your graph and stationMap here with predefined vertices
        // For example:
        stationMap = new HashMap<>();
        stationMap.put("A", new Station(266,"A", 8400319,"Den Bosch","s-hertogenbosch","NL","knooppuntIntercitystation", 0.0, 0.0));
        stationMap.put("B", new Station(267,"B", 8400319,"Den Bosch","s-hertogenbosch","NL","knooppuntIntercitystation", 1.0, 1.0));
        stationMap.put("C", new Station(268,"C", 8400319,"Den Bosch","s-hertogenbosch","NL","knooppuntIntercitystation", 2.0, 2.0));

        // Create a graph with the vertices "A", "B", "C"
        graph = new WeightedMatrixGraph<>(true, "A", "B", "C");

        // Connect vertices with weights
        graph.connect("A", "B", 1.0);
        graph.connect("B", "C", 1.0);
        graph.connect("A", "C", 2.5);

    }

    @Test
    void testFindShortestPathAStar() {
        List<String> path = GraphUtils.findShortestPathAStar(graph, "A", "C", stationMap);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(List.of("A", "B", "C"), path, "The path should be the shortest from A to C.");
    }

    @Test
    void testFindShortestPathDijkstra() {
        List<String> path = GraphUtils.findShortestPathDijkstra(graph, "A", "C");
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(List.of("A", "B", "C"), path, "The path should be the shortest from A to C.");
    }

    @Test
    void testFindMinimumSpanningTreePrim() {
        List<GraphUtils.Edge<String>> mst = findMinimumSpanningTreePrim(graph);

        // Check if the MST is not null and has the correct number of edges
        // For a graph with 3 vertices, the MST should have 2 edges
        assertNotNull(mst, "The MST should not be null");
        assertEquals(2, mst.size(), "The MST should have 2 edges for a graph with 3 vertices");

        // Calculate the total weight of the MST and check if it's minimal
        // Based on the provided graph connections, the minimum total weight should be 2.0
        double totalWeight = mst.stream().mapToDouble(GraphUtils.Edge::weight).sum();
        assertEquals(2.0, totalWeight, "The total weight of the MST should be minimal");
    }
}
