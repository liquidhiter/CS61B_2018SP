public class SLListWithEmpty {
    
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
    private IntNode first;
    private int size;

    /**
     * 
     * @param x
     */
    public SLListWithEmpty(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /**
     * Initialize an empty list
     */
    public SLListWithEmpty() {
        first = null;
        size = 0;
    }

    /**
     * 
     * @param x
     */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    /**
     * 
     * @return -1 if the list is empty otherwise return the first item
     */
    public int getFirst() {
        // return first.item;
        if (first == null)
            return -1;
        
        return first.item;
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
        
        IntNode p = this.first;
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
        if (first == null)
            return -1;
        
        // return findithNode(size - 1).item;
        
        // Worth of checking the last node is non-null ?
        IntNode last = findithNode(size - 1);
        assert(last != null);
        
        return last.item;
    }


    /**
     * 
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Helper method to append a node to a non-empty list
     * @param x
     */
    private void addLastToNonEmptyList(int x) {
        IntNode p = first;
        while (p.next != null)
            p = p.next;
        
        p.next = new IntNode(x, null);
    }

    //!NOTE: works on the empty list
    /**
     * 
     * @param x
     */
    public void addLast(int x) {
        // Actually same as addFirst when first is null (empty list)
        if (first == null) {
            first = new IntNode(x, null);
        } else {
            addLastToNonEmptyList(x);
        }

        size += 1;
    }

    /**
     * 
     * @param start
     * @param x
     */
    private void addLastNodeToNonEmptyList(IntNode start, int x) {
        if (start.next == null)
            start.next = new IntNode(x, null);
        else
            addLastNodeToNonEmptyList(start.next, x);
    }

    /**
     * 
     * @param x
     */
    public void addLastRecursively(int x) {
        if (first == null) {
            first = new IntNode(x, null);
        } else {
            addLastNodeToNonEmptyList(first, x);
        }

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
