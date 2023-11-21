package testGraph;

import lb.Edge;
import lb.IncidentEdgesIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidentEdgesIteratorTest {
    private IncidentEdgesIterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        List<Edge<Integer>> incidentEdges = new ArrayList<>();
        incidentEdges.add(new Edge<>(1, 2));
        incidentEdges.add(new Edge<>(1, 3));
        incidentEdges.add(new Edge<>(1, 4));
        iterator = new IncidentEdgesIterator<>(incidentEdges);
    }

    @Test
    public void testHasNextOutgoing() {
        assertTrue(iterator.hasNextOutgoing());
        iterator.nextOutgoing();
        assertTrue(iterator.hasNextOutgoing());
        iterator.nextOutgoing();
        assertTrue(iterator.hasNextOutgoing());
        iterator.nextOutgoing();
        assertFalse(iterator.hasNextOutgoing());
    }

    @Test
    public void testNextOutgoingException() {
        iterator.nextOutgoing();
        iterator.nextOutgoing();
        iterator.nextOutgoing();
    }

    @Test
    public void testHasNextIncoming() {
        assertFalse(iterator.hasNextIncoming());
        iterator.nextOutgoing();
        assertTrue(iterator.hasNextIncoming());
        iterator.nextOutgoing();
        assertTrue(iterator.hasNextIncoming());
        iterator.nextOutgoing();
        assertTrue(iterator.hasNextIncoming());
    }

}