package lectures.chapter_02.code;
public class SLListWithSizeCache {
    
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

    public SLListWithSizeCache(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /**
     * Initialize an empty list
     */
    public SLListWithSizeCache() {
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

    public boolean inEmpty() {
        return size() == 0;
    }

    /**
     * Iterative Implementation
     * @param x
     */
    public void addLast(int x) {
        IntNode p = first;
        while (p.next != null)
            p = p.next;
        
        p.next = new IntNode(x, null);
        size += 1;
    }

    /**
     *  Helper method to add a node to the last
     * @param start
     * @param x
     */
    private void addLastNode(IntNode start, int x) {
        if (start.next == null)
            start.next = new IntNode(x, null);
        else
            addLastNode(start.next, x);
    }

    public void addLastRecursively(int x) {
        addLastNode(first, x);
        size += 1;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SLListWithSizeCache list = new SLListWithSizeCache(0);
        assert(list.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list.addFirst(i);
            assert(list.size() == i + 1);
            System.out.println("Element at the index of " + i + " is " + list.getFirst());
        }

        SLListWithSizeCache list1 = new SLListWithSizeCache(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLast(i);
            assert(list.size() == i + 1);
            //TODO implement method used to get the last element and add test case here
        }

        SLListWithSizeCache list2 = new SLListWithSizeCache(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLastRecursively(i);
            assert(list.size() == i + 1);
            //TODO implement method used to get the last element and add test case here
        }
    }
}
