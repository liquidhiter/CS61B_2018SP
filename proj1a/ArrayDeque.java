public class ArrayDeque<T> {
    private static final int EXPANDFACTOR = 2;
    private static final int SHRINKFACTOR = 2;
    private static final int CAPACITYMINIMUM = 8;

    private T[] items;
    private int size;
    private int capacity;
    private int head;
    private int tail;

    private void creatEmptyDeque(int defaultCapacity) {
        capacity = defaultCapacity;
        items = (T[]) new Object[capacity];
        head = (capacity - 1) >> 1;
        tail = head + 1;
        size = 0;
    }

    /**
     * Time Complexity: O(N)
     * NOTE: not required in course of 2018 summer (use the gradescope)
     * @param other
     */
//    public ArrayDeque(ArrayDeque other) {
//        /* Avoid the overhead of resizing */
//        creatEmptyDeque(other.capacity);
//
//        /**
//         * copy elements from other into this
//         * NOTE: call addFirst or addLast results in bad performance
//         *       considering the time complexity of resizing (O(N) x O(N))
//         */
//        int numOfElements = other.size();
//        for (int i = 0; i < numOfElements; ++i) {
//            /* same as addLast call in deep copy constructor of LinkedListDeque */
//            items[tail] = (T) other.get(i);
//            tail = (tail + 1) % capacity;
//            size += 1;
//        }
//    }

    /**
     * Create an empty ArrayDeque
     */
    public ArrayDeque() {
        creatEmptyDeque(CAPACITYMINIMUM);
    }

    /**
     * Helper function for expand and shrink
     * @param newCapacity
     */
    private void updateItems(int newCapacity) {
        /* Calculate the new position of head - front of the array */
        int front = size >> 1;
        /* Copy elements from the old items to the new one */
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newItems[front + i] = get(i);
        }
        /* Update the head and tail based on the front */
        head = front - 1;
        tail = front + size;
        /* Update the capacity */
        capacity = newCapacity;
        /* Assign new items and GC old items */
        T[] oldItems =  items;
        items = newItems;
        oldItems = null;
    }

    /**
     * Expand the array when it is full
     * @param factor
     */
    private void expand(int factor) {
        int newCapacity = capacity * factor;
        updateItems(newCapacity);
    }

    /**
     * Assumption: minimum capacity is 8
     * @param factor
     */
    private void shrink(int factor) {
        int newCapacity = capacity / factor;
        if (newCapacity < CAPACITYMINIMUM) {
            newCapacity = CAPACITYMINIMUM;
        }
        updateItems(newCapacity);
    }

    /**
     *
     */
    private void resize() {
        if (size == capacity) {
            /* Expand - double */
            expand(EXPANDFACTOR);
        } else if (capacity > CAPACITYMINIMUM && capacity > (size << 2)) {
            /* Shrink - halve */
            shrink(SHRINKFACTOR);
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

        /* Ensure the usage ratio >= 1/4 */
        resize();

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

        /* Ensure the usage ratio >= 1/4 */
        resize();

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
