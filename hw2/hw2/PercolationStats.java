package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int N; // grid dimension N x N
    private int T; // number of trials
    private double[] results;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.T = T;
        this.results = new double[T];
        runTrials();
    }

    private void runTrials() {
        for (int i = 0; i < T; i++) {
            double openSites = 0;
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
                openSites += 1;
            }
            results[i] = openSites / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stdDev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stdDev() / Math.sqrt(T));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stdDev() / Math.sqrt(T));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(300, 100);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stdDev());
        System.out.println("interval = " + ps.confidenceLo() + " " + ps.confidenceHi());
    }
}
