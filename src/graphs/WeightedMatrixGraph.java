package graphs;

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

}
