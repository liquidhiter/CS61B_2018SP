public interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);

    default String convToString() {
        String s = this.V() + " vertices, " + this.E() + " edges\n";
        for (int v = 0; v < this.V(); v++) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    /*====================== Utility Functions ======================*/
    public static int degree(UndirectedGraph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(UndirectedGraph G) {
        int max = 0;
        for (int v = 0; v < G.V(); ++v) {
            int d = degree(G, v);
            if (d > max) {
                max = d;
            }
        }
        return max;
    }

    public static double avgDegree(UndirectedGraph G) {
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(UndirectedGraph G) {
        int count = 0;
        for (int v = 0; v < G.V(); ++v) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        /* Each edge is counted twice as this is a UndirectedGraph */
        return count >> 1;
    }
}
