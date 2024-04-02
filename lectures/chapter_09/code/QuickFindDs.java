package lectures.chapter_09.code;
public class QuickFindDs implements DisjointSets {
    
    private int[] id;
    
    public QuickFindDs(int N) {
        id = new int[N];
        for (int i = 0; i < N; ++i) {
            id[i] = -1;
        }
    }

    /**
     * Connect p with q
     */
    public void connect(int p, int q) {
        // int prevId = id[p];
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; ++i) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public boolean isConnected(int p, int q) {
        return id[p] == id[q];
    }

    public static void main(String[] args) {
        QuickFindDs q = new QuickFindDs(10);
        q.connect(4, 3);
        q.connect(3, 8);
        assert(q.isConnected(4, 8));
        assert(q.isConnected(3, 8));
        assert(q.isConnected(3, 4));
        System.out.println(q.isConnected(4, 8));
    }
}
