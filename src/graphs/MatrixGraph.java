package graphs;

import java.util.*;

public class MatrixGraph<V> extends AbstractGraph<V> {
    private final boolean directed;
    private final boolean[][] connections;

    @SafeVarargs
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

    public void breadthFirst(V startNode) {
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            V next = queue.poll();
            if (!visited.contains(next)) {

                System.out.println(next);
                visited.add(next);

                int index = getIndex(next);
                for (int i = 0; i < vertices.length; i++) {
                    if (connections[index][i] && !visited.contains(vertices[i])) {
                        queue.offer(vertices[i]);
                    }
                }
            }
        }
    }

    public void depthFirst(V startNode) {
        Set<V> visited = new HashSet<>();
        Stack<V> stack = new Stack<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            V node = stack.pop();
            if (!visited.contains(node)) {
                System.out.println(node);
                visited.add(node);

                int index = getIndex(node);
                for (int i = vertices.length - 1; i >= 0; i--) {
                    if (connections[index][i] && !visited.contains(vertices[i])) {
                        stack.push(vertices[i]);
                    }
                }
            }
        }
    }


}
