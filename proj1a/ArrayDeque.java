public class ArrayDeque<AnyType> {
    private AnyType[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double ratio;
    private static final double usageFactor = 0.25;


    public ArrayDeque() {
        items = (AnyType []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (AnyType []) new Object[other.size()];
        size = other.size();
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        ratio = other.ratio;
        for (int i = 0; i < size; i++) {
            items[i] = (AnyType) other.get(i);
        }
    }

    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private void resize(int capacity) {
        AnyType[] a = (AnyType []) new Object[capacity];

        int x = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[x];
            x = plusOne(x);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void fixRatio() {
        ratio = 1.0 * size / items.length;
        if (size >= 16) {
            while (ratio < 0.25) {
                resize(size / 2);
            }
        }
    }

    public void addFirst(AnyType x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst);

        fixRatio();
    }

    public void addLast(AnyType x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast);

        fixRatio();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size() == 0) {
            System.out.println("Empty Array!");
        }

        int count = 0;
        int index = plusOne(nextFirst);
        while (true) {
            if (count == size) break;
            if (items[index] != null) {
                System.out.print(items[index] + " ");
                count += 1;
            }
            index = plusOne(index);
        }
        System.out.println();
    }

    public AnyType removeFirst() {
        AnyType returnValue = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        fixRatio();
        return returnValue;
    }

    public AnyType removeLast() {
        AnyType returnValue = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        fixRatio();
        return returnValue;
    }

    public AnyType get(int index) {
        if (index >= size || index < 0) return null;

        int i = 0;
        int returnIndex = plusOne(nextFirst);

        while (true) {
            if (i == index) break;
            if (items[returnIndex] != null) {
                i += 1;
                returnIndex = plusOne(returnIndex);
            }
        }
        return items[returnIndex];
    }

    public int getLength() {
        return items.length;
    }

    public double getRatio() {
        return ratio;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        int i = 0;
        while (i < 20) {
            a.addFirst(i);
            i += 1;
        }

        a.printDeque();
        System.out.println(a.getLength());
        System.out.println(a.getRatio());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        System.out.println(a.removeLast());
        a.printDeque();
        System.out.println(a.getLength());
        System.out.println(a.getRatio());
        System.out.println(a.removeLast());
    }

}
