/*
 * Rational behind the DLList:
 *  
 * public void addLast(int x) {
 *    size += 1;
 *   IntNode p = sentinel;
 *   while (p.next != null) {
 *       p = p.next;
 *   }
 *
 *   p.next = new IntNode(x, null);
 * }
 * 
 * the method above takes O(n) time which is slow
 * One solution is to add another sentinel node to make the last node easy to track
 */

public class DLListDraft {

    // Why static: make the method of the outer class DLList non-accessible by IntNode
    private static class IntNode {
        int item;
        IntNode next;

        IntNode(int x, IntNode n) {
            this.item = x;
            this.next = n;
        }
    }

    //================== Implementation of DLList ====================//
    /* Two sentinel nodes */
    private IntNode head;
    private IntNode tail;
    private int size;


    public DLListDraft(int x) {
        // 63 used as the special value of the head node
        // IntNode first = new IntNode(x, null);
        // head = new IntNode(63, first);
        // tail = new IntNode(64, first);

        head = new IntNode(63, null);
        head.next = new IntNode(x, null);
        tail = new IntNode(64, null);
        tail.next = head.next;
        size = 1;
    }

    public DLListDraft() {
        head = new IntNode(63, null);
        tail = new IntNode(64, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * constant time
     * @param x
     */
    public void addLast(int x) {
        assert(tail != null);
        tail.next = new IntNode(x, null);
        tail = tail.next;
        size += 1;
    }

    /**
     * constant time
     * @return
     */
    public int getLast() {
        return tail.item;
    }

    /**
     * O(N)
     */
    public void removeLast() {
        assert(isEmpty() == false) : "No element to be removed";
        
        // Find the second to last node
        IntNode p = head;
        while (p.next.next != null)
            p = p.next;
        p.next = null;
        tail = p;

        size -= 1;
    }

    // public String toString() {
    //     String s = "";
    //     IntNode p = head.next;
    //     while (p != null) {
    //         s += p.item + ",";
    //         p = p.next;
    //     }

    //     return s;
    // }

    public static void main(String[] args) {
        // DLList emptyList = new DLList();
        // assert(emptyList.size() == 0);
        
        // // Basic tests of the \addLast and \getLast methods
        // for (int i = 0; i < 10; ++i) {
        //     emptyList.addLast(i);
        //     assert(emptyList.size() == i + 1);
        //     assert(emptyList.getLast() == i);
        // }

        // System.out.println(emptyList);

        // Basic tests of the \removeLast method
        // for (int i = 10; i > 0; --i) {
        //     assert(emptyList.getLast() == i - 1);
        //     emptyList.removeLast();
        //     assert(emptyList.size() == i - 1);
        //     assert(emptyList.getLast() == i - 2);
        // }
    }
}
