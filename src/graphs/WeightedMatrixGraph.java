package graphs;

import java.util.ArrayList;
import java.util.List;

public class WeightedMatrixGraph<V> extends AbstractGraph<V> {
    private final boolean directed;
    private final boolean[][] connections;
    private final double[][] weights;

    @SafeVarargs
    public WeightedMatrixGraph(boolean directed, V... vertices) {
        super(vertices);
        this.directed = directed;
        this.connections = new boolean[vertices.length][vertices.length];
        this.weights = new double[vertices.length][vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (i != j) {
                    weights[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public boolean isDirected() {
        return directed;
    }

    public void connect(V vertex1, V vertex2, double weight) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        weights[index1][index2] = weight;
        connections[index1][index2] = true;
        if (!isDirected()) {
            weights[index2][index1] = weight;
            connections[index2][index1] = true;
        }
    }

    public double getWeight(V vertex1, V vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        return weights[index1][index2];
    }

    public List<V> getNeighbors(V vertex) {
        List<V> neighbors = new ArrayList<>();
        int index = getIndex(vertex);
        if (index < 0 || index >= vertices.length) {
            return neighbors; // Vertex not found
        }

        for (int i = 0; i < vertices.length; i++) {
            if (connections[index][i]) {
                neighbors.add(vertices[i]);
            }
        }

        return neighbors;
    }

    public String toGraphViz() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph WeightedMatrixGraph {\n");

        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (connections[i][j]) {
                    // Use -> for all edges, indicating a directed graph visualization
                    sb.append("    ")
                            .append(vertices[i])
                            .append(" -> ")
                            .append(vertices[j])
                            .append(" [label=\"")
                            .append(weights[i][j])
                            .append("\"];\n");
                }
            }
        }

        sb.append("}\n");
        return sb.toString();
    }



}
