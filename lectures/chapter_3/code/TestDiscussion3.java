import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestDiscussion3 {

    @Test
    public void testInsert() {
        Discussion3.SLList list = new Discussion3.SLList();
        list.addFirst(2);
        list.addFirst(6);
        list.addFirst(5);
        list.insert(10, 1);
        assertEquals(list.get(1), 10);

        Discussion3.SLList list1 = new Discussion3.SLList();
        list1.addFirst(2);
        list1.addFirst(6);
        list1.addFirst(5);
        list1.insert(10, 7);
        assertEquals(list1.get(3), 10);

        Discussion3.SLList list2 = new Discussion3.SLList();
        list2.addFirst(2);
        list2.addFirst(3);
        list2.addFirst(4);
        list2.insert(5, 0);
        assertEquals(list2.get(0), 5);
    }

    public void SLListEqual(Discussion3.SLList list0, Discussion3.SLList list1, int len) {
        for (int i = 0; i < len; ++i) {
            assertEquals(list0.get(i), list1.get(i));
        }
    }

    @Test
    public void testReverseIterative() {
        Discussion3.SLList list0 = new Discussion3.SLList();
        list0.addFirst(0);
        Discussion3.SLList expected = new Discussion3.SLList();
        expected.addFirst(0);
        list0.reverseIterative();
        assertEquals(list0.get(0), expected.get(0));

        Discussion3.SLList list1 = new Discussion3.SLList();
        list1.addFirst(5);
        list1.addFirst(10);
        list1.addFirst(6);
        list1.addFirst(2);
        Discussion3.SLList list2 = new Discussion3.SLList();
        list2.addFirst(2);
        list2.addFirst(6);
        list2.addFirst(10);
        list2.addFirst(5);
        list1.reverseIterative();
        for (int i = 0; i < 4; ++i) {
            assertEquals(list1.get(i), list2.get(i));
        }

        Discussion3.SLList list3 = new Discussion3.SLList();
        Discussion3.SLList list4 = new Discussion3.SLList();
        for (int i = 0; i < 100000; ++i) {
            list3.addFirst(i);
            list4.addFirst(99999 - i);
        }
        list3.reverseIterative();
        for (int i = 0; i < 100000; ++i) {
            assertEquals(list3.get(i), list4.get(i));
        }
    }

    @Test
    public void testReverseRecursive() {
        Discussion3.SLList list0 = new Discussion3.SLList();
        list0.addFirst(0);
        Discussion3.SLList expected = new Discussion3.SLList();
        expected.addFirst(0);
        list0.reverseRecursive();
        assertEquals(list0.get(0), expected.get(0));

        Discussion3.SLList list1 = new Discussion3.SLList();
        list1.addFirst(5);
        list1.addFirst(10);
        list1.addFirst(6);
        list1.addFirst(2);
        Discussion3.SLList list2 = new Discussion3.SLList();
        list2.addFirst(2);
        list2.addFirst(6);
        list2.addFirst(10);
        list2.addFirst(5);
        list1.reverseRecursive();
        for (int i = 0; i < 4; ++i) {
            assertEquals(list1.get(i), list2.get(i));
        }

        Discussion3.SLList list3 = new Discussion3.SLList();
        Discussion3.SLList list4 = new Discussion3.SLList();
        for (int i = 0; i < 100000; ++i) {
            list3.addFirst(i);
            list4.addFirst(99999 - i);
        }
        list3.reverseRecursive();
        for (int i = 0; i < 100000; ++i) {
            assertEquals(list3.get(i), list4.get(i));
        }
    }

    @Test
    public void testInsertArray() {
        int[] x = {5, 9, 14, 15};
        int[] expected = {5, 9, 6, 14, 15};
        int[] actual = Discussion3.Array.insert(x, 6, 2);
        assertArrayEquals(expected, actual);

        int[] empty = {};
        int[] expected1 = {6};
        int[] actual1 = Discussion3.Array.insert(empty, 6, 2);
        assertArrayEquals(expected1, actual1);

        int[] y = {5, 9, 14, 15};
        int[] expectedY = {5, 9, 14, 15, 6};
        int[] actualY = Discussion3.Array.insert(y, 6, 20);
        assertArrayEquals(expectedY, actualY);
    }

    @Test
    public void testReverseArray() {
        int[] x = {5, 9, 14, 15};
        int[] xReversed = {15, 14, 9, 5};
        Discussion3.Array.reverse(x);
        assertArrayEquals(x, xReversed);

        int[] y = {1};
        int[] yReversed = {1};
        Discussion3.Array.reverse(y);
        assertArrayEquals(y, yReversed);

        int[] z = {1, 3, 5, 7, 9};
        int[] zReversed = {9, 7, 5, 3, 1};
        Discussion3.Array.reverse(z);
        assertArrayEquals(z, zReversed);
    }

    @Test
    public void testReverseArrayRecursive() {
        int[] x = {5, 9, 14, 15};
        int[] xReversed = {15, 14, 9, 5};
        Discussion3.Array.reverseRecursive(x, 0, 3);
        assertArrayEquals(x, xReversed);

        int[] y = {1};
        int[] yReversed = {1};
        Discussion3.Array.reverseRecursive(y, 0, 0);
        assertArrayEquals(y, yReversed);

        int[] z = {1, 3, 5, 7, 9};
        int[] zReversed = {9, 7, 5, 3, 1};
        Discussion3.Array.reverseRecursive(z, 0, 4);
        assertArrayEquals(z, zReversed);
    }

    @Test
    public void testReplicate() {
        int[] x = {5, 9, 14, 15};
        int[] xReplicated = {5, 5, 9, 9, 14, 14, 15, 15};
        int[] actual = Discussion3.Array.replicate(x);
        assertArrayEquals(xReplicated, actual);

        int[] y = {1};
        int[] yReplicated = {1, 1};
        int[] actualY = Discussion3.Array.replicate(y);
        assertArrayEquals(yReplicated, actualY);

        int[] z= {1, 2, 3};
        int[] zReplicated = {1, 1, 2, 2, 3, 3};
        int[] actualZ = Discussion3.Array.replicate(z);
        assertArrayEquals(zReplicated, actualZ);

        int[] m = {};
        int[] mReplicated = {};
        int[] actualM = Discussion3.Array.replicate(m);
        assertArrayEquals(mReplicated, actualM);
    }
}
