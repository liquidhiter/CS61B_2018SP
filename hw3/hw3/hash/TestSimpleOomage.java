package hw3.hash;

import org.junit.Test;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */

        /* Create all SimpleOomages objects with valid red / blue / green colors */
        HashSet<SimpleOomage> oomages = new HashSet<>();
        for (int r = 0; r <= 255; r += 5) {
            for (int b = 0; b <= 255; b += 5) {
                for (int g = 0; g <= 255; g += 5) {
                    oomages.add(new SimpleOomage(r, g, b));
                }
            }
        }

        /* Ensure there is no hash collision */
        int numOfData = oomages.size();
        SimpleOomage[] oomagesList = oomages.toArray(new SimpleOomage[numOfData]);
        for (int i = 0; i < numOfData - 1; ++i) {
            for (int j = i + 1; j < numOfData; ++j) {
                SimpleOomage oo1 = oomagesList[i];
                SimpleOomage oo2 = oomagesList[j];
                /* Two SimpleOomages can have the same hashcode only when they have the same red / blue / green colors */
                if (oo1.hashCode() == oo2.hashCode()) {
                    assert oo1.red == oo2.red && oo1.blue == oo2.blue && oo1.green == oo2.green : "same hashcode with different colors: [" + oo1 + "], [" + oo2 + "]";
                }
            }
        }

        System.out.println("Perfect HashCode!");
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
