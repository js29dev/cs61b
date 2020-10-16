public class BubbleGrid {
    private int[][] grid;

    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    public int[] popBubbles(int[][] darts) {

        return new int[]{0, 0};
    }

    public boolean isStuck(int[] dartPos) {
        int x = dartPos[0];
        int y = dartPos[1];
        if (grid[x][y] != 1) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        return false;
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
        int[][] grid = new int[4][3];
        grid[0] = new int[]{1, 1, 0};
        grid[1] = new int[]{1, 0, 0};
        grid[2] = new int[]{1, 1, 0};
        grid[3] = new int[]{1, 1, 1};

        BubbleGrid bg = new BubbleGrid(grid);
        System.out.println(bg);
    }

}
