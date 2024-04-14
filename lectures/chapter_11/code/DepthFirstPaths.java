import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstPaths implements Paths {
    private boolean[] marked;
    private int[] edgeTo; // the last node in the path from s to v
    private final int s; // start node

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    /**
     *
     * @param G
     * @param v
     */
    private void dfs(Graph G, int v) {
        /* Mark the current vertex */
        marked[v] = true;
        /* Iterate all current vertex's children */
        for (int w : G.adj(v)) {
            /* Do nothing if the vertex has been visited already */
            if (!marked[w]) {
                /* Record the father node to w the first time it is visited */
                edgeTo[w] = v;
                /* Next search from the child node */
                dfs(G, w);
            }
        }
    }

    /**
     * @param v 
     * @return
     */
    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * @param v 
     * @return
     */
    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> paths = new Stack<>();
        for (int x = v; x != s; x = edgeTo(x)) {
            paths.push(x);
        }
        paths.push(s);

        return paths;
    }

    private int edgeTo(int x) {
        return edgeTo[x];
    }
}
