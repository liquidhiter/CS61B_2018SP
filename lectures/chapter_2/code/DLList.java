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
    
    public static void main(String[] args) {

        // Basic tests of empty list
        DLList emptyList = new DLList();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);

        final int len = 10;
        for (int i = 0; i < len; ++i) {
            emptyList.addLast(i);
            assert(emptyList.size() == i + 1);
            assert(emptyList.getLast() == i);
        }

        // Basic tests of non-empty list
        DLList list = new DLList(0);
        assert(list.size() == 1);
        assert(list.isEmpty() == false);
        assert(list.getLast() == 0);
    }

}
