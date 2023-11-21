package lb;

import java.util.List;
import java.util.NoSuchElementException;

public class IncidentEdgesIterator<T>{
    private List<Edge<T>> incidentEdges;
    private int currentIndex;

    public IncidentEdgesIterator(List<Edge<T>> incidentEdges) {
        this.incidentEdges = incidentEdges;
        this.currentIndex = 0;
    }

    public boolean hasNextOutgoing() {
        return currentIndex < incidentEdges.size();
    }

    public Edge<T> nextOutgoing() {
        if (!hasNextOutgoing()) {
            throw new NoSuchElementException();
        }
        return incidentEdges.get(currentIndex++);
    }

    public boolean hasNextIncoming() {
        return currentIndex > 0;
    }

    public Edge<T> nextIncoming() {
        if (!hasNextIncoming()) {
            throw new NoSuchElementException();
        }
        return incidentEdges.get(--currentIndex);
    }
}
