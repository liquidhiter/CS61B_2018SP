package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] sites; /* Open-ness */
    private WeightedQuickUnionUF unions; /* Top and bottom rows are connected, Connectedness */
    private WeightedQuickUnionUF full; /* Top rows are connected, should be only used by isFull */
    private int numOfSitesOpen;
    private int size; /* rows / columns */

    /**
     * Create an N-by-N grid with all sites initially blocked
     * @param N
     */
    public Percolation(int N) {
        numOfSitesOpen = 0;
        this.size = N;
        /* Is it a good practice to call functions / throw exceptions in the constructor */
        init(N);
    }

    /**
     * Helper method to initialize the system
     * @param N
     */
    private void init(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Grid size must be positive!");
        }

        sites = new boolean[N * N];
        /* All sites are blocked by default */
        for (int i = 0; i < N * N; i++) {
            sites[i] = false;
        }

        /* Virtual top site and bottom site to accelerate the isFull() and Percolate()  */
        unions = new WeightedQuickUnionUF(N * N);
        full = new WeightedQuickUnionUF(N * N);
        for (int i = 0; i < N - 1; ++i) {
            /* Connect the top and bottom rows */
            unions.union(xyToIndex(0, i), xyToIndex(0, i + 1));
            unions.union(xyToIndex(N - 1, i), xyToIndex(N - 1, i + 1));
            /* Connect the top rows only */
            full.union(xyToIndex(0, i), xyToIndex(0, i + 1));
        }
    }

    /**
     * Check the given row and index is within the size of grid
     * @assumption: grid is N by N
     * @param row
     * @param col
     */
    private void validateIndex(int row, int col) {
        if (row < 0 || row >= size) {
            throw new IndexOutOfBoundsException("Row index out of bound!");
        }

        if (col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Column index out of bound!");
        }
    }

    /**
     * Map (rol, col) to the index of the flatten one-dimension array
     * @param row
     * @param col
     * @return
     */
    private int xyToIndex(int row, int col) {
        validateIndex(row, col);
        return row * size + col;
    }

    /**
     * Open the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validateIndex(row, col);

        /* Do nothing if the site (row, col) is open already */
        if (isOpen(row, col)) {
            return;
        }

        /* Only increase the number of open sites when it is not open */
        sites[xyToIndex(row, col)] = true;
        numOfSitesOpen += 1;

        /* Connect to the neighbors */
        /* Up: (row + 1, col)
         * Bottom: (row - 1, col)
         * Left: (row, col - 1)
         * Right: (row, col + 1)
         */
        if (row > 0 && isOpen(row - 1, col)) {
            unions.union(xyToIndex(row, col), xyToIndex(row - 1, col));
            full.union(xyToIndex(row, col), xyToIndex(row - 1, col));
        }

        if (row < size - 1 && isOpen(row + 1, col)) {
            unions.union(xyToIndex(row, col), xyToIndex(row + 1, col));
            full.union(xyToIndex(row, col), xyToIndex(row + 1, col));
        }

        if (col > 0 && isOpen(row, col - 1)) {
            unions.union(xyToIndex(row, col), xyToIndex(row, col -  1));
            full.union(xyToIndex(row, col), xyToIndex(row, col - 1));
        }

        if (col < size - 1 && isOpen(row, col + 1)) {
            unions.union(xyToIndex(row, col), xyToIndex(row, col + 1));
            full.union(xyToIndex(row, col), xyToIndex(row, col + 1));
        }
    }

    /**
     * Is the site(row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return sites[xyToIndex(row, col)];
    }

    /**
     * Is the site(row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        /* O(1) */
        return isOpen(row, col) && full.connected(xyToIndex(row, col), 0);
    }

    /**
     * Number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return numOfSitesOpen;
    }

    /**
     * Does the system percolate?
     * @return
     */
    public boolean percolates() {
        /* Corner case: the system percolates only when the (0,0) is open for 1x1 grid */
        if (size == 1) {
            return isOpen(0, 0);
        }
        return unions.connected(0, xyToIndex(size - 1, size - 1));
    }

    public static void main(String[] args) {
//        Percolation test = new Percolation(5);
//        test.open(0, 2);
//        test.open(1, 2);
//        test.open(2, 2);
//        test.open(3, 2);
//        test.open(4, 2);
//        assert(test.percolates());
//
//        test.open(2, 4);
//        test.open(3, 4);
//        test.open(4, 4);
//        assert(!test.isFull(2, 4));
//        assert(!test.isFull(3, 4));
//        assert(!test.isFull(4, 4));

        // corner case
//        Percolation test = new Percolation(1);
//        System.out.println(test.percolates());
//        test.open(0, 0);
//        System.out.println(test.percolates());
    }
}
