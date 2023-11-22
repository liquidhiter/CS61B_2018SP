public class SLList {
        
    /**
     * Nested class: IntNode representing each element in a linked-list
     */
    protected static class IntNode {
        public int item;
        public IntNode next;
        
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    };

    /*================================= Implementation of SLList /*=================================*/
    // head is a sentinel node
    private IntNode head;
    private int size;

    /**
     * 
     * @param x
     */
    public SLList(int x) {
        IntNode first = new IntNode(x, null);
        // first initialize the sentinel node with special value of `63`
        head = new IntNode(63, first);
        size = 1;
    }

    /**
     * Initialize an empty list
     */
    public SLList() {
        head = new IntNode(63, null);
        size = 0;
    }

    /**
     * 
     * @param x
     */
    public void addFirst(int x) {
        IntNode first = head.next;
        IntNode newFirst = new IntNode(x, first);
        head.next = newFirst;
        size += 1;
    }

    /**
     * 
     * @return
     */
    public int getFirst() {
        return isEmpty() ? -1 : head.next.item;
    }

    /**
     * Linear time
     * @param x
     * @return
     */
    private IntNode findithNode(int x) {
        // Argument check
        if ((x < 0) || (x >= size))
            throw new IndexOutOfBoundsException();
        
        IntNode p = head.next;
        while(x-- > 0)
            p = p.next;
        
        return p;

        // if (x == 0)
        //     return node;
        
        // return findithNode(node.next, x-1);
    }

    /**
     * 
     * @return
     */
    public int getLast() {
        return isEmpty() ? -1 : findithNode(size - 1).item;
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
     * @param x
     */
    public void addLast(int x) {
        IntNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size += 1;
    }

    /**
     * 
     * @param start
     * @param x
     */
    private void addLastNode(IntNode start, int x) {
        if (start.next == null)
            start.next = new IntNode(x, null);
        else
            addLastNode(start.next, x);
    }

    /**
     * 
     * @param x
     */
    public void addLastRecursively(int x) {
        addLastNode(head, x);
        size += 1;
    }

    /**
     * Constant time
     * @return
     */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLListWithEmpty list = new SLListWithEmpty(0);
        assert(list.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list.addFirst(i);
            assert(list.size() == i + 1);
            System.out.println("Element at the index of " + i + " is " + list.getFirst());
        }

        SLListWithEmpty list1 = new SLListWithEmpty(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLast(i);
            assert(list.size() == i + 1);
            assert(list1.getLast() == i);
        }

        SLListWithEmpty list2 = new SLListWithEmpty(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLastRecursively(i);
            assert(list.size() == i + 1);
            assert(list1.getLast() == i);
        }

        // Test empty list
        SLListWithEmpty emptyList = new SLListWithEmpty();
        assert(emptyList.size() == 0);
        assert(emptyList.isEmpty() == true);
        for (int i = 0; i < 10; ++i) {
            emptyList.addFirst(i);
            assert(emptyList.getFirst() == i);
            assert(emptyList.size() == i + 1);
            assert(emptyList.isEmpty() == false);
        }

        for (int i = 11; i < 30; ++i) {
            emptyList.addLast(i);
            assert(emptyList.size() == i);
            assert(emptyList.isEmpty() == false);
            assert(emptyList.getLast() == i) : "Expected last element " + i + " but found " + emptyList.getLast();
        }
    }
}
