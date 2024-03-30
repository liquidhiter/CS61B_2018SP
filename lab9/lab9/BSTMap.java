package lab9;

import com.sun.jdi.Value;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            /* Update the value if key already exists */
            p.value = value;
        }

        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////
    private void keys(Node p, Set<K> keysSet) {
        if (p == null) {
            return;
        }

        /* InOrder */
        keys(p.left, keysSet);
        keysSet.add(p.key);
        keys(p.right, keysSet);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        keys(root, keySet);
        return keySet;
    }

    /**
     *
     * @param p
     * @return
     */
    private Node min(Node p) {
        if (p.left == null) {
            return p;
        }

        return min(p.left);
    }

    private Node remove(K key, Node root, V val, boolean isValMatched) {
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = remove(key, root.left, val, isValMatched);
        } else if (cmp > 0) {
            root.right = remove(key, root.right, val, isValMatched);
        } else {
            /* Do nothing if the value doesn't match only when value matters */
            if (isValMatched && !root.value.equals(val)) {
                return root;
            }

            /* Otherwise, remove the key-value pair from the tree */
            /* Don't forget to update the size */
            size -= 1;
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node tmp = root;
                /* Find the successor */
                root = min(root.right);
                /* node to be removed and value match doesn't matter for the successor node */
                root.right = remove(root.key, tmp.right, null, false);
                root.left = tmp.left;
            }
        }

        return root;
    }

    /**
     *
     * @param key
     * @param p
     * @return
     */
    private V removeHelper(K key, Node p, V val, boolean isValMatched) {
        V ret = get(key);
        root = remove(key, root, val, isValMatched);
        return isValMatched ? (ret.equals(val)) ? ret : null : ret;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        return removeHelper(key, root, null, false);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        return removeHelper(key, root, value, true);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
