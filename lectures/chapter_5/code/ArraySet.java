import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        /* Assume initial capacity is 100 */
        items = (T[]) new Object[100];
        size = 0;
    }

    /**
     * Helper method used to locate the given element in the array
     * Assumption: Object T is comparable ?
     * @param item
     * @return
     */
    private int find(T item) {
        for (int i = 0; i < size; ++i) {
            /* Allow null to be stored as one of the elements? */
            if (items[i] == null) {
                if (item == null) {
                    return i;
                }
            } else {
                if (items[i].equals(item)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        return find(x) > 0;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (contains(x)) {
            return;
        }

        items[size++] = x;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** returns an iterator (a.k.a. seer) */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int nextIdx;

        public ArraySetIterator() {
            nextIdx = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIdx < size;
        }

        @Override
        public T next() {
            return items[nextIdx++];
        }
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        Iterator<String> iter = s.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        for (String item : s) {
            System.out.println(item);
        }
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */

    /* Iterator */

    /* Enhanced for-loop  */

}