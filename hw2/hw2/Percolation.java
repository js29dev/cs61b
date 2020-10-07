package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufNoVirtualBottom;
    private int virtualTop;
    private int virtualBot;

    public Percolation(int N) {
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufNoVirtualBottom = new WeightedQuickUnionUF(N * N + 1);
        virtualTop = N * N;
        virtualBot = N * N + 1;
        numberOfOpenSites = 0;
    }

    private void connectToOpenNeighbors(int row, int col) {
        int currentPos = xyTo1D(row, col);

        // check left neighbor
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            int left = xyTo1D(row, col - 1);
            uf.union(currentPos, left);
            ufNoVirtualBottom.union(currentPos, left);
        }

        // check right neighbor
        if (col + 1 < grid.length && isOpen(row, col + 1)) {
            int right = xyTo1D(row, col + 1);
            uf.union(currentPos, right);
            ufNoVirtualBottom.union(currentPos, right);
        }

        // check top neighbor
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            int top = xyTo1D(row - 1, col);
            uf.union(currentPos, top);
            ufNoVirtualBottom.union(currentPos, top);
        }

        // check bottom neighbor
        if (row + 1 < grid.length && isOpen(row + 1, col)) {
            int bot = xyTo1D(row + 1, col);
            uf.union(currentPos, bot);
            ufNoVirtualBottom.union(currentPos, bot);
        }
    }

    private int xyTo1D(int x, int y) {
        return x * grid.length + y;
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(row, col)) return;
        connectToOpenNeighbors(row, col);

        grid[row][col] = true;
        numberOfOpenSites += 1;

        if (row == 0) {
            uf.union(virtualTop, xyTo1D(row, col));
            ufNoVirtualBottom.union(virtualTop, xyTo1D(row, col));
        }

        if (row == grid.length - 1) {
            uf.union(virtualBot, xyTo1D(row, col));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }

        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }

        return uf.connected(virtualTop, xyTo1D(row, col)) &&
                ufNoVirtualBottom.connected(virtualTop, xyTo1D(row, col));
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBot);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
    }
}
