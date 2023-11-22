public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. */
    public static IntList incrList(IntList L, int x) {
        if (L.size() == 1)
            return new IntList(L.first + x, null);

        IntList newList = new IntList(L.first + x, null);
        IntList pL = L.rest;
        IntList pNewList = newList;
        while (pL != null) {
            pNewList.rest = new IntList(pL.first + x, null);
            pL = pL.rest;
            pNewList = pNewList.rest;
        }

        return newList;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList p = L;
        while(p != null) {
            p.first += x;
            p = p.rest;
        }

        return L;
    }

    /**
     * Recursive implementation and non-mutative
     */
    public static IntList square(IntList L) {
        if (L == null)
            return null;
        
        IntList newList = new IntList(L.first * L.first, null);
        newList.rest = square(L.rest);
        return newList;
    }

    /**
     * Iterative implementation and non-mutative
     */
    public static IntList squareIterative(IntList L) {
        IntList newList = new IntList(L.first * L.first , null);
        IntList retList = newList;
        IntList p = L.rest;
        while (p != null) {
            newList.rest = new IntList(p.first * p.first, null);
            newList = newList.rest;
            p = p.rest;
        }

        return retList;
    }

    /**
     * Iterative and mutative implementation
     */
    public static IntList squareMutativeIterative(IntList L) {
        IntList p = L;
        while (p != null) {
            p.first *= p.first;
            p = p.rest;
        }

        return L;
    }

    /**
     * Recursive and mutative
     */
    public static IntList squareMutative(IntList L) {
        // dummy return due to the function prototype
        if (L == null)
            return null;
        
        L.first *= L.first;
        squareMutative(L.rest);
        
        return L;
    }


    public static void main(String[] args) {
        // IntList L = new IntList(15, null);
        // L = new IntList(10, L);
        // L = new IntList(5, L);

        // System.out.println(L.get(100));

        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        // System.out.println(L.get(1));
//        System.out.println(incrList(L, 3));
//        System.out.println(dincrList(L, 3));
        // IntList pL = L;
        // IntList incrListRet = incrList(L, 3);
        // while (pL != null) {
        //     assert (pL.first == incrListRet.first + 3);
        //     pL = pL.rest;
        //     incrListRet = incrListRet.rest;
        // }

        IntList squareOfL = squareIterative(L);
    }
}