package lectures.chapter_02.code;
public class AList {

    private int items[];
    private int size;

    /* Dynamically adjust: shrink or expand */
    private int CAPACITY = 2;

    /* Instaniate an empty list */
    public AList() {
        // items = new T[CAPACITY];
        items = new int[CAPACITY];
        size = 0;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    private void expand(int factor) {
        // System.out.println("    size = " + size + ", capacity = " + CAPACITY);

        /* Double the capacity */
        CAPACITY *= factor;

        /* Create new array */
        int[] newArray = new int[CAPACITY];

        /* Copy existing elements into the new array */
        System.arraycopy(items, 0, newArray, 0, size);

        /* Points items to new array */
        items = newArray;

        /* Optimization: GC ?*/
    }

    private void shrink(int factor) {
        CAPACITY /= factor;
        int[] newArray = new int[CAPACITY];
        System.arraycopy(items, 0, newArray, 0, size);
        items = newArray;
    }

    /**
     * 
     * @param x
     */
    public void addLast(int x) {
        // TODO: remove the assert here
        // assert(size < CAPACITY) : "Can't eat more than I can hold";
        if (size == CAPACITY) {
            expand(2);
        }

        double usageRatio = (double)(size) / CAPACITY;
        if (usageRatio < 0.25) {
            shrink(2);
        }

        items[size++] = x;
    }

    /**
     * 
     * @return
     */
    public int getLast() {
        return items[size - 1];
    }

    /**
     * 
     * @param index
     * @return
     */
    public int get(int index) {
        // assert(index >= 0 && index < size);
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return items[index];
    }

    /**
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 
     * @return
     */
    public int removeLast() {
        if (size == 0) {
            throw new RuntimeException("Empty list");
        }
        return items[--size];
    }

    public static void main(String[] args) {
        AList numbers = new AList();
        assert(numbers.size() == 0);
        for (int i = 1; i < 100000; ++i) {
            numbers.addLast(i);
            assert(numbers.size() == i + 1);
            assert(numbers.getLast() == i);

            // /* Size V.S. Capacity */
            // System.out.println("    size = " + numbers.size() + ", capacity = " + numbers.getCapacity());
        }

        for (int i = 0; i < 10; ++i) {
            assert(numbers.get(i) == i);
        }
    }
}
