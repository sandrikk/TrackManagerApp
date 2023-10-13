package graphs;

import lists.DoublyLinkedList;
import java.util.function.Predicate;

public class AbstractGraph<V> {
    protected DoublyLinkedList<V> vertices;

    public AbstractGraph(V... vertices) {
        this.vertices = new DoublyLinkedList<>();
        for (V vertex : vertices) {
            addVertex(vertex);
        }
    }

    public void addVertex(V vertex) {
        vertices.add(vertex);
    }

    public void removeVertex(V vertex) {
        vertices.remove(vertex);
        // You may want to remove associated edges when removing a vertex.
    }

    public boolean containsVertex(V vertex) {
        return vertices.contains(vertex);
    }

    public DoublyLinkedList<V> getVertices() {
        return vertices;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    // You can add other methods for working with vertices and edges as needed.

    public V search(Predicate<V> condition) {
        return vertices.search(condition);
    }
}

