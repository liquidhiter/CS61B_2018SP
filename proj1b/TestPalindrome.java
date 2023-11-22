import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test(timeout = 2000)
    public void testWordToDeque() {
        /* Test returned deque must be null if given a null string */
//        String nullString = null;
//        Deque nullDeque = palindrome.wordToDeque(nullString);
//        assertNull(nullDeque);

        String emptyString = "";
        Deque emptyDeque = palindrome.wordToDeque(emptyString);
        assertNotNull(emptyDeque);
        assertEquals(0, emptyDeque.size());

        /* Test string containing only on char */
        String singleChar = "h";
        Deque singleCharDeque = palindrome.wordToDeque(singleChar);
        assertEquals(1, singleCharDeque.size());
        String retOfSingleChar = "";
        for (int i = 0; i < "h".length(); ++i) {
            retOfSingleChar += singleCharDeque.removeFirst();
        }
        assertEquals("h", retOfSingleChar);

        /* Test string containing many chars */
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);

        String largePalindrome = "";
        largePalindrome += "a";
        for (int i = 0; i < 10000; ++i) {
            largePalindrome += "b";
        }
        largePalindrome += "a";
        Deque manyCharsDeque = palindrome.wordToDeque(largePalindrome);
        assertEquals(10002, manyCharsDeque.size());
        String retOfManyChars = "";
        for (int i = 0; i < largePalindrome.length(); ++i) {
            retOfManyChars += manyCharsDeque.removeFirst();
        }
        assertEquals(largePalindrome, retOfManyChars);

    } // Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        /* Corner cases */
        String nullString = null;
        assertFalse(palindrome.isPalindrome(nullString));

        /* Palindromes */
        String emptyStr = "";
        assertTrue(palindrome.isPalindrome(emptyStr));

        String singleCharA = "A";
        assertTrue(palindrome.isPalindrome(singleCharA));
        String singleCharb = "b";
        assertTrue(palindrome.isPalindrome(singleCharb));
        String singleCharSymbol = "@";
        assertTrue(palindrome.isPalindrome(singleCharSymbol));

        /* Non-palindromes */
        String notAPalindromeA = "abcb";
        assertFalse(palindrome.isPalindrome(notAPalindromeA));
        String notAPalindromeB = "";
        for (int i = 0; i < 10000; ++i) {
            notAPalindromeB += (char) (i % 256);
        }
        assertFalse(palindrome.isPalindrome(notAPalindromeB));
        String notAPalindromeC = "Racecar";
        assertFalse(palindrome.isPalindrome(notAPalindromeC));

        /* palindromes */
        String isPalindromeA = "racecar";
        assertTrue(palindrome.isPalindrome(isPalindromeA));
        String isPalindromeB = "noon";
        assertTrue(palindrome.isPalindrome(isPalindromeB));
        String isPalindromeC = "";
        for (int i = 0; i < 5000; ++i) {
            isPalindromeC += (char) i;
        }
        for (int i = 4999; i >= 0; --i) {
            isPalindromeC += (char) i;
        }
        assertTrue(palindrome.isPalindrome(isPalindromeC));
        String isPalindromeD = "saippuakivikauppias";
        assertTrue(palindrome.isPalindrome(isPalindromeD));
//        assertTrue(palindrome.isPalindrome(isPalindromeD, 0, isPalindromeD.length() - 1));
    }

    @Test
    public void testIsPalindromeWithComparator() {
        CharacterComparator offByOne = new OffByOne();
        /* Invalid input */
//        String nullString = null;
//        assertFalse(palindrome.isPalindrome(nullString, offByOne));
//        String nonNullString = "happy";
//        assertFalse(palindrome.isPalindrome(nonNullString, null));

        /* is off-by-1 palindrome string */
        String isOffBy1Palindrome0 = "flake";
        assertTrue(palindrome.isPalindrome(isOffBy1Palindrome0, offByOne));
        String isOffBy1Palindrome1 = "a";
        assertTrue(palindrome.isPalindrome(isOffBy1Palindrome1, offByOne));
//        String isOffBy1Palindrome2 = " ";
//        assertTrue(palindrome.isPalindrome(isOffBy1Palindrome2, offByOne));
        String isOffBy1Palindrome3 = "@";
        assertTrue(palindrome.isPalindrome(isOffBy1Palindrome3, offByOne));
        String isOffBy1Palindrome4 = "ab";
        assertTrue(palindrome.isPalindrome(isOffBy1Palindrome4, offByOne));

//        int numOfCharsOdd = 12 + 1;
//        LinkedListDeque<Character> oddNumOfChars = new LinkedListDeque<>();
//        oddNumOfChars.addLast('*');
//        for (int i = 0; i < numOfCharsOdd - 1; i += 2) {
//            if (i % 2 == 1) {
//                oddNumOfChars.addLast((char) i);
//                oddNumOfChars.addFirst((char) (i + 1));
//            } else {
//                oddNumOfChars.addFirst((char) i);
//                oddNumOfChars.addLast((char) (i + 1));
//            }
//        }
//        String largeString = "";
//        for (int i = 0; i < numOfCharsOdd; ++i) {
//            largeString += oddNumOfChars.removeFirst();
//        }
//        assertTrue(palindrome.isPalindrome(largeString, offByOne));
//
//        int numOfCharsEven = 12;
//        LinkedListDeque<Character> evenNumOfChars = new LinkedListDeque<>();
//        for (int i = 0; i < numOfCharsEven; i += 2) {
//            if (i % 3 == 1) {
//                evenNumOfChars.addLast((char) i);
//                evenNumOfChars.addFirst((char) (i + 1));
//            } else {
//                evenNumOfChars.addFirst((char) i);
//                evenNumOfChars.addLast((char) (i + 1));
//            }
//        }
//        String largeStringEven = "";
//        for (int i = 0; i < numOfCharsEven; ++i) {
//            largeStringEven += evenNumOfChars.removeFirst();
//        }
//        assertTrue(palindrome.isPalindrome(largeStringEven, offByOne));

        /* not off-by-1 palindrome string */
        String notOffBy1Palindrome0 = "happy";
        assertFalse(palindrome.isPalindrome(notOffBy1Palindrome0, offByOne));
        String notOffBy1Palindrome1 = "abc";
        assertFalse(palindrome.isPalindrome(notOffBy1Palindrome1, offByOne));
//        String notOffBy1Palindrome2 = " @";
//        assertFalse(palindrome.isPalindrome(notOffBy1Palindrome2, offByOne));

        /* Large string to be added */
    }
}
