package graphs;


public class AbstractGraph<V> {
    protected V[] vertices;

    @SafeVarargs
    public AbstractGraph(V... vertices) {
        this.vertices = vertices;
    }


    public V[] getVertices() {
        return vertices;
    }

    public int getSize() {
        return vertices.length;
    }

    public int getIndex(V vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return vertices.length == 0;
    }

}

