package utils;

import graphs.WeightedMatrixGraph;
import model.Station;

import java.util.*;

public class GraphUtils {
    public static List<String> findShortestPathAStar(WeightedMatrixGraph<String> graph, String startStationCode, String goalStationCode, Map<String, Station> stationMap) {
        // Set of nodes already evaluated
        Set<String> closedSet = new HashSet<>();

        // The set of currently discovered nodes that are not evaluated yet.
        // Initially, only the start node is known.
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(Node::getFscore));
        openSet.add(new Node(startStationCode, heuristic(startStationCode, goalStationCode, stationMap), 0));

        // For each node, which node it can most efficiently be reached from.
        // If a node can be reached from many nodes, cameFrom will eventually contain the
        // most efficient previous step.
        Map<String, String> cameFrom = new HashMap<>();

        // For each node, the cost of getting from the start node to that node.
        Map<String, Double> gScore = new HashMap<>(); // Default value is Infinity
        gScore.put(startStationCode, 0.0);

        // For each node, the total cost of getting from the start node to the goal
        // by passing by that node. That value is partly known, partly heuristic.
        Map<String, Double> fScore = new HashMap<>(); // Default value is Infinity
        fScore.put(startStationCode, heuristic(startStationCode, goalStationCode, stationMap));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.getCode().equals(goalStationCode)) {
                return reconstructPath(cameFrom, goalStationCode);
            }

            closedSet.add(current.getCode());

            for (String neighbor : graph.getNeighbors(current.getCode())) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGScore = gScore.getOrDefault(current.getCode(), Double.MAX_VALUE) + graph.getWeight(current.getCode(), neighbor);

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    // This path to neighbor is better than any previous one. Record it!
                    cameFrom.put(neighbor, current.getCode());
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goalStationCode, stationMap));

                    Node neighborNode = new Node(neighbor, fScore.get(neighbor), tentativeGScore);

                    // Remove the node if it's already in the open set with a worse score
                    openSet.remove(neighborNode);
                    // Add the neighbor with the updated score to the open set
                    openSet.add(neighborNode);
                }
            }
        }

        return Collections.emptyList();
    }

    private static double heuristic(String stationCode1, String stationCode2, Map<String, Station> stationMap) {
        Station station1 = stationMap.get(stationCode1);
        Station station2 = stationMap.get(stationCode2);

        double lat1 = Math.toRadians(station1.getGeoLat());
        double lon1 = Math.toRadians(station1.getGeoLng());
        double lat2 = Math.toRadians(station2.getGeoLat());
        double lon2 = Math.toRadians(station2.getGeoLng());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);
        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371; // Radius of the earth in kilometers. Use 3956 for miles
        return c * r;
    }

    private static List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        LinkedList<String> totalPath = new LinkedList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.addFirst(current); // Add to the front of the path
        }
        return totalPath;
    }

    // Helper class to represent a node in the graph
    private static class Node {
        private final String code;
        private double fscore; // The cost of getting from the start node to this node
        private double gScore; // The cost from this node to the goal, estimated by the heuristic

        // Constructor
        public Node(String code, double fscore, double gScore) {
            this.code = code;
            this.fscore = fscore;
            this.gScore = gScore;
        }

        public String getCode() {
            return code;
        }

        public double getFscore() {
            return fscore;
        }

        // Override equals and hashCode based on Node code
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(code, node.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }
}
