import org.junit.Test;
import static org.junit.Assert.*;

public class TestSLList {

    @Test
    public void testGet() {
        SLList<Integer> list = new SLList<>(0);
        Integer expected = 0;
        assertEquals(expected, list.get(0));

        /* Make the list empty */
        list.removeLast();
        assertNull(list.get(0));

        /* Make a large list */
        SLList<Integer> listLarge = new SLList<>(0);
        final int numOfElements = 10000;
        for (int i = 1; i < numOfElements; ++i) {
            listLarge.addLast(i);
        }
        for (int i = 0; i < numOfElements; ++i) {
            assertEquals((Integer)i, listLarge.get(i));
        }
    }

    @Test
    public void testAddFirst() {
        SLList<Integer> list = new SLList<>(0);
        list.addFirst(-1);
        Integer expected = -1;
        assertEquals(expected, list.getFirst());

        /* Make the list empty */
        list.removeLast();
        list.removeLast();
        assertNull(list.getFirst());
        list.addFirst(0);
        Integer expected1 = 0;
        assertEquals(expected1, list.getFirst());

        /* Make a large list */
        SLList<Integer> listLarge = new SLList<>(0);
        assertEquals((Integer)0, listLarge.getFirst());
        final int numOfElements = 10000;
        for (int i = 1; i < numOfElements; ++i) {
            listLarge.addFirst(i);
            assertEquals((Integer)i, listLarge.getFirst());
        }
    }

    @Test
    public void testGetFirst() {
        /* I am getting lazy ... */
    }

    @Test
    public void testAddLast() {
        /* I am getting lazy ... */
    }

    @Test
    public void testGetLast() {
        /* I am getting lazy ... */
    }

    @Test
    public void testRemoveLast() {
        /* I am getting lazy ... */
    }

    @Test
    public void testSize() {
        SLList<String> list = new SLList<>("HI");
        assertEquals(list.size(), 1);
        list.removeLast();
        assertEquals(list.size(), 0);
        SLList<Integer> listLarge = new SLList<>(0);
        /* The following code runs slowly: addLast consumes O(N) */
        for (int i = 1; i < 100000; ++i) {
            assertEquals(listLarge.size(), i);
            if (i % 3 == 0) {
                listLarge.addLast(i);
            } else {
                listLarge.addFirst(i);
            }
            assertEquals(listLarge.size(), i + 1);
        }
    }
}
