import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque {
    @Test
    public void testSize() {
        ArrayDeque<String> emptyDeque = new ArrayDeque<>();
        assertTrue(emptyDeque.isEmpty());
        assertEquals(0, emptyDeque.size());
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        for (int i = 0; i < 7; ++i) {
            emptyDeque.addFirst(i);
        }
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        for (int i = 0; i < 7; ++i) {
            emptyDeque.addLast(i);
        }
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        for (int i = 0; i < 7; ++i) {
            assertNull(emptyDeque.get(i));
        }

        /* 4<-3<-2<-1<-0<-7<-6<-5 */
        for (int i = 0; i < 8; ++i) {
            emptyDeque.addFirst(i);
        }

        /* Print the deque */
        emptyDeque.printDeque();

        for (int i = 7; i >= 0; --i) {
            assertEquals((Integer) i, emptyDeque.get(7 - i));
        }
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        assertNull(emptyDeque.removeFirst());
        for (int i = 0; i < 8; ++i) {
            emptyDeque.addFirst(i);
        }
        for (int i = 7; i >= 0; --i) {
            assertEquals(i + 1, emptyDeque.size());
            assertEquals((Integer) i, emptyDeque.get(0));
            emptyDeque.removeFirst();
            assertEquals(i, emptyDeque.size());
            if (i > 0) {
                assertEquals((Integer) (i - 1), emptyDeque.get(0));
            } else {
                assertNull(emptyDeque.get(0));
            }
        }

        for (int i = 0; i < 8; ++i) {
            emptyDeque.addFirst(i);
        }
        emptyDeque.removeFirst();
        assertEquals((Integer) 6, emptyDeque.get(0));
        emptyDeque.addFirst(8);
        assertEquals((Integer) 8, emptyDeque.get(0));
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        assertNull(emptyDeque.removeLast());
        for (int i = 0; i < 8; ++i) {
            emptyDeque.addLast(i);
        }
        emptyDeque.printDeque();
        for (int i = 7; i >= 0; --i) {
            assertEquals(i + 1, emptyDeque.size());
            assertEquals((Integer) i, emptyDeque.get(i));
            emptyDeque.removeLast();
            assertEquals(i, emptyDeque.size());
            if (i > 0) {
                assertEquals((Integer) (i - 1), emptyDeque.get(i - 1));
            } else {
                assertNull(emptyDeque.get(i));
            }
        }

        for (int i = 0; i < 8; ++i) {
            emptyDeque.addLast(i);
        }
        emptyDeque.removeLast();
        assertEquals((Integer) 6, emptyDeque.get(6));
        emptyDeque.addLast(8);
        assertEquals((Integer) 8, emptyDeque.get(7));
    }
}
