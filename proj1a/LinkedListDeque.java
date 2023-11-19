public class LinkedListDeque<T> {

    /*=============================== IntNode ===============================*/
    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(IntNode prev, T item, IntNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /*==== Implementation of LinkedListDeque ====*/
    private IntNode sentinel;       // sentinel node: tracking both head and tail
    private int size;               // cache the size to ensure constant time of getting the size

    /**
     * Returns an empty linked list deque
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null); // null item to be compatible with generic type T
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Returns a deep copy of the given linked list deque
     * @param other
     */
    public LinkedListDeque(LinkedListDeque other) {
        throw new RuntimeException("Not yet implemented!");
    }

    /**
     *
     * @param item
     */
    public void addFirst(T item) {
        IntNode first = new IntNode(null, item, null);
        first.prev = sentinel;
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;

        /*sentinel.prev needs point to the first node*/
        if (sentinel.prev == sentinel) {
            sentinel.prev = first;
        }

        size += 1;
    }


    /**
     *
     * @param item
     */
    public void addLast(T item) {
        IntNode last = new IntNode(null, item, null);
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev.next = last;
        sentinel.prev = last;

        /*sentinel.next needs point to the first node*/
        if (sentinel.next == sentinel) {
            sentinel.next = last;
        }

        size += 1;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
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
     */
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("");
        }

        IntNode p = sentinel.next;
        do {
            System.out.print(p.item);
            p = p.next;

            if (p != sentinel) {
                System.out.print(" ");
            }
        } while (p != sentinel);
        System.out.println();
    }

    /**
     *
     * @return
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        IntNode first = sentinel.next;
        T retVal = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first = null;
        size -= 1;

        return retVal;
    }

    /**
     *
     * @return
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        IntNode last = sentinel.prev;
        T retVal = last.item;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last = null;
        size -= 1;

        return retVal;
    }

    /**
     *
     * @param index
     * @return
     */
    public T get(int index) {
        int position = 0;
        IntNode p = sentinel.next;
        do {
            if (position == index) {
                return p.item;
            }
            position += 1;
            p = p.next;
        } while (p != sentinel);

        return null;

        /* Simplified version: personally prefer the do-while for better readability */
//        IntNode node = sentinel.next;
//        for (int i = 0; i < index && node != sentinel; i++, node = node.next);
//        return node == sentinel ? null : node.item;
    }

    /**
     * Helper method for getRecursive
     * @param first
     * @param index
     * @return
     */
    private IntNode getRecursive(IntNode first, int index) {
        if (index == 0 || first == sentinel) {
            return first;
        }

        return getRecursive(first.next, index - 1);
    }

    /**
     *
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        IntNode nodeAtIndex = getRecursive(sentinel.next, index);
        return nodeAtIndex == null ? null : nodeAtIndex.item;
    }

    /*==== Useful method for Unit Test ====*/

    /**
     *
     * @return
     */
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }

        return sentinel.next.item;
    }

    /**
     *
     * @return
     */
    public T getLast() {
        if (isEmpty()) {
            return null;
        }

        return sentinel.prev.item;
    }
}
