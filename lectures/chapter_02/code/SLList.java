package lectures.chapter_02.code;
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
    private IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /**
     * 
     * @param x
     */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    /**
     * 
     * @return
     */
    public int getFirst() {
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
    }

    /**
     * Helper method to get the number of nodes in the SLList
     * @return
     */
    private int countNode(IntNode start) {
        if (start == null)
            return 1;
        
        return 1 + countNode(start.next);
    }

    /**
     * Recursive Implementation
     * TODO: Move the helper method in the size() with lambda functions (closure is the king)
     * @return
     */
    public int size() {
        return countNode(first);
    }

    /**
     * 
     * @return
     */
    public int sizeIterative() {
        int size = 0;
        IntNode p = first;
        while (p != null) {
            size += 1;
            p = p.next;
        }

        return size;
    }

    public static void main(String[] args) {
        SLList list = new SLList(0);
        assert(list.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list.addFirst(i);
            assert(list.size() == i + 1);
            System.out.println("Element at the index of " + i + " is " + list.getFirst());
        }

        SLList list1 = new SLList(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLast(i);
            assert(list.size() == i + 1);
            //TODO implement method used to get the last element and add test case here
        }

        SLList list2 = new SLList(0);
        assert(list1.size() == 1);
        for (int i = 1; i <= 10; ++i) {
            list1.addLastRecursively(i);
            assert(list.size() == i + 1);
            //TODO implement method used to get the last element and add test case here
        }
    }
}
