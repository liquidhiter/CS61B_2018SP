public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(UndirectedGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(UndirectedGraph G, int s) {
        marked[s] = true;
        count++;
        for (int w : G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            } /*else {
                return;
            }*/
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
