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
        assertNull(emptyDeque.get(0));

        emptyDeque.addFirst(0);
        assertEquals(1, emptyDeque.size());
        assertEquals((Integer) 0, emptyDeque.get(0));

        for (int i = 1; i < 10000; ++i) {
            emptyDeque.addFirst(i);
            assertEquals((Integer) i, emptyDeque.get(0));
        }
    }

    @Test(timeout = 1000)
    public void testAddLast() {
        LinkedListDeque<Integer> emptyDeque = new LinkedListDeque<>();
        assertNull(emptyDeque.get(0));

        emptyDeque.addLast(0);
        assertEquals(1, emptyDeque.size());
        assertEquals((Integer) 0, emptyDeque.get(emptyDeque.size() - 1));

        for (int i = 1; i < 10000; ++i) {
            emptyDeque.addLast(i);
            assertEquals((Integer) i, emptyDeque.get(emptyDeque.size() - 1));
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

    @Test
    public void testDeepCopyConstructor() {
        LinkedListDeque<Integer> other = new LinkedListDeque<>();
        for (int i = 0; i < 10000; ++i) {
            if (i % 3 == 2) {
                other.addLast(i);
            } else {
                other.addFirst(i);
            }
        }

        LinkedListDeque<Integer> copy = new LinkedListDeque<>(other);
        for (int i = 0; i < 10000; ++i) {
            assertEquals(other.get(i), copy.get(i));
            assertEquals(other.get(0), copy.get(0));
            assertEquals(other.get(other.size() - 1), copy.get(copy.size() - 1));
        }

//        int lastOfOther = other.get(other.size() - 1);
//        other.removeLast();
//        assertNotEquals((Integer) lastOfOther, other.get(other.size() - 1));
//        assertEquals((Integer) lastOfOther, copy.get(copy.size() - 1));

        int first = other.get(0);
        int last = other.get(other.size() - 1);
        for (int i = 0; i < 10000; ++i) {
            if (i % 2 == 0) {
                int firstOfOther = other.get(0);
                other.removeFirst();
                assertEquals(9999 - i, other.size());
                assertNotEquals((Integer) firstOfOther, other.get(0));
                assertEquals((Integer) first, copy.get(0));
                assertEquals(10000, copy.size());
            } else {
                int lastOfOther = other.get(other.size() - 1);
                other.removeLast();
                assertEquals(9999 - i, other.size());
                assertNotEquals((Integer) lastOfOther, other.get(other.size() - 1));
                assertEquals((Integer) last, copy.get(copy.size() - 1));
                assertEquals(10000, copy.size());
            }
        }
    }

}
