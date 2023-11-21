package lb;

public class Edge<T> {
    private T source;
    private T destination;

    public Edge(T source, T destination) {
        this.source = source;
        this.destination = destination;
    }

    public T getSource() {
        return source;
    }

    public T getDestination() {
        return destination;
    }
}
