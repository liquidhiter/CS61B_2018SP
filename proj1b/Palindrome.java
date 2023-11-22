public class Palindrome {

    /**
     * Time Complexity: O(N)
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }

        int lenOfWord = word.length();
        Deque<Character> retDeque = new LinkedListDeque<>();
        for (int i = 0; i < lenOfWord; ++i) {
            retDeque.addLast(word.charAt(i));
        }

        return retDeque;
    }

    /**
     * Recursive implementation of isPalindrome
     * @param word
     * @param left
     * @param right
     * @return
     */
    private boolean isPalindrome(String word, int left, int right) {
        if (left >= right) {
            return true;
        }

        return isPalindrome(word, left + 1, right - 1)
                & (word.charAt(left) == word.charAt(right));
    }


    /**
     * Time Complexity: O(N)
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        int lenOfWord = word.length();
        for (int i = 0, j = lenOfWord - 1; i < j; ++i, --j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || cc == null) {
            return false;
        }

        int lenOfWord = word.length();
        for (int i = 0, j = lenOfWord - 1; i < j; ++i, --j) {
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                return false;
            }
        }

        return true;
    }
}
