package synthesizer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {

    @Test
    public void testEnqueueAndDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        List<Integer> fillInElems = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        for (Integer i : fillInElems) {
            arb.enqueue(i);
        }
        assertFalse(arb.isEmpty());
        assertTrue(arb.isFull());

        for (int i = 0; i < 10; ++i) {
            assertEquals(fillInElems.get(i), arb.dequeue());
        }
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        List<Integer> fillInElems = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        for (Integer i : fillInElems) {
            arb.enqueue(i);
        }

        for (Integer i : fillInElems) {
            assertEquals(i, arb.peek());
            arb.dequeue();
        }
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        List<Integer> fillInElems = List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        for (Integer i : fillInElems) {
            arb.enqueue(i);
        }

        Iterator<Integer> itr = arb.iterator();
        int idx = 0;
        while (itr.hasNext()) {
            assertEquals(fillInElems.get(idx++), itr.next());
        }

        /* Dequeue 3 elements from the ArrayRingBuffer */
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();

        /* Re-enqueue 3 elements */
        arb.enqueue(-1);
        arb.enqueue(-2);
        arb.enqueue(-3);

        /* So far: first should be increased to be 3 */
        List<Integer> fillInElems2 = List.of(-1, -2, -3, 7, 6, 5, 4, 3, 2, 1);
        Iterator<Integer> itr2 = arb.iterator();
        int idx2 = 3;
        while (itr2.hasNext()) {
            assertEquals(fillInElems2.get((idx2++) % 10), itr2.next());
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
