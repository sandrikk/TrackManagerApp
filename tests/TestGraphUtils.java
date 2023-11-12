
import graphs.WeightedMatrixGraph;
import model.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GraphUtils;

import java.util.*;

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

    @Test
    void testFindMinimumSpanningTreePrim() {
        // Create a graph
        WeightedMatrixGraph<String> testGraph = new WeightedMatrixGraph<>(false, "A", "B", "C", "D", "E", "F", "G", "H", "I");

        // Add edges with weights
        testGraph.connect("A", "B", 4);
        testGraph.connect("A", "H", 8);

        testGraph.connect("B", "H", 11);
        testGraph.connect("B", "C", 8);

        testGraph.connect("C", "D", 7);
        testGraph.connect("C", "F", 4);
        testGraph.connect("C", "I", 2);

        testGraph.connect("D", "E", 9);
        testGraph.connect("D", "F", 14);

        testGraph.connect("E", "F", 10);

        testGraph.connect("F", "G", 2);

        testGraph.connect("G", "H", 1);
        testGraph.connect("G", "I", 6);

        testGraph.connect("I", "H", 7);

        // Find the MST using Prim's algorithm
        List<GraphUtils.Edge<String>> mstEdges = GraphUtils.findMinimumSpanningTreePrim(testGraph);

        // Calculate the total weight of the actual MST
        double actualTotalWeight = mstEdges.stream().mapToDouble(GraphUtils.Edge::weight).sum();

        // Define the expected total weight of the MST
        double expectedTotalWeight = 4 + 8 + 2 + 4 + 2 + 1 + 7 + 9; // Sum of the weights of the expected edges

        // Check if the total weight of the MST is as expected
        assertEquals(expectedTotalWeight, actualTotalWeight, "Total weight of the MST should be correct");
    }

    @Test
    void testToString() {
        // Create an Edge object
        GraphUtils.Edge<String> edge = new GraphUtils.Edge<>("Vertex1", "Vertex2", 5.0);

        // Expected string representation
        String expected = "Edge from Vertex1 to Vertex2 with weight 5.0";

        // Assert that the toString method returns the expected string
        assertEquals(expected, edge.toString(), "Edge toString should return the correct string representation.");
    }

    @Test
    void testFindShortestPathAStarNoPath() {
        // Create a graph with isolated station pairs
        WeightedMatrixGraph<String> graph = new WeightedMatrixGraph<>(true, "A", "B", "X", "Y");

        // Stations with coordinates
        Map<String, Station> stationMap = new HashMap<>();
        stationMap.put("A", new Station(1, "A", 1001, "Station A", "sta-a", "Country1", "Type1", 0, 0));
        stationMap.put("B", new Station(2, "B", 1002, "Station B", "sta-b", "Country1", "Type1", 1, 1));
        stationMap.put("X", new Station(3, "X", 1003, "Station X", "sta-x", "Country1", "Type1", 2, 2));
        stationMap.put("Y", new Station(4, "Y", 1004, "Station Y", "sta-y", "Country1", "Type1", 3, 3));

        // Connect only A and B, and X and Y
        graph.connect("A", "B", 1);
        graph.connect("X", "Y", 1);

        // Attempt to find a path from "A" to "X" (no path should exist)
        List<String> path = GraphUtils.findShortestPathAStar(graph, "A", "X", stationMap);

        // Assert that the returned path is empty
        assertEquals(Collections.emptyList(), path, "There should be no path from A to X");
    }


}
