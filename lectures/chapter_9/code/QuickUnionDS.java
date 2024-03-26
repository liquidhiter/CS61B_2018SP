public class QuickUnionDS implements DisjointSets {
    int[] parents;

    public QuickUnionDS(int N) {
        parents = new int[N];
        for (int i = 0; i < N; ++i) {
            parents[i] = -1;
        }
    }
    
    private int findRoot(int p) {
        while(parents[p] >= 0) {
            p = parents[p];
        }
        return p;
    }

    public void connect(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        /* q's root becomes p's */
        parents[rootQ] = rootP;
    }

    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public static void main(String[] args) {
        QuickUnionDS union = new QuickUnionDS(10);
        union.connect(1, 2);
        assert(union.isConnected(1, 2));
        union.connect(0, 1);
        assert(union.isConnected(0, 1));
        assert(union.isConnected(1, 2));

        union.connect(3, 5);
        assert(union.isConnected(3, 5));
        assert(!union.isConnected(0, 3));
        assert(!union.isConnected(2, 5));
        union.connect(2, 5);
        assert(union.isConnected(2, 5));
        assert(union.isConnected(4, 3));
    }
}
