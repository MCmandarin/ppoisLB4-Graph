package lb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class VertexIterator<T> implements Iterator<T>, BidirectionalIterator<T> {
    private List<T> vertices;
    private int currentIndex;

    public VertexIterator(List<T> vertices) {
        this.vertices = new ArrayList<>(vertices);
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < vertices.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return vertices.get(currentIndex++);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    @Override
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        return vertices.get(--currentIndex);
    }
}