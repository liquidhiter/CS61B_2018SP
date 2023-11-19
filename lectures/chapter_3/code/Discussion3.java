public class Discussion3 {
    private static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    public static class SLList {
        private IntNode first;

        /**
         * return an empty list
         */
        public SLList() {
            first = null;
        }

        /**
         *
         * @param x
         */
        public void addFirst(int x) {
            first = new IntNode(x, first);
        }

        /**
         * Assume the skeleton code of SLList is not allowed to extend
         * the implementation is really ugly...
         * @param item
         * @param position
         */
        public void insert(int item, int position) {
            if (position == 0) {
                addFirst(item);
            } else {
                IntNode p = first;
                int currPos = 0;
                boolean fondPos = false;
                while (p.next != null) {
                    if (currPos == position - 1) {
                        /* Found the valid position to insert the new node after */
                        fondPos = true;
                        break;
                    }
                    p = p.next;
                    currPos += 1;
                }

                if (fondPos) {
                    IntNode next = p.next;
                    p.next = new IntNode(item, next);
                } else {
                    /* position is larger than the number of existing nodes */
                    p.next = new IntNode(item, null);
                }
            }
        }

        /**
         * Iterative implementation of reverse method
         * Time Complexity: O(N)
         * no matter how the linked list is implemented: this is the best performance ?
         */
        public void reverseIterative() {
            IntNode prevNode = null;
            IntNode currNode = first;
            IntNode nextNode = null;
            while (currNode != null) {
                /* Save the next node used to iterate other nodes */
                nextNode = currNode.next;
                /* Points the current node to the previous node: reverse */
                currNode.next = prevNode;
                /* Continue on reversing other nodes */
                prevNode = currNode;
                /* Current node points to the next node */
                currNode = nextNode;
            }

            /* prevNode now points to the current node, i.e. the last node */
            first = prevNode;
        }

        /**
         * Helper function for reverseRecursive
         * TODO: not finished yet
         * @param node
         */
        public void reverseNodes(IntNode node, IntNode prevNode) {
            if (node == null) {
                return;
            }

            /*Save the original next node*/
            IntNode nextNode = node.next;
            /*Points the current node to the previous node*/
            node.next = prevNode;
            /*Update previous node for the next iteration*/
            prevNode = node;

            reverseNodes(nextNode, prevNode);
        }

  
        /**
         * Recursive implementation of the reverse method
         * Time Complexity:
         */
        public void reverseRecursive() {
            reverseNodes(first, null);
        }

        /**
         * The following implementation is not generally correct
         * it is only created for testing other methods
         * @param position
         * @return
         */
        public int get(int position) {
            /* Assume there are always more elements */
            IntNode p = first;
            for (int i = 0; i < position; ++i) {
                p = p.next;
            }
            return p.item;
        }

        public static void main(String[] args) {
            Discussion3.SLList list2 = new Discussion3.SLList();
            list2.addFirst(2);
            list2.addFirst(6);
            list2.addFirst(10);
            list2.addFirst(5);
            list2.reverseRecursive();
        }

    }
}
