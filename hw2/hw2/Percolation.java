package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] sites; /* Open-ness */
    private WeightedQuickUnionUF unions; /* Connectedness */
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

        /* O(N^2) */
        sites = new int[N * N];
        unions = new WeightedQuickUnionUF(N * N);
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
     *
     * @param row
     * @param col
     */
    private void connectLeft(int row, int col) {
        for (int i = col - 1; i >= 0 && isOpen(row, i); --i) {
            unions.union(xyToIndex(row, i + 1), xyToIndex(row, i));
        }
    }

    /**
     *
     * @param row
     * @param col
     */
    private void connectRight(int row, int col) {
        for (int i = col + 1; i <= size - 1 && isOpen(row, i); ++i) {
            unions.union(xyToIndex(row, i - 1), xyToIndex(row, i));
        }
    }

    /**
     *
     * @param row
     * @param col
     */
    private void connectBottom(int row, int col) {
        for (int i = row + 1; i <= size - 1 && isOpen(i, col); ++i) {
            unions.union(xyToIndex(i - 1, col), xyToIndex(i, col));
        }
    }

    /**
     *
     * @param row
     * @param col
     */
    private void connectUp(int row, int col) {
        for (int i = row - 1; i >= 0 && isOpen(i, col); --i) {
            unions.union(xyToIndex(i + 1, col), xyToIndex(i, col));
        }
    }

    /**
     * from top to bottom, left to right ?
     * @param row
     * @param col
     */
    private void connectNeighbours(int row, int col) {
        /* left / right / bottom / up */
        connectLeft(row, col);
        connectRight(row, col);
        connectBottom(row, col);
        connectUp(row, col);
    }

    /**
     * Open the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        validateIndex(row, col);

        /* Save the status of the site to open */
        if (!isOpen(row, col)) {
            sites[xyToIndex(row, col)] = 1;
            numOfSitesOpen += 1;
        }

        /* Connect its neighbours ? */
        connectNeighbours(row, col);
    }

    /**
     * Is the site(row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return sites[xyToIndex(row, col)] > 0;
    }

    /**
     * Is the site(row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        /* Conditions to be full:
         * 1> must be open
         * 2> any of the neighbours must be connected ? (be careful with the boundary)
         */

        /* First check if the site is open */
        if (!isOpen(row, col)) {
            return false;
        }

        /* O(N) */
        for (int i = 0; i < size; ++i) {
            if (unions.connected(xyToIndex(row, col), xyToIndex(0, i))) {
                return true;
            }
        }

        return false;
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
        /* Check if there is a full site at the bottom row */
        for (int i = 0; i < size; ++i) {
            if (isFull(size - 1, i)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
//        Percolation test = new Percolation(5);
//        test.open(4, 4);
//        test.open(3, 4);
//        test.open(2, 4);
//        test.open(2, 2);
//        test.open(2, 3);
//        test.open(0, 2);
//        test.open(1, 2);
//
//        test.isFull(2, 2);
    }
}
