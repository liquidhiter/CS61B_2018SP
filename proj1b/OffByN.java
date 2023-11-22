public class OffByN implements CharacterComparator {
    private int offSetN;

    public OffByN(int N) {
        assert (N >= 0);
        offSetN = N;
    }


    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == offSetN || y - x == offSetN;
    }
}
