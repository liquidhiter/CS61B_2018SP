import java.util.PriorityQueue;
import java.util.Queue;

public class UndirectedGraphOrdered implements Graph {
    private final int V; // number of vertices
    private int E; // number of edges
    private Queue<Integer>[] adj; // adjacent nodes connected

    public UndirectedGraphOrdered(int v) {
        this.V = v;
        this.E = 0;
        adj = (PriorityQueue<Integer>[]) new PriorityQueue[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new PriorityQueue<>();
        }
    }

    public UndirectedGraphOrdered(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; ++i) {
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
