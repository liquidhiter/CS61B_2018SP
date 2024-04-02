package lectures.chapter_09.code;
public class WeightedQuickUnion implements DisjointSets {
    int parents[];
    int sizes[];

    public WeightedQuickUnion(int N) {
        parents = new int[N];
        sizes = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = -1;
            sizes[i] = 1;
        }
    }

    public int findRoot(int p) {
        while(parents[p] >= 0) {
            p = parents[p];
        }
        return p;
    }

    public void connect(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) return;
        
        if (sizes[rootP] < sizes[rootQ]) {
            parents[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            /* Including tie */
            parents[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
    }

    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
}
