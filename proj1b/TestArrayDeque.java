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

    @Test
    public void testExpand() {
        ArrayDeque<Integer> emptyDeque = new ArrayDeque<>();
        for (int i = 0; i < 8; ++i) {
            emptyDeque.addFirst(i);
        }
        emptyDeque.addFirst(9);
        assertEquals((Integer) 9, emptyDeque.get(0));
        emptyDeque.addFirst(10);
        assertEquals((Integer) 10, emptyDeque.get(0));

        emptyDeque.addLast(11);
        assertEquals((Integer) 11, emptyDeque.get(emptyDeque.size() - 1));
        emptyDeque.addLast(12);
        assertEquals((Integer) 12, emptyDeque.get(emptyDeque.size() - 1));

        emptyDeque.removeLast();
        emptyDeque.removeFirst();
        emptyDeque.removeFirst();
        emptyDeque.removeLast();
        emptyDeque.removeFirst();
        emptyDeque.removeLast();
        emptyDeque.removeFirst();
        emptyDeque.removeFirst();
        emptyDeque.removeLast();
        emptyDeque.removeFirst();

        assertEquals(2, emptyDeque.size());

        for (int i = 0; i < 10000; ++i) {
            if (i % 4 == 2) {
                emptyDeque.addLast(i);
            } else {
                emptyDeque.addFirst(i);
            }
        }

        assertEquals(10000 + 2, emptyDeque.size());

        for (int i = 0; i < 10000; ++i) {
            if (i % 3 == 1) {
                emptyDeque.removeLast();
            } else {
                emptyDeque.removeLast();
            }
        }

        assertEquals(2, emptyDeque.size());
    }

//    @Test
//    public void testDeepCopyConstructor() {
//        ArrayDeque<Integer> other = new ArrayDeque<>();
//        for (int i = 0; i < 10000; ++i) {
//            if (i % 3 == 2) {
//                other.addLast(i);
//            } else {
//                other.addFirst(i);
//            }
//        }
//
//        ArrayDeque<Integer> copy = new ArrayDeque<>(other);
//        for (int i = 0; i < 10000; ++i) {
//            assertEquals(other.get(i), copy.get(i));
//            assertEquals(other.get(0), copy.get(0));
//            assertEquals(other.get(other.size() - 1), copy.get(copy.size() - 1));
//        }
//    }
}
