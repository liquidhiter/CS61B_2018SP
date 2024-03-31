package hw2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int gridSize;  // grid size
    private int iterations;  // repeat times
    private double[] testData;     // open site ratio when system percolates

    /**
     *
     * @param p
     * @return
     */
    private int getBlockedSite(Percolation p) {
        int row = 0, col = 0;
        int rowColIndex = 0;
        do {
            rowColIndex = StdRandom.uniform(gridSize * gridSize);
            row = rowColIndex / gridSize;
            col = rowColIndex % gridSize;
        } while (p.isOpen(row, col));

        return rowColIndex;
    }

    /**
     * Run single test
     */
    private double testRun(Percolation p) {
        while (!p.percolates()) {
            /* Choose a site uniformly at random among all blocked sites */
            int rowColIndex = getBlockedSite(p);
            int row = rowColIndex / gridSize;
            int col = rowColIndex % gridSize;

            /* Open the site */
            p.open(row, col);
        }

        /* Logging for fun */
//        StdOut.println("[INFO] >>>>>> " +
//                "simulation is processing, the system percolates with open sites of " +
//                p.numberOfOpenSites());

        /* Calculate the test result */
        return p.numberOfOpenSites() / (double) (gridSize * gridSize);
    }

    /**
     * Main method to run the Monte Carlo Simulation
     */
    private void simulate(PercolationFactory pf) {
        for (int i = 0; i < iterations; ++i) {
            double openSitesFrac = testRun(pf.make(gridSize));
            testData[i] = openSitesFrac;
        }
    }

    /**
     *
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        /* Parameter validation */
        if (N <= 0) {
            throw new IllegalArgumentException("Grid size must be positive!");
        }

        if (T <= 0) {
            throw new IllegalArgumentException("Repeat times must be positive!");
        }

        this.gridSize = N;
        this.iterations = T;

        /* Initialize the test data array */
        testData = new double[T];

        /* Run simulation with the given Percolate instance */
        simulate(pf);
    }

    /**
     *
     * @return
     */
    public double mean() {
        return StdStats.mean(testData);
    }

    /**
     *
     * @return
     */
    public double stddev() {
        if (iterations == 1) {
            return Double.NaN;
        }

        return StdStats.stddev(testData);
    }

    /**
     *
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 / Math.sqrt(iterations) * stddev();
    }

    /**
     *
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 / Math.sqrt(iterations) * stddev();
    }

//    public static void main(String[] args) {
//        PercolationFactory pf = new PercolationFactory();
//        int N = 20;
//        int T = 1000000;
//        PercolationStats test = new PercolationStats(N, T, pf);
//        StdOut.println("********************************************* Experiment Result " +
//                "*********************************************");
//        StdOut.println("Grid size: " + N + "-by-" + N + '\n' + "Iterations: " + T);
//        StdOut.println("Test Results: ");
//        StdOut.println("             Mean threshold " + test.mean());
//        StdOut.println("             Standard deviation " + test.stddev());
//        StdOut.println("             confidence interval [" + test.confidenceLow() + ", "
//                + test.confidenceHigh() + "]");
//    }

}
