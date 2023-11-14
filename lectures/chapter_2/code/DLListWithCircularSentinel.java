public class DLListWithCircularSentinel {
    
    private static class IntNode {
        private IntNode prev;
        private int item;
        private IntNode next;

        public IntNode(IntNode prev, int item, IntNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    //================ Implementation of DLListWithCircularSentinel ================ //
    private IntNode sentinel;
    private int size;

    /**
     * Parametrized constructor
     * @param x
     */
    public DLListWithCircularSentinel(int x) {
        sentinel = new IntNode(null, 63, null);
        sentinel.next = new IntNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Return an empty list
     */
    public DLListWithCircularSentinel() {
        sentinel = new IntNode(null, 63, null);
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
    public int getFirst() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        
        return sentinel.next.item;
    }

    /**
     * 
     * @return
     */
    public int getLast() {
        if (isEmpty())
            throw new RuntimeException("Empty list");
        
        return sentinel.prev.item;
    }

    /**
     * 
     * @param x
     */
    public void addFirst(int x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
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
    public void addLast(int x) {
        IntNode newNode = new IntNode(null, x, null);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
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
        DLListWithCircularSentinel list = new DLListWithCircularSentinel(10);
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
        DLListWithCircularSentinel emptyList = new DLListWithCircularSentinel();
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
