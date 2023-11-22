import org.junit.Test;
import static org.junit.Assert.*;

public class TestWordUtils {

    @Test
    public void testLongest() {
        /* seriously: shouldn't we add the test case with invalid input parameters? */
        /* What can be tested: return null or exception? */

        /* SLList with single element */
        SLList<String> testList1 = new SLList<>("longest");
        String expected1 = "longest";
        String returned1 = WordUtils.longest(testList1);
        assertEquals(expected1, returned1);

        /* SLList with duplicated elements */
        SLList<String> testList2 = new SLList<>("longest");
        testList2.addLast("longest");
        String expected2 = "longest";
        String returned2 = WordUtils.longest(testList2);
        assertEquals(expected2, returned2);

        /* SLList with more elements */
        SLList<String> testList3 = new SLList<>("first");
        testList3.addLast("first");
        testList3.addLast("second");
        testList3.addLast("last");
        testList3.addLast("maximumisnotmaximum");
        testList3.addLast("HHHHHHHHHHHHHHHHHHH");
        testList3.addLast("IAMIORNMAN");
        /* what if user wants the longest element with the largest index ?*/
        String expected3 = "maximumisnotmaximum";
        String returned3 = WordUtils.longest(testList3);
        assertEquals(expected3, returned3);
    }
}
