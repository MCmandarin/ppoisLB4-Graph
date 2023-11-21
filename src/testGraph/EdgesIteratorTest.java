package testGraph;

import lb.Edge;
import lb.EdgesIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class EdgesIteratorTest {
    private EdgesIterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        List<Edge<Integer>> edges = new ArrayList<>();
        edges.add(new Edge<>(1, 2));
        edges.add(new Edge<>(2, 3));
        edges.add(new Edge<>(3, 4));
        iterator = new EdgesIterator<>(edges);
    }

    @Test
    public void testHasNext() {
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }


    @Test
    public void testNextException() {
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void testHasPrevious() {
        assertFalse(iterator.hasPrevious());
        iterator.next();
        assertTrue(iterator.hasPrevious());
        iterator.next();
        assertTrue(iterator.hasPrevious());
        iterator.next();
        assertTrue(iterator.hasPrevious());
    }
}