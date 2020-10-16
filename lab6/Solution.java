import java.util.Arrays;

public class Solution {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int[][] grid;
    private int rows, cols;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;

        DisjointSet ds = new DisjointSet(rows * cols + 1);

        /** Mark cells to hit as 2. */
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) grid[hit[0]][hit[1]] = 2;
        }

        /** Union around 1 cells. */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j ++) {
                if (grid[i][j] == 1) unionAround(i, j, ds);
                this.toString();
            }
        }

        int numBricksLeft = ds.size[ds.find(0)]; // numBricksLeft after the last erasure.
        int i = hits.length - 1; // Index of erasure.
        int[] numBricksDropped = new int[hits.length]; // Number of bricks that will drop after each erasure.

        while (i >= 0) {
            int x = hits[i][0];
            int y = hits[i][1];
            if (grid[x][y] == 2) {
                grid[x][y] = 1; // Restore to last erasure.
                unionAround(x, y, ds);
                int newNumBricksLeft = ds.size[ds.find(0)];
                numBricksDropped[i] = Math.max(newNumBricksLeft - numBricksLeft - 1, 0); // Excluding the brick to erase.
                numBricksLeft = newNumBricksLeft;
            }
            i--;
        }

        return numBricksDropped;
    }

    private void unionAround(int x, int y, DisjointSet ds) {
        int curMark = mark(x, y);

        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && grid[nx][ny] == 1) {
                ds.union(curMark, mark(nx, ny));
            }
        }

        if(x == 0) ds.union(0, curMark); // Connect to the top of the grid.
    }

    public int mark(int x, int y) {
        return x * cols + y + 1;
    }

    public class DisjointSet {
        int[] parent, size;

        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) { // 0 indicates top of the grid.
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] grid = new int[4][3];
        grid[0] = new int[]{1, 1, 0};
        grid[1] = new int[]{1, 0, 0};
        grid[2] = new int[]{1, 1, 0};
        grid[3] = new int[]{1, 1, 1};

        int[][] hits = new int[2][2];
        hits[0] = new int[]{2, 2};
        hits[1] = new int[]{2, 0};

        int[] result = sol.hitBricks(grid, hits);
        System.out.println(result[0] + ", " + result[1]);
    }
}