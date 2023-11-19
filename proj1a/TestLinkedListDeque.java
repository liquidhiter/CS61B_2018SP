import static org.junit.Assert.*;
import org.junit.Test;

public class TestLinkedListDeque {
    @Test
    public void testSize() {
        LinkedListDeque<String> emptyDeque = new LinkedListDeque<>();
        assertEquals(emptyDeque.size(), 0);
        assertTrue(emptyDeque.isEmpty());

        emptyDeque.addFirst("HI");
        assertEquals(1, emptyDeque.size());
        assertFalse(emptyDeque.isEmpty());
    }

    @Test(timeout = 1000)
    public void testAddFirst() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.getFirst());

        emptyDeque.addFirst(0);
        assertEquals(1, emptyDeque.size());
        assertEquals((Integer) 0, emptyDeque.getFirst());

        for (int i = 1; i < 10000; ++i) {
            emptyDeque.addFirst(i);
            assertEquals((Integer) i, emptyDeque.getFirst());
        }
    }

    @Test(timeout = 1000)
    public void testAddLast() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.getFirst());

        emptyDeque.addLast(0);
        assertEquals(1, emptyDeque.size());
        assertEquals((Integer) 0, emptyDeque.getLast());

        for (int i = 1; i < 10000; ++i) {
            emptyDeque.addLast(i);
            assertEquals((Integer) i, emptyDeque.getLast());
        }
    }

    @Test(timeout = 1000)
    public void testRemoveFirst() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.removeFirst());

        emptyDeque.addFirst(0);
        assertEquals((Integer) 0, emptyDeque.removeFirst());
        emptyDeque.addLast(0);
        assertEquals((Integer) 0, emptyDeque.removeFirst());

        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addFirst(i);
        }
        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addLast(i);
        }
        for (int i = 9999; i >= 0; --i) {
            assertEquals((Integer) i, emptyDeque.removeFirst());
        }
        for (int i = 0; i < 10000; ++i) {
            assertEquals((Integer) i, emptyDeque.removeFirst());
        }
    }

    @Test(timeout = 1000)
    public void testRemoveLast() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.removeLast());

        emptyDeque.addFirst(0);
        assertEquals((Integer) 0, emptyDeque.removeLast());
        emptyDeque.addLast(0);
        assertEquals((Integer) 0, emptyDeque.removeLast());

        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addLast(i);
        }
        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addFirst(i);
        };
        for (int i = 9999; i >= 0; --i) {
            assertEquals((Integer) i, emptyDeque.removeLast());
        }
        for (int i = 0; i < 10000; ++i) {
            assertEquals((Integer) i, emptyDeque.removeLast());
        }
    }

    @Test
    public void testMemoryUsage() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addLast(i);
        }
        assertEquals(10000, emptyDeque.size());
        for (int i = 9999; i >= 5000; --i) {
            assertEquals((Integer) i, emptyDeque.removeLast());
            assertEquals(i, emptyDeque.size());
        }
        assertEquals(5000, emptyDeque.size());
        for (int i = 0; i < 5000; ++i) {
            assertEquals((Integer) i, emptyDeque.removeFirst());
            assertEquals(4999 - i, emptyDeque.size());
        }
        assertEquals(0, emptyDeque.size());
    }

    @Test
    public void testGet() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.get(0));
        assertNull(emptyDeque.get(1));

        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addLast(i);
        }
        for (int i = 0; i < 10000; ++i) {
            assertEquals((Integer) i, emptyDeque.get(i));
        }
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.getRecursive(0));
        assertNull(emptyDeque.getRecursive(1));

        for (int i = 0; i < 10000; ++i) {
            emptyDeque.addLast(i);
        }
        for (int i = 0; i < 10000; ++i) {
            assertEquals((Integer) i, emptyDeque.getRecursive(i));
        }
    }

}
