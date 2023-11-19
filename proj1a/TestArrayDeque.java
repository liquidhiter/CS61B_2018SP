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

    }
}
