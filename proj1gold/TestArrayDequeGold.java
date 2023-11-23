import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /* APIs need test:
     * addFirst
     * addLast
     * removeFirst
     * removeLast
     * isEmpty
     * size
     * get
     * */
    private static ArrayDequeSolution<Integer> dequeReference = new ArrayDequeSolution<>();
    private static StudentArrayDeque<Integer> dequeTest = new StudentArrayDeque<>();

//    @Test
//    public void testIsEmpty() {
//        Integer item = getRandInteger();
//        dequeTest.addLast(item);
//        assertFalse(dequeTest.isEmpty());
//        while (!dequeTest.isEmpty()) {
//            dequeTest.removeFirst();
//        }
//        /* Redundant test */
//        assertTrue(dequeTest.isEmpty());
//    }

//    @Test
//    public void testAddFirst() {
//        Integer item = getRandInteger();
//        int sizeOfTest = dequeTest.size();
//        dequeReference.addFirst(item);
//        dequeTest.addFirst(item);
//        assertEquals(sizeOfTest + 1, dequeTest.size());
//        assertEquals(dequeReference.get(0), dequeTest.get(0));
//    }
//
//    @Test
//    public void testSize() {
//        int numOfItems = getRandPositiveInteger();
//        int sizeOfTestPrev = dequeTest.size();
//        for (int i = 0; i < numOfItems; ++i) {
//            double anchor = StdRandom.gaussian();
//            Integer newItem = getRandInteger();
//            if (anchor < 1/3) {
//                dequeReference.addFirst(newItem);
//                dequeTest.addFirst(newItem);
//            } else {
//                dequeReference.addLast(newItem);
//                dequeTest.addLast(newItem);
//            }
//        }
//        assertEquals(sizeOfTestPrev + numOfItems, dequeTest.size());
//    }
//
//    @Test
//    public void testAddLast() {
//        Integer item = getRandInteger();
//        int sizeOfTest = dequeTest.size();
//        dequeReference.addLast(item);
//        dequeTest.addLast(item);
//        assertEquals(sizeOfTest + 1, dequeTest.size());
//        assertEquals(dequeReference.get(dequeReference.size() - 1), dequeTest.get(dequeTest.size() - 1));
//    }
//
//    @Test
//    public void testRemoveFirst() {
//        if (dequeReference.isEmpty() && dequeTest.isEmpty()) {
//            assertTrue(true);
//        } else if (dequeTest.isEmpty() || dequeReference.isEmpty()) {
//            assertTrue(false);
//        } else {
//            int sizeOfTest = dequeTest.size();
//            Integer firstOfReference = dequeReference.removeFirst();
//            Integer firstOfTest = dequeTest.removeFirst();
//            assertEquals(sizeOfTest - 1, dequeTest.size());
//            assertEquals(firstOfReference, firstOfTest);
//        }
//    }
//
//    @Test
//    public void testRemoveLast() {
//        if (dequeReference.isEmpty() && dequeTest.isEmpty()) {
//            assertTrue(true);
//        } else if (dequeTest.isEmpty() || dequeReference.isEmpty()) {
//            assertTrue(false);
//        } else {
//            int sizeOfTest = dequeTest.size();
//            Integer lastOfReference = dequeReference.removeLast();
//            Integer lastOfTest = dequeTest.removeLast();
//            assertEquals(sizeOfTest - 1, dequeTest.size());
//            assertEquals(lastOfReference, lastOfTest);
//        }
//    }
//
//    /**
//     * Generate random data and fill in both reference and test deque with them ???
//     * @param minRange
//     * @param maxRange
//     */
//    private void randomizeDeque(int minRange, int maxRange, int numOfItems) {
//        while (numOfItems-- > 0) {
//            Integer item = StdRandom.uniform(minRange, maxRange);
//            double anchor = StdRandom.gaussian();
//            if (anchor < 3/7) {
//                dequeTest.addLast(item);
//                dequeReference.addLast(item);
//            } else {
//                dequeTest.addFirst(item);
//                dequeReference.addFirst(item);
//            }
//        }
//    }

    private Integer getRandPositiveInteger() {
        return StdRandom.uniform(Integer.MAX_VALUE) % (2 << 14) + (2 << 10);
    }

    private Integer getRandNegativeInteger() {
        return StdRandom.uniform(Integer.MIN_VALUE, 0);
    }

    private Integer getRandInteger() {
        return StdRandom.uniform(Integer.MIN_VALUE >> 4, Integer.MAX_VALUE >> 4);
    }

    /**
     *
     */
    @Test
    public void testRandomOps() {
        String opTrace = "\n";
        for (int i = 0; i < 65536; ++i) {
            int opCode = StdRandom.uniform(0, 4);
            switch (opCode) {
                case 0: {
                    Integer item = getRandInteger();
                    dequeReference.addFirst(item);
                    dequeTest.addFirst(item);
                    opTrace += "addFirst(" + item + ")\n";
                    assertEquals(opTrace, dequeReference.get(0), dequeTest.get(0));
                    break;
                }
                case 1: {
                    Integer item = getRandInteger();
                    dequeReference.addLast(item);
                    dequeTest.addLast(item);
                    opTrace += "addLast(" + item + ")\n";
                    assertEquals(opTrace, dequeReference.get(dequeReference.size() - 1), dequeTest.get(dequeTest.size() - 1));
                    break;
                }
                case 2: {
                    if (dequeTest.isEmpty() || dequeReference.isEmpty()) {
                        continue;
                    }
                    opTrace += "removeFirst()\n";
                    assertEquals(opTrace, dequeReference.removeFirst(), dequeTest.removeFirst());
                    break;
                }
                case 3: {
                    if (dequeTest.isEmpty() || dequeReference.isEmpty()) {
                        continue;
                    }
                    opTrace += "removeLast()\n";
                    assertEquals(opTrace, dequeReference.removeLast(), dequeTest.removeLast());
                    break;
                }
            }
        }
    }
}
