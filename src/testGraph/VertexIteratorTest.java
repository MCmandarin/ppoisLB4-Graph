package testGraph;

import lb.VertexIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class VertexIteratorTest {
    private VertexIterator<Integer> iterator;

    @BeforeEach
    public void setUp() {
        List<Integer> vertices = Arrays.asList(1, 2, 3, 4);
        iterator = new VertexIterator<>(vertices);
    }

    @Test
    public void testHasNext() {
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals(Integer.valueOf(1), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertEquals(Integer.valueOf(4), iterator.next());
    }

    @Test
    public void testNextException() {
        iterator.next();
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
        iterator.next();
        assertTrue(iterator.hasPrevious());
    }

    @Test
    public void testPrevious() {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        assertEquals(Integer.valueOf(4), iterator.previous());
        assertEquals(Integer.valueOf(3), iterator.previous());
        assertEquals(Integer.valueOf(2), iterator.previous());
        assertEquals(Integer.valueOf(1), iterator.previous());
    }
}