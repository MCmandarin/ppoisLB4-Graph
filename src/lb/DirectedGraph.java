package lb;

import java.util.*;

public class DirectedGraph<T> implements Iterable<T> {
    private Map<T, List<T>> adjacencyList;

    public DirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }


    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(T vertex) {
        for (List<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }

        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
    }

    public void removeEdge(Iterator<Edge<T>> edgeIterator) {
        if (edgeIterator.hasNext()) {
            Edge<T> edgeToRemove = edgeIterator.next();
            T source = edgeToRemove.getSource();
            T destination = edgeToRemove.getDestination();

            if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) {
                adjacencyList.get(source).remove(destination);
            }
        }
    }

    public void removeVertex(Iterator<T> vertexIterator) {
        if (vertexIterator.hasNext()) {
            T vertexToRemove = vertexIterator.next();

            for (List<T> neighbors : adjacencyList.values()) {
                neighbors.remove(vertexToRemove);
            }

            adjacencyList.remove(vertexToRemove);
        }
    }

    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source) && adjacencyList.containsKey(destination)) {
            adjacencyList.get(source).remove(destination);
        }
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }


    public int getEdgeCount() {
        int edgeCount = 0;
        for (List<T> neighbors : adjacencyList.values()) {
            edgeCount += neighbors.size();
        }
        return edgeCount;
    }

    public void printGraph() {
        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (T neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public int getDegree(T vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return adjacencyList.get(vertex).size();
        } else {
            return 0;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new VertexIterator<>(new ArrayList<>(adjacencyList.keySet()));
    }

    public Iterator<Edge<T>> getEdgesIterator() {
        List<Edge<T>> edges = new ArrayList<>();

        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            T source = entry.getKey();
            for (T destination : entry.getValue()) {
                edges.add(new Edge<>(source, destination));
            }
        }

        return edges.iterator();
    }

    public List<Edge<T>> getEdges() {
        List<Edge<T>> edges = new ArrayList<>();

        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            T source = entry.getKey();
            for (T destination : entry.getValue()) {
                edges.add(new Edge<>(source, destination));
            }
        }

        return edges;
    }

    public IncidentEdgesIterator<T> getIncidentEdgesIterator(T vertex) {
        List<Edge<T>> incidentEdges = new ArrayList<>();

        if (adjacencyList.containsKey(vertex)) {
            for (T destination : adjacencyList.get(vertex)) {
                incidentEdges.add(new Edge<>(vertex, destination));
            }
        }

        return new IncidentEdgesIterator<>(incidentEdges);
    }

    public AdjacentVerticesIterator<T> getAdjacentVerticesIterator(T vertex) {
        List<T> adjacentVertices = adjacencyList.getOrDefault(vertex, new ArrayList<>());
        return new AdjacentVerticesIterator<>(adjacentVertices);
    }
}