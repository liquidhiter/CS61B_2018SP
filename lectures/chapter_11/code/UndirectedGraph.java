import edu.princeton.cs.algs4.Bag;

public class UndirectedGraph implements Graph {
    /* Number of vertices */
    private final int V;
    /* Number of edges */
    private int E;
    private Bag<Integer>[] adj;

    public UndirectedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        /* Initialize all llist */
        for (int i = 0; i < V; ++i) {
            adj[i] = new Bag<Integer>();
        }
    }

    public UndirectedGraph(In in) {
        /* Construct this object */
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        return convToString();
    }
}
