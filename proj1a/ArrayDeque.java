public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int capacity = 8;
    private int head;
    private int tail;

    /**
     * Create an empty ArrayDeque
     */
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
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
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return
     */
    public int size() {
        return size;
    }

    /**
     *
     */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println(" ");
        }

        for (int i = 0; i < size; ++i) {
            System.out.print(get(i));
            if (i < size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * @return
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int indexOfFirst = (head + 1) % capacity;
        T first = items[indexOfFirst];
        items[indexOfFirst] = null;
        head = indexOfFirst;
        size -= 1;
        return first;
    }

    /**
     * @return
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int indexOfLast = Math.floorMod(tail - 1, capacity);
        T last = items[indexOfLast];
        items[indexOfLast] = null;
        tail = indexOfLast;
        size -= 1;
        return last;
    }

    /**
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        /* +1 is because head always points to the next memory box */
        return items[(head + index + 1) % capacity];
    }
}
