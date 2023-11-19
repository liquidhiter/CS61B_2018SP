public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int capacity = 8;
    private int head;
    private int tail;

    /**
     * Create an empty ArrayDeque
     */
    public ArrayDeque() {
        items = (T[])new Object[capacity];
        head = capacity / 2;
        tail = head + 1;
        size = 0;
    }

    private void resize() {
        if (size == capacity) {
            /* Expand - double */
        } else if (capacity < (size << 2)) {
            /* Shrink - halve */
        }
    }

    /**
     * Helper method to update the head index
     * hm... mod operator returns the remainder instead of the modulus
     * e.x. -1 mod 8 returns -1 instead of 7 (expected)
     * !NOTE: Math.floorMod is the one!
     */
//    private void updateHeadIndex() {
//        head = head - 1 < 0 ? capacity - 1 : head - 1;
//    }

    /**
     * @param item
     */
    @Override
    public void addFirst(T item) {
        /* Performance improvement */
        resize();

        /*Invariant: array is not full*/
        items[head] = item;
        head = Math.floorMod(head - 1, capacity);
        size += 1;
    }

    /**
     * @param item
     */
    @Override
    public void addLast(T item) {
        /* Performance improvement */
        resize();

        /* Invariant: array is not full */
        items[tail] = item;
        tail = (tail + 1) % capacity;
        size += 1;
    }

    /**
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *
     */
    @Override
    public void printDeque() {

    }

    /**
     * @return
     */
    @Override
    public T removeFirst() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public T removeLast() {
        return null;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return items[index];
    }

    /*=============================== Useful method for Unit Test ===============================*/
//    public T getFirst() {
//        if (isEmpty()) {
//            return null;
//        }
//
//        return items[head - 1];
//    }
}
