public class IntList {
    public int first;
    public IntList rest;

    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    /**
     * 
     * @param x
     */
    public void addFirst(int x) {
        IntList nextToFirst = new IntList(first, rest); // Save a copy of the first and rest
        first = x;
        this.rest = nextToFirst;
    }
    
    /**
     * 
     * @return
     */
    public int getFirst() {
        return first;
    }

    /**
     * 
     * @return
     */
    public int size() {
        if (this.rest == null)
            return 1;

        return 1 + this.rest.size();
    }

    /**
     * 
     * @return
     */
    public int IterativeSize() {
        int size = 0;
        IntList next = this;
        while (next != null) {
            size += 1;
            next = next.rest;
        }
        return size;
    }

    /**
     * 
     * @param i
     * @return
     */
    public int IterativeGet(int i) {
        int size = this.size();
        assert(i >= 0) : "Illegal index: index must be non-negative";
        assert(i < size) : "Illegal index: index out of range";

        IntList pList = this;
        while(i-- > 0)
            pList = pList.rest;
        
        return pList.first;
    }

    /**
     * 
     * @param i
     * @return
     */
    public int get(int i) {
        if (i == 0)
            return first;
        
        return rest.get(i - 1);
    }

    public static void main(String[] args) {
        IntList L = new IntList(0, null);
        assert(L.size() == 1);
        assert(L.getFirst() == 0);

        L.addFirst(1);
        assert(L.getFirst() == 1);
        assert(L.size() == 2);

        L.addFirst(2);
        assert(L.getFirst() == 2);
        assert(L.size() == 3);
        
        L.addFirst(3);
        assert(L.getFirst() == 3);
        assert(L.size() == 4);

        for (int i = 0; i <= 3; ++i) {
            assert(L.get(i) == i);
            assert(L.IterativeGet(i) == i);
        }
    }
}
