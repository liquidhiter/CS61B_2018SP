import edu.princeton.cs.algs4.StdOut;

public class TestDFSPaths {

    private static void testDFSPaths(Graph G, int s, DepthFirstPaths dfsPath) {
        for (int v = 0; v < G.V(); ++v) {
            if (dfsPath.hasPathTo(v)) {
                StdOut.println("Has path to " + v);
                StdOut.println("Path to " + v + ": " + dfsPath.pathTo(v));
            } else {
                StdOut.println("No path to " + v);
            }
        }
    }
    public static void main(String[] args) {
        StdOut.println("======================= Undirected Graph Based on Bag =======================");
        Graph G = new UndirectedGraphOrdered(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfsPath = new DepthFirstPaths(G, s);
        testDFSPaths(G, s, dfsPath);

        StdOut.println("======================= Undirected Graph Based on Priority Queue =======================");
        G = new UndirectedGraph(new In(args[0]));
        s = Integer.parseInt(args[1]);
        dfsPath = new DepthFirstPaths(G, s);
        testDFSPaths(G, s, dfsPath);
    }
}
