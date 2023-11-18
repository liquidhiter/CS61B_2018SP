public class Sort {

    /**
     *
     * @param str
     * @param from
     * @param to
     */
    private static void selectionSort(String[] str, int from, int to) {
        if (from == to) {
            return;
        }

        int indexOfSmallest = findSmallest(str, from, to);
        swap(str, from, indexOfSmallest);

        selectionSort(str, from + 1, to);
    }

    /**
     * Sort strings destructively
     * @param str
     */
    public static void sort(String[] str) {
        /* 1> Find the smallest item
         * 2> Move it to the front
         * 3> Selection sort the rest
         */
        selectionSort(str, 0, str.length);
    }

    /**
     * Return the smallest string in the given array
     * @param str
     * @return
     */
    public static int findSmallest(String[] str, int from, int to) {
        int smallest = from;
        for (int i = from; i < to; ++i) {
            if (str[i].compareTo(str[smallest]) < 0) {
                smallest = i;
            }
        }

        return smallest;
    }

    /**
     *
     * @param str
     * @param a
     * @param b
     */
    public static void swap(String[] str, int a, int b) {
       String tmp = str[a];
       str[a] = str[b];
       str[b] = tmp;
    }
}
