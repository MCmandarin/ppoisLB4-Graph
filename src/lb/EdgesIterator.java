package lb;

import java.util.List;
import java.util.NoSuchElementException;

public class EdgesIterator<T> implements BidirectionalIterator<Edge<T>> {
    private List<Edge<T>> edges;
    private int currentIndex;

    public EdgesIterator(List<Edge<T>> edges) {
        this.edges = edges;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < edges.size();
    }

    @Override
    public Edge<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return edges.get(currentIndex++);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    @Override
    public Edge<T> previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return edges.get(--currentIndex);
    }
}