import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B {
    private Node root;

    public class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public Object get(Object key) {
        return get(root, (K) key);
    }

    public V get(Node x, K key) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);

        if (compare < 0) return get(x.left, key);
        else if (compare > 0) return get(x.right, key);
        else return x.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    @Override
    public void put(Object key, Object value) {
        root = put(root, (K) key, (V) value);
    }

    public Node put(Node x, K key, V val) {
        if (x == null) return null;
        int compare = key.compareTo(x.key);
        if (compare < 0) {
            x.left = put(x.left, key, val);
        } else if (compare > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object remove(Object key, Object value) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
