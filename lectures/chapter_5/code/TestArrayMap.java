package Map61B;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayMap {

    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<Integer, Integer>();
        am.put(2, 5);
        int expected = 5;
        long actual = am.get(2); // unbox the returned value of am.get(2) to long
//        assertEquals(expected, am.get(2));
        assertEquals(expected, actual);
    }
}
