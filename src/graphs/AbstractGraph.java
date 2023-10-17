package graphs;


public class AbstractGraph<V> {
    protected V[] vertices;

    @SafeVarargs
    public AbstractGraph(V... vertices) {
        this.vertices = vertices;
    }


    protected V[] getVertices() {
        return vertices;
    }

    protected int getSize() {
        return vertices.length;
    }

    protected int getIndex(V vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isEmpty() {
        return vertices.length == 0;
    }

}

