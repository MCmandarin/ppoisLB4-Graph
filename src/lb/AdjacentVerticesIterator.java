package lb;

import java.util.List;
import java.util.NoSuchElementException;

public class AdjacentVerticesIterator<T>{
    private List<T> adjacentVertices;
    private int currentIndex;

    public AdjacentVerticesIterator(List<T> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
        this.currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < adjacentVertices.size();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return adjacentVertices.get(currentIndex++);
    }

    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return adjacentVertices.get(--currentIndex);
    }
}
