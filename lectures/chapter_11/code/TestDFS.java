import edu.princeton.cs.algs4.StdOut;
public class TestDFS {

    public static void main(String args[]) {
        UndirectedGraph G = new UndirectedGraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch dfs = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); ++v) {
            if (dfs.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        if (dfs.count() != G.V()) {
            StdOut.print("NOT ");
        }

        StdOut.println("connected!");

        StdOut.println(G);
        StdOut.println("Number of selfloops: " + Graph.numberOfSelfLoops(G));
        StdOut.println("Max degree: " + Graph.maxDegree(G));
        StdOut.println("Avg degree: " + Graph.avgDegree(G));
    }
}
