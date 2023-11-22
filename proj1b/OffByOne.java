public class OffByOne implements CharacterComparator {
    /**
     * Returns true for characters that are different by exactly one
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == 1 || x - y == -1;
    }
}
