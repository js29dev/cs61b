public class UnionFind {
    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception {
        if (vertex < 0 || vertex >= parent.length) {
            throw new Exception("Invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -1 * parent[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size1 <= size2) {
            parent[root1] = root2;
            parent[root2] -= size1;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == v1) {
                    parent[i] = root2;
                }
            }
        } else {
            parent[root2] = root1;
            parent[root1] -= root2;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == v1) {
                    parent[i] = root2;
                }
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int b = vertex;
        while (parent[b] >= 0) {
            b = parent[b];
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < parent.length; i++) {
            sb.append(parent[i]);
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        System.out.println(uf);
        uf.union(3, 4);
        System.out.println(uf);
        System.out.println(uf.sizeOf(4));
        uf.union(2, 3);
        System.out.println(uf);
        System.out.println(uf.sizeOf(4));
        uf.union(0,1);
        System.out.println(uf);
        uf.union(1, 4);
        System.out.println(uf);
        System.out.println(uf.sizeOf(4));
    }

}
