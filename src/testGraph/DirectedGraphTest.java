package testGraph;

import lb.AdjacentVerticesIterator;
import lb.DirectedGraph;
import lb.Edge;
import lb.IncidentEdgesIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    private DirectedGraph<Integer> graph;

    @BeforeEach
    public void setUp() {
        graph = new DirectedGraph<>();
    }

    @Test
    public void testAddVertex() {
        graph.addVertex(1);
        assertEquals(1, graph.getVertexCount());
    }

    @Test
    public void testRemoveVertex() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(2, graph.getVertexCount());

        graph.removeVertex(1);
        assertEquals(1, graph.getVertexCount());
        assertFalse(graph.getEdgesIterator().hasNext());
    }

    @Test
    public void testAddEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(1, graph.getEdgeCount());
    }

    @Test
    public void testRemoveEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(1, graph.getEdgeCount());

        Iterator<Edge<Integer>> edgesIterator = graph.getEdgesIterator();
        graph.removeEdge(edgesIterator);
        assertEquals(0, graph.getEdgeCount());
    }

    @Test
    public void testGetIncidentEdgesIterator() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        IncidentEdgesIterator<Integer> iterator = graph.getIncidentEdgesIterator(1);
        assertFalse(iterator.hasNextIncoming());
        assertFalse(iterator.hasNextIncoming());
    }

    @Test
    public void testGetAdjacentVerticesIterator() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        AdjacentVerticesIterator<Integer> iterator = graph.getAdjacentVerticesIterator(1);
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertFalse(iterator.hasNext());
    }
}