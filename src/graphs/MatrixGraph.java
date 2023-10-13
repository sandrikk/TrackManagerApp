package graphs;

public class MatrixGraph<V> extends AbstractGraph<V> {
    private final boolean directed;
    private final boolean[][] connections;

    public MatrixGraph(boolean directed, V... vertices) {
        super(vertices);
        this.directed = directed;
        this.connections = new boolean[vertices.length][vertices.length];
    }


    public boolean isDirected() {
        return directed;
    }


    public void connect(V vertex1, V vertex2) {
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        connections[index1][index2] = true;
        if (!isDirected()) {
            connections[index2][index1] = true;
        }
    }

    private int getIndex(V vertex1) {
        return 0;
    }


}
