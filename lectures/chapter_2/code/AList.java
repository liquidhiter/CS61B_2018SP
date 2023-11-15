public class AList {

    private int items[];
    private int size;

    /* Dynamically adjust: shrink or expand */
    private static final int CAPACITY = 100;

    /* Instaniate an empty list */
    public AList() {
        // items = new T[CAPACITY];
        items = new int[CAPACITY];
        size = 0;
    }

    /**
     * 
     * @param x
     */
    public void addLast(int x) {
        // TODO: remove the assert here
        assert(size < CAPACITY) : "Can't eat more than I can hold";
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
        for (int i = 1; i < 10; ++i) {
            numbers.addLast(i);
            assert(numbers.size() == i + 1);
            assert(numbers.getLast() == i);
        }

        for (int i = 0; i < 10; ++i) {
            assert(numbers.get(i) == i);
        }
    }
}
