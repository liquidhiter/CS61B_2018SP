public class WeightedQuickUnionWithPathCompression implements DisjointSets{
    int parents[];
    int sizes[];

    public WeightedQuickUnionWithPathCompression(int N) {
        parents = new int[N];
        sizes = new int[N];
        for (int i = 0; i < N; ++i) {
            parents[i] = -1;
            sizes[i] = 1;
        }
    }

    public int findRoot(int p) {
        /* Find the root */
        if (parents[p] < 0)
            return p;
        
        int root = findRoot(parents[p]);
        parents[p] = root;

        return root;
    }

    public void connect(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        /* already connected */
        if (rootP == rootQ) return;

        if (sizes[rootP] < sizes[rootQ]) {
            parents[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            parents[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
    }

    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public static void main(String[] args) {
        WeightedQuickUnionWithPathCompression union = new WeightedQuickUnionWithPathCompression(16);
        union.connect(3, 10);
        union.connect(0, 3);
        union.connect(0, 4);
        union.connect(8, 14);
        union.connect(2, 8);
        union.connect(2, 9);
        union.connect(0, 2);
        union.connect(11, 15);
        union.connect(5, 11);
        union.connect(5, 12);
        union.connect(6, 13);
        union.connect(1, 5);
        union.connect(1, 6);
        union.connect(1, 7);
        union.connect(0, 1);
    }
}
