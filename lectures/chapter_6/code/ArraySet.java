package lectures.chapter_6.code;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public String toString() {
        /* Naive solution: immutable string and always create new string */
        // String formatString = "(";
        // for (T x : items) {
        //     formatString += x.toString() + ",";
        // }
        // formatString += ")";
        // return formatString;
        StringBuilder strBuilder = new StringBuilder("(");
        // for (T x : items) {
        //     if (x == null) {
        //         strBuilder.append("null");
        //     } else {
        //         strBuilder.append(x.toString());
        //     }
        //     strBuilder.append(",");
        // }
        for (int i  = 0; i < size; ++i) {
            T item = items[i];
            if (item == null) {
                strBuilder.append("null");
            } else {
                strBuilder.append(item.toString());
            }

            if (i < size - 1) {
                strBuilder.append(",");
            }
        }
        strBuilder.append(")");

        return strBuilder.toString();
    }

    public static <type> ArraySet<type> of(type... args) {
        ArraySet<type> result = new ArraySet<>();
        for (type t : args) {
            result.add(t);
        }

        return result;
    }

    /*@Override
    public String toString() {
        List<String> listOfItems = new ArrayList<String>();
        for (T item : this) {
            listOfItems.add(item.toString());
        }
        return "{" + String.join(",", listOfItems) + "}";
    }*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        ArraySet<T> other = (ArraySet<T>) obj;
        if (other.size() != this.size()) {
            return false;
        }

        for (T item : this) {
            if (!other.contains(item)) {
                return false;
            }
        }
        
        return true;
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
        // System.out.println(s.contains("horse"));
        // System.out.println(s.size());

        System.out.println(s);

        // Iterator<String> iter = s.iterator();
        // while (iter.hasNext()) {
        //     System.out.println(iter.next());
        // }

        // for (String item : s) {
        //     System.out.println(item);
        // }
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */

    /* Iterator */

    /* Enhanced for-loop  */

}