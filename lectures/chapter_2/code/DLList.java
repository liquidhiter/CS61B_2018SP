public class DLList {
    private static class IntNode {
        public IntNode prev;
        public int item;
        public IntNode next;

        public IntNode(IntNode p, int i, IntNode n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }

    //========== Implementation of DLList ============= //
    private IntNode sentinel;
    private IntNode last;
    private int size;

    /**
     * 
     * @param x
     */
    public DLList(int x) {
        sentinel = new IntNode(null, 63, null);
        IntNode first = new IntNode(null, x, null);
        sentinel.next = first;
        first.prev = sentinel;
        last = first;
        size += 1;
    }

    /**
     * Returns an empty list
     */
    public DLList() {
        sentinel = new IntNode(null, 63, null);
        last = sentinel;
        size = 0;
    }

    /**
     * Add a new element in front of the list
     * @param x
     */
    public void addFirst(int x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        if (isEmpty())
            last = newNode;
        
        size += 1;
    }

    /**
     * Returns the first element's value
     */
    public int getFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        
        return sentinel.next.item;
    }
    
    /**
     * Removes the first element
     */
    public void removeFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        
        // Remove the first element from the list
        IntNode first = sentinel.next;
        sentinel.next = first.next;
        first.prev = null;
        first.next = null;

        size -= 1;

        // Corner case: one element list, and last needs to point to the sentinel node
        if (isEmpty())
            last = sentinel;
    }

    /**
     * 
     * @param x
     */
    public void addLast(int x) {
        IntNode newNode = new IntNode(null, x, null);
        if (isEmpty())
            sentinel.next = newNode;
        
        newNode.prev = last;
        last.next = newNode;
        last = newNode;
        size += 1;
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
        return size() == 0;
    }

    /**
     * 
     * @return
     */
    public int getLast() {
        // Standard behavior if empty list?
        // - throw an exception: empty list has nothing to return
        // - throw a warning message ?
        // - return a special value indicating errors ?
        // Personally prefer exception - early stop
        if (isEmpty())
            throw new RuntimeException("Empty list!");

        return last.item;
    }

    /**
     * Remove the last element
     * @return
     */
    public void removeLast() {
        if (isEmpty())
            throw new RuntimeException("Empty list!");

        IntNode secondToLast = last.prev;
        secondToLast.next = null;
        last = secondToLast;
        size -= 1;
    }

    public static void testLastMethods() {
        // Basic tests of empty list
        DLList emptyList = new DLList();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);

        final int len = 10;
        for (int i = 0; i < len; ++i) {
            emptyList.addLast(i);
            assert(emptyList.size() == i + 1);
            assert(emptyList.getLast() == i);
            // System.out.println("Last element is " + emptyList.getLast());
        }
        assert(emptyList.size() == len);
        assert(emptyList.getLast() == len - 1);
        assert(emptyList.isEmpty() == false);

        for (int i = 9; i >= 0; --i) {
            assert(emptyList.getLast() == i);
            emptyList.removeLast();
            assert(emptyList.size() == i);
            assert(emptyList.getLast() == i - 1);
        }
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == false);

        // Basic tests of non-empty list
        DLList list = new DLList(0);
        assert(list.size() == 1);
        assert(list.isEmpty() == false);
        assert(list.getLast() == 0);
        for (int i = 1; i < len; ++i) {
            list.addLast(i);
            assert(list.size() == i + 1);
            assert(list.getLast() == i);
        }
        assert(list.size() == len);
        assert(list.isEmpty() == false);
        assert(list.getLast() == 9);

        for (int i = 9; i >= 0; --i) {
            assert(list.getLast() == i);
            list.removeLast();
            assert(list.size() == i);
            assert(list.getLast() == i - 1);
        }
        assert(list.size() == 0);
        assert(list.isEmpty() == false);
    }

    public static void testFirstMethods() {
        // Basic tests of empty lists
        DLList emptyList = new DLList();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty());
        for (int i = 1; i <= 10; ++i) {
            emptyList.addFirst(i);
            assert(emptyList.size() == i);
            assert(emptyList.getFirst() == i);
            assert(emptyList.getLast() == i);
        }
        assert(emptyList.size() == 10);
        assert(emptyList.isEmpty() == false);
        assert(emptyList.getFirst() == 1);
        assert(emptyList.getLast() == 10);

        for (int i = 1; i <= 10; ++i) {
            assert(emptyList.getFirst() == i);
            assert(emptyList.size() == 11 - i);
            emptyList.removeFirst();
            assert(emptyList.getFirst() == i + 1);
            assert(emptyList.size() == 10 - i);
        }
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);

        //TDOO: I am lazy to add more test cases ...
    }

    public static void testMixMethods() {
        throw new RuntimeException("Not implemented");
    }
    
    public static void main(String[] args) {
        testLastMethods();
        testFirstMethods();
        // testMixMethods();
    }

}
