public class DLListWithSentinelNodes {
    
    /**
     * Nested class representing each node in the DLList
     */
    private static class IntNode {
        public IntNode prev;
        public int item;
        public IntNode next;

        public IntNode(IntNode prev, int item, IntNode next) {
            this.prev = prev; 
            this.item = item; 
            this.next = next;
        }
    }

    //======================== Implementation of DLListWithSentinelNodes ========================//
    /* Two sentinel nodes */
    private IntNode head;
    private IntNode tail;
    /* Cache size to avoid constant time of iteration to get the size */
    private int size;

    /**
     * Parametereized constructor
     * @param x
     */
    public DLListWithSentinelNodes(int x) {
        head = new IntNode(null, 63, null);
        tail = new IntNode(null, 64, null);
        head.next = new IntNode(head, x, tail);
        tail.prev = head.next;
        size = 1;
    }

    /**
     * Return an empty list
     */
    public DLListWithSentinelNodes() {
        head = new IntNode(null, 63, null);
        tail = new IntNode(null, 64, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * Constant time
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Constant time
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Constant time
     * @return
     */
    public int getFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        
        return head.next.item;
    }

    /**
     * Constant time
     * @return
     */
    public int getLast() {
        if (isEmpty())
            throw new RuntimeException("Emtpy list");
        
        return tail.prev.item;
    }

    /**
     * Constant time
     * @param x
     */
    public void addFirst(int x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;

        // Tail needs to point to the new node if it was empty
        //! Special case can't be avoided
        if (isEmpty())
            tail.prev = newNode;

        size += 1;
    }

    /**
     * Constant time
     * @mutator
     */
    public void removeFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        
        IntNode first = head.next;
        head.next = first.next;
        first.next.prev = head;
        // Better to inform GC to re-cycle the first ?
        first = null;
        size -= 1;
    }

    /**
     * Constant time
     * @mutator
     * @param x
     */
    public void addLast(int x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;

        // Head needs to point to the new node if it was empty
        //! Special case can't be avoided
        if (isEmpty())
            head.next = newNode;

        size += 1;
    }

    /**
     * Constant time
     * @mutator
     */
    public void removeLast() {
        IntNode last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;
        // Better to inform GC to re-cycle the first ?
        last = null;
        size -= 1;
    }


    public static void main(String[] args) {

        // Basic Tests of Non-empty lists
        DLListWithSentinelNodes list = new DLListWithSentinelNodes(-10);
        assert(list.size() == 1);
        assert(list.isEmpty() == false);
        assert(list.getFirst() == -10);
        assert(list.getLast() == -10);
        list.addFirst(-9);
        assert(list.size() == 2);
        assert(list.getFirst() == -9);
        assert(list.getLast() == -10);
        list.removeFirst();
        assert(list.size() == 1);
        assert(list.getFirst() == -10);
        assert(list.getLast() == -10);
        list.addLast(-11);
        assert(list.size() == 2);
        assert(list.getFirst() == -10);
        assert(list.getLast() == -11);
        list.removeLast();
        assert(list.size() == 1);
        assert(list.getFirst() == -10);
        assert(list.getLast() == -10);

        // Basic Tests of empty list
        DLListWithSentinelNodes emptyList = new DLListWithSentinelNodes();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);
        // emptyList.getFirst();
        // emptyList.getLast();
        emptyList.addFirst(-10);
        assert(emptyList.size() == 1);
        assert(emptyList.isEmpty() == false);
        assert(emptyList.getFirst() == -10);
        assert(emptyList.getLast() == -10);
        emptyList.removeFirst();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);
        // emptyList.getFirst();
        // emptyList.getLast();
        emptyList.addLast(-11);
        assert(emptyList.size() == 1);
        assert(emptyList.isEmpty() == false);
        assert(emptyList.getFirst() == -11);
        assert(emptyList.getLast() == -11);
        emptyList.removeLast();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);
        // emptyList.getFirst();
        // emptyList.getLast();
    }
}
