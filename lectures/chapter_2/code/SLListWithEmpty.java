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
     * 
     * @return
     */
    public boolean inEmpty() {
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
            //TODO implement method used to get the last element and add test case here
        }

        SLListWithEmpty list2 = new SLListWithEmpty(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLastRecursively(i);
            assert(list.size() == i + 1);
            //TODO implement method used to get the last element and add test case here
        }

        // Test empty list
        SLListWithEmpty emptyList = new SLListWithEmpty();
        assert(emptyList.size() == 0);
        assert(emptyList.inEmpty() == true);
        for (int i = 0; i < 10; ++i) {
            emptyList.addFirst(i);
            assert(emptyList.getFirst() == i);
            assert(emptyList.size() == i + 1);
            assert(emptyList.inEmpty() == false);
        }
    }
}
