package testGraph;

import lb.AdjacentVerticesIterator;
import lb.DirectedGraph;
import lb.Edge;
import lb.IncidentEdgesIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void testAddEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertEquals(1, graph.getEdgeCount());
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

    @Test
    public void testRemoveVertex() {
        graph = new DirectedGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addVertex(4);
        graph.addEdge(3, 4);

        assertEquals(4, graph.getVertexCount());

        Iterator<Integer> vertexIterator = graph.iterator();
        graph.removeVertex(vertexIterator);

        assertEquals(3, graph.getVertexCount());
        assertEquals(2, graph.getEdgeCount());
    }

    @Test
    public void testRemoveEdge() {
        graph = new DirectedGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addVertex(4);
        graph.addEdge(3, 4);

        assertEquals(4, graph.getVertexCount());
        assertEquals(4, graph.getEdgeCount());

        Iterator<Edge<Integer>> edgeIterator = graph.getEdgesIterator();
        graph.removeEdge(edgeIterator);

        assertEquals(4, graph.getVertexCount());
        assertEquals(3, graph.getEdgeCount());
    }


    @Test
    public void testGetDegree() {
        graph = new DirectedGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addVertex(4);
        graph.addEdge(3, 4);

        assertEquals(2, graph.getDegree(1));
        assertEquals(1, graph.getDegree(2));
        assertEquals(1, graph.getDegree(3));
        assertEquals(0, graph.getDegree(4));
        assertEquals(0, graph.getDegree(5));
    }

    @Test
    public void testGetEdges() {
        graph = new DirectedGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addVertex(4);
        graph.addEdge(3, 4);

        assertEquals(4, graph.getEdges().size());
        graph.removeEdge(1, 2);
        assertEquals(3, graph.getEdges().size());
    }
}