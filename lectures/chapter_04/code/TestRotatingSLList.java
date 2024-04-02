package lectures.chapter_04.code;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestRotatingSLList {
    @Test(timeout = 5000)
    public void testRotateRight() {
        SLList<Integer> emptyList = new SLList<>(1);
        emptyList.removeLast();
        assertEquals(0, emptyList.size());

        RotatingSLList<Integer> list = new RotatingSLList<>();
        RotatingSLList<Integer> rotatedList = list.rotateRight(emptyList);
        assertEquals(0, rotatedList.size());

        final int lenOfList = 10000;
        for (int i = 0; i < lenOfList; ++i) {
            emptyList.addFirst(i);
        }
        assertEquals(lenOfList, emptyList.size());
        rotatedList = list.rotateRight(emptyList);
        assertEquals(lenOfList, rotatedList.size());
        assertEquals(emptyList.get(lenOfList - 1), rotatedList.get(0));
        for (int i = 0; i < lenOfList - 1; ++i) {
            assertEquals(emptyList.get(i), rotatedList.get(i+1));
        }
    }

    @Test(timeout = 5000)
    public void testRoateLeft() {
        SLList<Integer> emptyList = new SLList<>(1);
        emptyList.removeLast();
        assertEquals(0, emptyList.size());

        RotatingSLList<Integer> list = new RotatingSLList<>();
        RotatingSLList<Integer> rotatedList = list.rotateLeft(emptyList);
        assertEquals(0, rotatedList.size());

        final int lenOfList = 10000;
        for (int i = 0; i < lenOfList; ++i) {
            emptyList.addFirst(i);
        }
        assertEquals(lenOfList, emptyList.size());
        rotatedList = list.rotateLeft(emptyList);
        assertEquals(lenOfList, rotatedList.size());
        assertEquals(emptyList.get(0), rotatedList.get(lenOfList - 1));
        for (int i = 1; i < lenOfList - 1; ++i) {
            assertEquals(emptyList.get(i), rotatedList.get(i - 1));
        }
    }
}
