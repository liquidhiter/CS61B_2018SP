import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    // Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
    @Test(timeout = 2000)
    public void testEqualChars() {
        char x0 = ' ';
        char y0 = 'a';
        assertFalse(offByOne.equalChars(x0, y0));
        char y1 = ' ';
        assertFalse(offByOne.equalChars(x0, y1));
        char[] x1 = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[] y2 = {'b', 'c', 'd', 'e', 'f', 'g'};
        for (int i = 0; i < 6; ++i) {
            assertTrue(offByOne.equalChars(x1[i], y2[i]));
            assertTrue(offByOne.equalChars(y2[i], x1[i]));
        }
        ArrayList<Character> listOfChars0 = new ArrayList<>();
        for (int i = 0; i < 10000; ++i) {
            listOfChars0.add((char) i);
        }
        ArrayList<Character> listOfChars1 = new ArrayList<>();
        for (int i = 1; i < 10001; ++i) {
            listOfChars1.add((char) i);
        }
        for (int i = 0; i < 10000; ++i) {
            assertTrue(offByOne.equalChars(listOfChars0.get(i), listOfChars1.get(i)));
            assertTrue(offByOne.equalChars(listOfChars1.get(i), listOfChars0.get(i)));
        }
    }
}
