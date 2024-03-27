import java.util.Random;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /**
     * Return a empty BST
     */
    public BST() {
        root = null;
    }

    /**
     * @return true if the BST is empty otherwise false
     */
    public Boolean empty() {
        return size() == 0;
    }

    /**
     * 
     * @return
     */
    public int size() {
        return size(root);
    }

    /**
     * 
     * @param n
     * @return
     */
    private int size(Node n) {
        if (n == null) {
            return 0;
        } else {
            return n.N;
        }
    }

    /**
     * 
     * @param k
     * @return
     */
    public Value get(Key k) {
        return get(root, k);
    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */
    private Value get(Node n, Key k) {
        if (n == null) {
            return null;
        }

        if (k.compareTo(n.key) > 0) {
            return get(n.right, k);
        } else if (k.compareTo(n.key) < 0) {
            return get(n.left, k);
        } else {
            return n.val;
        }
    }

    /**
     * 
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * Return the root node after inserting the new key-value pair
     *
     * @param n
     * @param k
     * @param v
     * @return
     */
    private Node put(Node n, Key k, Value v) {
        if (n == null) {
            return new Node(k, v, 1);
        }
        
        int cmp = k.compareTo(n.key);
        if (cmp > 0) {
            n.right = put(n.right, k, v);
        } else if (cmp < 0) {
            n.left = put(n.left, k, v);
        } else {
            /* Update the value if the key exists */
            n.val = v;
        }

        /* Update the size of subtree */
        n.N = size(n.left) + size(n.right) + 1;

        return n;
    }

    /**
     * 
     * @return
     */
    public Key min() {
        return root == null ? null : min(root).key;
    }

    /**
     * 
     * @param n
     * @return
     */
    private Node min(Node n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    /**
     * 
     * @return
     */ 
    public Key max() {
        return root == null ? null : max(root).key;
    }

    /**
     * 
     * @param n
     * @return
     */
    private Node max(Node n) {
        if (n.right == null) {
            return n;
        }
        return max(n.right);
    }

    /**
     * 
     * @param k
     * @return
     */
    public Key floor(Key k) {
        return floor(root, k).key;
    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */
    private Node floor(Node n, Key k) {
        if (n == null) {
            return null;
        }

        int cmp = k.compareTo(n.key);
        if (cmp == 0) {
            return n;
        }

        if (cmp < 0) {
            return floor(n.left, k);
        }
        /*
         * floor of k can exist in the right subtree only when there is a key not larger than the
         * given key otherwise, the current root (key > root) is the floor
         */
        Node tmp = floor(n.right, k);
        return tmp == null ? n : tmp;
    }

    /**
     * 
     * @param k
     * @return
     */
    public Key ceiling(Key k) {
        return ceiling(root, k).key;
    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */
    private Node ceiling(Node n, Key k) {
        if (n == null) {
            return null;
        }

        int cmp = k.compareTo(n.key);
        if (cmp == 0)
            return n;
        if (cmp > 0)
            return floor(n.right, k);

        /*
         * ceiling of k can exist in the left subtree only when there is a key not smaller than the
         * given key otherwise, the current root (key < root) is the ceiling
         */
        Node tmp = floor(n.left, k);
        return tmp == null ? n : tmp;
    }

    /**
     * 
     * @param k
     * @return
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */
    private Node select(Node n, int k) {
        if (n == null) {
            return null;
        }

        /*
         * left subtree of the current node contains N nodes, 0...k-1, the current node is the kth
         * one
         *
         * left subtree contains more than k nodes: the kth node must exist in the left subtree
         *
         * left subtree contains fewer than k nodes: the kth node must exist in the right subtree
         * NOTE: all nodes in the right subtree contains the key larger than the left subtree that's
         * to say, we only need to find the k - size(left subtree) - 1 th node
         */
        int sizeOfLeft = size(n.left);
        if (sizeOfLeft == k) {
            return n;
        } else if (sizeOfLeft > k) {
            return select(n.left, k);
        } else {
            return select(n.right, k - sizeOfLeft - 1);
        }
    }

    /**
     * 
     * @param k
     * @return
     */
    public int rank(Key k) {
        return rank(root, k);
    }

    /**
     * Return the rank of the given key in the tree grows from the tree node n
     * NOTE: if the given key doesn't exist in the tree, its rank as if it is inserted
     *       returns. In general, rank always return the rank of the given key correctly
     * @param n
     * @param k
     * @return
     */
    private int rank(Node n, Key k) {
        if (n == null) {
            return 0;
        }

        int cmp = k.compareTo(n.key);
        if (cmp > 0) {
            /*
             * Case 1: when the key is larger than the current root node's key which means the key
             * must exist in the right subtree and rank n.left.N + 1 + x
             */
            return size(n.left) + 1 + rank(n.right, k);
        } else if (cmp < 0) {
            /*
             * Case 2: when the key is smaller than the current root node's key which means the key
             * must exist in the left subtree
             */
            return rank(n.left, k);
        } else {
            /*
             * Case 3: when the key is the same as the current root node's key which means the key
             * ranks n.left
             */
            return size(n.left);
        }
    }

    /**
     * Delete the node with the minimum key
     */
    public void deleteMin() {
        if (empty()) {
            throw new RuntimeException("Empty BST!");
        }

        root = deleteMin(root);
    }

    /**
     * Helper function
     */
    private Node deleteMin(Node n) {
        if (n.left == null) {
            return n.right;
        }

        n.left = deleteMin(n.left);

        /* Update the size of all subtrees from the bottom to the top */
        n.N = size(n.left) + size(n.right) + 1;

        /* Returns the root node with the minimum key deleted */
        return n;
    }

    /**
     * 
     */
    public void deleteMax() {
        if (empty()) {
            throw new RuntimeException("Empty BST!");
        }

        root = deleteMax(root);
    }

    /**
     * 
     * @param n
     * @return
     */
    private Node deleteMax(Node n) {
        if (n.right == null) {
            return n.left;
        }

        n.right = deleteMax(n.right);

        /* Update the size of all subtrees from the bottom to the top */
        n.N = size(n.left) + size(n.right) + 1;

        /* Returns the root node with the minimum key deleted */
        return n;
    }

    /**
     * Delete the node with the given key
     * Cases:
     * 1> node to be deleted is a leaf node
     * 2> node to be deleted is a parent node (has left or right non-null node)
     * 3> node to be deleted is a root node (left and right non-null nodes)
     * @param k
     */
    public void delete(Key k) {
        if (empty()) {
            throw new RuntimeException("Empty BST!");
        }

        root = delete(root, k);
    }

    /**
     * Helper function. See the comments for delete(Key k)
     * @param n
     * @param k
     * @return
     */
    private Node delete(Node n, Key k) {
        if (n == null) {
            return null;
        }

        int cmp = k.compareTo(n.key);
        if (cmp > 0) {
            /* Key exists in the right subtree */
            n.right = delete(n.right, k);
        } else if (cmp < 0) {
            /* Key exist in the left subtree */
            n.left = delete(n.left, k);
        } else {
            /* the current node is the one to be deleted */
            // case 1 & 2: leaf node or parent nodes
            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            // case 3: root node
            } else  {
                /* First save the current node */
                Node root = n;

                /* Find the successor node - minimum in the right subtree */
                n = min(n.right);

                /* Remember to delete the successor node from the right subtree */
                n.right = delete(root.right, n.key);

                /* Remember to update the left subtree */
                n.left = root.left;

                /* Returned on line 433 */
            }
        }

        /* Update the tree sizes */
        n.N = size(n.left) + size(n.right) + 1;

        return n;
    }

    /**
     * Print the BST in inorder
     */
    public void printInOrder() {
        printInOrder(root);
    }

    /**
     * 
     * @param n
     */
    private void printInOrder(Node n) {
        if (n == null) {
            return;
        }

        /* Print the left subtree */
        printInOrder(n.left);
        /* Print the root node */
        StdOut.println(n.key);
        /* Print the right subtree */
        printInOrder(n.right);
    }

    /**
     * 
     * @return
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * 
     * @param lo
     * @param hi
     * @return
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * !NOTE: first left subtree, then root node, last right subtree (InOrder)
     * @param n
     * @param queue
     * @param lo
     * @param hi
     */
    private void keys(Node n, Queue<Key> queue, Key lo, Key hi) {
        if (n == null) {
            return;
        }

        int cmpToLo = lo.compareTo(n.key);
        int cmpToHigh =hi.compareTo(n.key);
        /* Case 1: if lo is smaller than the current node's key, go to the left subtree */
        if (cmpToLo < 0) {
            keys(n.left, queue, lo, hi);
        }
        /* Case 2: if lo <= current node's key <= hi, add the current node's key to the queue */
        if (cmpToLo <= 0 && cmpToHigh >= 0) {
            queue.enqueue(n.key);
        }
        /* Case 3: if hi is larger than the current node's key, go to the right subtree */
        if (cmpToHigh > 0) {
            keys(n.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {
        System.out.println(
            "************************** Test BST.java **************************\n"
        );


        BST<String, Integer> bst = new BST<>();

        System.out.print("/* m */\n");
        bst.put("m", 0);
        assert(bst.size() == 1);
        assert(!bst.empty());
        bst.deleteMin();
        assert(bst.empty() == true);
        System.out.println("PASS!");

        System.out.println(
        "/**\n" +
        " *     m\n"+
        " *    /\n"+
        " *   e\n"+
        " *");
        bst.put("m", 0);
        bst.put("e", 1);
        assert(bst.size() == 2);
        assert(bst.empty() == false);
        bst.deleteMin();
        assert(bst.empty() == false);
        assert(bst.size() == 1);
        assert(bst.min() == "m");
        System.out.println("PASS!");

        System.out.println(
        "/**\n"       +
        " *     m\n"  +
        " *    /\n"   +
        " *   e\n"    +
        " *    \\\n"   +
        " *     f\n"  +
        " */");
        bst = new BST<>();
        bst.put("m", 0);
        bst.put("e", 1);
        bst.put("f", 2);
        assert(bst.size() == 3);
        bst.deleteMin();
        assert(bst.size() == 2);
        assert(bst.min() == "f");
        System.out.println("PASS!");

        System.out.println(
        "/**\n"     +
        " *     m\n" +
        " *      \\\n" +
        " *       p\n" +
        " */");
        bst = new BST<>();
        bst.put("m", 0);
        bst.put("p", 1);
        assert(bst.size() == 2);
        assert(bst.min() == "m");
        bst.deleteMin();
        assert(bst.size() == 1);
        assert(bst.min() == "p");
        System.out.println("PASS!");

        /* Generate a random BST and print it */
        BST<Character, Integer> bstInOrder = new BST<>();
        Random keyGen = new Random();
        // keyGen.setSeed(0x12345678);

        for (int j = 0; j < 2; ++j) {
            StdOut.println("====================== Iteration " + j + " ======================");
            int keyBaseVal = (int)'A';
            int keyRange = (int)('Z' - 'A' + 1);
            for (int i = 0; i < keyRange; ++i) {
                char key = (char)((keyGen.nextInt(1024) % keyRange) + keyBaseVal);
                bstInOrder.put(key, keyGen.nextInt());
            }
            assert(bstInOrder.size() == keyRange);
            // bstInOrder.printInOrder();

            for (char key : bstInOrder.keys()) {
                StdOut.print(key + " ");
            }
            StdOut.println('\n');
        }
    }
}
