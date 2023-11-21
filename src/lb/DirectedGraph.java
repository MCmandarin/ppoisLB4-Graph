package lb;

import java.util.*;

class DirectedGraph<T> implements Iterable<T> {
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

    public static void main(String[] args) {
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        graph.printGraph();

        System.out.println("Vertex Count: " + graph.getVertexCount());
        System.out.println("Edge Count: " + graph.getEdgeCount());

        System.out.println("Degree of vertex 1: " + graph.getDegree(1));
        System.out.println("Degree of vertex 2: " + graph.getDegree(2));
        System.out.println("Degree of vertex 3: " + graph.getDegree(3));
        System.out.println("Degree of vertex 4: " + graph.getDegree(4));

//        graph.removeVertex(3);
//        graph.removeEdge(1, 2);
//        graph.printGraph();

        VertexIterator<Integer> reverseIterator = new VertexIterator<>(new ArrayList<>(graph.adjacencyList.keySet()));

        System.out.println("Graph in forward direction:");
        while (reverseIterator.hasNext()) {
            System.out.print(reverseIterator.next() + " ");
        }
        System.out.println();

        System.out.println("Graph in reverse direction:");
        while (reverseIterator.hasPrevious()) {
            System.out.print(reverseIterator.previous() + " ");
        }
        System.out.println();


        Iterator<Edge<Integer>> edgesIterator = graph.getEdgesIterator();

        System.out.println("Forward iteration:");
        while (edgesIterator.hasNext()) {
            Edge<Integer> edge = edgesIterator.next();
            System.out.println(edge.getSource() + " -> " + edge.getDestination());
        }

        EdgesIterator<Integer> bidirectionalIterator = new EdgesIterator<>(graph.getEdges());
        System.out.println("\nBackward iteration:");
        while (bidirectionalIterator.hasPrevious()) {
            Edge<Integer> edge = bidirectionalIterator.previous();
            System.out.println(edge.getDestination() + " <- " + edge.getSource());
        }


        IncidentEdgesIterator<Integer> incidentEdgesIterator = graph.getIncidentEdgesIterator(1);

        System.out.println("Outgoing edges from vertex 1:");
        while (incidentEdgesIterator.hasNextOutgoing()) {
            Edge<Integer> edge = incidentEdgesIterator.nextOutgoing();
            System.out.println(edge.getSource() + " -> " + edge.getDestination());
        }

        System.out.println("\nIncoming edges to vertex 1:");
        while (incidentEdgesIterator.hasNextIncoming()) {
            Edge<Integer> edge = incidentEdgesIterator.nextIncoming();
            System.out.println(edge.getDestination() + " <- " + edge.getSource());
        }


        AdjacentVerticesIterator<Integer> adjacentVerticesIterator = graph.getAdjacentVerticesIterator(1);

        System.out.println("Adjacent vertices to vertex 1:");
        while (adjacentVerticesIterator.hasNext()) {
            Integer adjacentVertex = adjacentVerticesIterator.next();
            System.out.println("Vertex 1 -> Vertex " + adjacentVertex);
        }

        System.out.println("\nPrevious vertices to vertex 1:");
        while (adjacentVerticesIterator.hasPrevious()) {
            Integer previousVertex = adjacentVerticesIterator.previous();
            System.out.println("Vertex " + previousVertex + " -> Vertex 1");
        }
    }
}