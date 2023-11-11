package utils;

import graphs.WeightedMatrixGraph;
import model.Station;

import java.util.*;

public class GraphUtils {
    public static List<String> findShortestPathAStar(WeightedMatrixGraph<String> graph, String startStationCode, String goalStationCode, Map<String, Station> stationMap) {
        Set<String> closedSet = new HashSet<>();

        PriorityQueue<AStarNode> openSet = new PriorityQueue<>(Comparator.comparingDouble(AStarNode::fscore));
        openSet.add(new AStarNode(startStationCode, heuristic(startStationCode, goalStationCode, stationMap), 0));

        Map<String, String> cameFrom = new HashMap<>();

        Map<String, Double> gScore = new HashMap<>(); // Default value is Infinity
        gScore.put(startStationCode, 0.0);

        Map<String, Double> fScore = new HashMap<>(); // Default value is Infinity
        fScore.put(startStationCode, heuristic(startStationCode, goalStationCode, stationMap));

        while (!openSet.isEmpty()) {
            AStarNode current = openSet.poll();

            if (current.code().equals(goalStationCode)) {
                return reconstructPathAStar(cameFrom, goalStationCode);
            }

            closedSet.add(current.code());

            for (String neighbor : graph.getNeighbors(current.code())) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeGScore = gScore.getOrDefault(current.code(), Double.MAX_VALUE) + graph.getWeight(current.code(), neighbor);

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    // This path to neighbor is better than any previous one. Record it!
                    cameFrom.put(neighbor, current.code());
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goalStationCode, stationMap));

                    AStarNode neighborNode = new AStarNode(neighbor, fScore.get(neighbor), tentativeGScore);

                    // Remove the node if it's already in the open set with a worse score
                    openSet.remove(neighborNode);
                    // Add the neighbor with the updated score to the open set
                    openSet.add(neighborNode);
                }
            }
        }

        return Collections.emptyList();
    }

    public static List<String> findShortestPathDijkstra(WeightedMatrixGraph<String> graph, String startStationCode, String goalStationCode) {
        Map<String, Double> distances = new HashMap<>();

        Map<String, String> previous = new HashMap<>();

        PriorityQueue<DijkstraNode> queue = new PriorityQueue<>(Comparator.comparingDouble(DijkstraNode::distance));

        for (String vertex : graph.getAllVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
            previous.put(vertex, null);
        }

        // Set the distance for the start station to 0 and add it to the queue
        distances.put(startStationCode, 0.0);
        queue.add(new DijkstraNode(startStationCode, 0.0));

        while (!queue.isEmpty()) {
            DijkstraNode current = queue.poll();
            String currentStationCode = current.code();

            // If the goal station is reached, use the previous map to backtrack the path
            if (currentStationCode.equals(goalStationCode)) {
                return reconstructPathDijkstra(previous, goalStationCode);
            }

            // Explore the neighbors of the current station
            for (String neighbor : graph.getNeighbors(currentStationCode)) {
                // Calculate what the new distance would be if we went to neighbor via current
                double alternateDist = distances.get(currentStationCode) + graph.getWeight(currentStationCode, neighbor);

                // If the new distance is shorter, update distances and previous, and add the neighbor to the queue
                if (alternateDist < distances.get(neighbor)) {
                    distances.put(neighbor, alternateDist);
                    previous.put(neighbor, currentStationCode);
                    queue.add(new DijkstraNode(neighbor, alternateDist));
                }
            }
        }

        // If the goal station is not reachable from the start station, return an empty list
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

    private static List<String> reconstructPathAStar(Map<String, String> cameFrom, String current) {
        LinkedList<String> totalPath = new LinkedList<>();
        totalPath.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.addFirst(current); // Add to the front of the path
        }
        return totalPath;
    }

    private static List<String> reconstructPathDijkstra(Map<String, String> previous, String current) {
        List<String> path = new ArrayList<>();
        while (current != null) { // Traverse the path backwards from goal to start
            path.add(0, current); // Add each station to the front of the list
            current = previous.get(current); // Move to the previous station on the path
        }
        return path; // Return the reconstructed path
    }


    private record AStarNode(String code, double fscore, double gScore) {
    // Constructor

    // Override equals and hashCode based on Node code
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AStarNode node = (AStarNode) o;
            return Objects.equals(code, node.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }

    private record DijkstraNode(String code, double distance) {

        @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DijkstraNode that)) return false;
                return code.equals(that.code);
            }

            @Override
            public int hashCode() {
                return code.hashCode();
            }
        }

}
