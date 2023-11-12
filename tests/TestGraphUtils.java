
import graphs.WeightedMatrixGraph;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GraphUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static utils.GraphUtils.findMinimumSpanningTreePrim;

class TestGraphUtils {
    private WeightedMatrixGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new WeightedMatrixGraph<>(false, "A", "B", "C", "D", "E", "F", "G");

        // Add the edges with the respective weights
        graph.connect("A", "B", 1);
        graph.connect("A", "C", 4);
        graph.connect("B", "C", 2);
        graph.connect("B", "D", 3);
        graph.connect("B", "E", 10);
        graph.connect("C", "D", 6);
        graph.connect("C", "G", 3);
        graph.connect("D", "E", 5);
        graph.connect("D", "G", 1);
        graph.connect("E", "G", 2);
        graph.connect("E", "F", 7);
        graph.connect("F", "G", 5);
    }

    @Test
    void testFindShortestPathDijkstraFromAtoF() {
        List<String> path = GraphUtils.findShortestPathDijkstra(graph, "A", "F");
        assertEquals(List.of("A", "B", "D", "G", "F"), path, "The path should be ABDGF as per the presentation example.");
    }

    @Test
    void testFindShortestPathDijkstraNoPath() {
        WeightedMatrixGraph<String> graphNoPath = new WeightedMatrixGraph<>(false, "A", "B", "C", "X", "Y", "Z");

        // Add edges such that "A", "B", "C" are connected, and "X", "Y", "Z" are connected,
        // but there is no connection between these two groups
        graphNoPath.connect("A", "B", 1);
        graphNoPath.connect("B", "C", 2);
        graphNoPath.connect("X", "Y", 3);
        graphNoPath.connect("Y", "Z", 4);

        // Attempt to find a path from a node in one group to a node in the other
        List<String> path = GraphUtils.findShortestPathDijkstra(graphNoPath, "A", "X");

        // Verify that the result is an empty list
        assertTrue(path.isEmpty(), "The path should be empty as there is no connection between A and X.");
    }

    @Test
    void testFindShortestPathAStar() {
        // Create a graph
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(false, "A", "B", "C", "D");

        // Add edges
        graph.connect("A", "B", 1);
        graph.connect("B", "C", 2);
        graph.connect("C", "D", 3);

        // Create stations with coordinates
        Map<String, Station> stationMap = new HashMap<>();
        stationMap.put("A", new Station(1, "A", 1001, "Station A", "sta-a", "Country1", "Type1", 0, 0));
        stationMap.put("B", new Station(2, "B", 1002, "Station B", "sta-b", "Country1", "Type1", 1, 1));
        stationMap.put("C", new Station(3, "C", 1003, "Station C", "sta-c", "Country1", "Type1", 2, 2));
        stationMap.put("D", new Station(4, "D", 1004, "Station D", "sta-d", "Country1", "Type1", 3, 3));

        // Test A* algorithm
        List<String> path = GraphUtils.findShortestPathAStar(graph, "A", "D", stationMap);

        // Assert the path is as expected
        assertEquals(Arrays.asList("A", "B", "C", "D"), path, "Path should be from A to D through B and C");
    }

}
