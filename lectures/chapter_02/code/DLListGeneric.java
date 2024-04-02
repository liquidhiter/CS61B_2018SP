package lectures.chapter_02.code;
public class DLListGeneric<T> {
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

    //================ Implementation of DLListGeneric ================ //
    private IntNode sentinel;
    private int size;

    /**
     * Parametrized constructor
     * @param x
     */
    public DLListGeneric(T x) {
        // If only type T elements are stored in the list
        // What's the meaning of having integers in the sentinel node, isnt it weird?
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Return an empty list
     */
    public DLListGeneric() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
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
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return
     */
    public T getFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");

        return sentinel.next.item;
    }

    /**
     *
     * @return
     */
    public T getLast() {
        if (isEmpty())
            throw new RuntimeException("Empty list");

        return sentinel.prev.item;
    }

    /**
     *
     * @param x
     */
    public void addFirst(T x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        if (isEmpty())
            // sentinel.prev keeps tracking the last node which needs update for empty list
            sentinel.prev = newNode;

        size += 1;
    }

    /**
     *
     */
    public void removeFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");

        IntNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first = null;
        size -= 1;
    }

    /**
     *
     * @param x
     */
    public void addLast(T x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        if (isEmpty())
            // sentinel.next keeps tracking the first node which needs update for empty list
            sentinel.next = newNode;

        size += 1;
    }

    /**
     *
     */
    public void removeLast() {
        if (isEmpty())
            throw new RuntimeException("Empty list");

        IntNode last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last = null;
        size -= 1;
    }


    public static void main(String[] args) {

        // Basic test of non-empty list
        DLListGeneric<Integer> list = new DLListGeneric<>(10);
        assert(list.size() == 1);
        assert(list.isEmpty() == false);
        assert(list.getFirst() == 10);
        assert(list.getLast() == 10);
        list.addFirst(9);
        assert(list.size() == 2);
        assert(list.getLast() == 10);
        assert(list.getFirst() == 9);
        list.removeFirst();
        assert(list.size() == 1);
        assert(list.getLast() == 10);
        assert(list.getFirst() == 10);
        list.addLast(11);
        assert(list.size() == 2);
        assert(list.getLast() == 11);
        assert(list.getFirst() == 10);
        list.removeLast();
        assert(list.size() == 1);
        assert(list.getFirst() == 10);
        assert(list.getLast() == 10);


        // Basic test of empty list
        DLListGeneric<Integer> emptyList = new DLListGeneric<>();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);
        emptyList.addFirst(10);
        assert(emptyList.isEmpty() == false);
        assert(emptyList.size() == 1);
        assert(emptyList.getFirst() == 10);
        assert(emptyList.getLast() == 10);
        emptyList.removeFirst();
        assert(emptyList.isEmpty() == true);
        assert(emptyList.size() == 0);
        // emptyList.getFirst();
        // emptyList.getLast();
        emptyList.addLast(10);
        assert(emptyList.isEmpty() == false);
        assert(emptyList.size() == 1);
        assert(emptyList.getFirst() == 10);
        assert(emptyList.getLast() == 10);
        emptyList.removeLast();
        assert(emptyList.isEmpty() == true);
    }
}
