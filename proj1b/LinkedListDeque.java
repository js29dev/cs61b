
public class LinkedListDeque<AnyType> implements Deque<AnyType> {
    private AnyNode sentinel;
    private int size;

    public class AnyNode {
        private AnyNode prev;
        private AnyType item;
        private AnyNode next;

        public AnyNode(AnyNode p, AnyType x, AnyNode n) {
            prev = p;
            item = x;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new AnyNode(null, (AnyType) "null", null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel.prev;
        size = 0;
    }

    public LinkedListDeque(AnyType x) {
        sentinel = new AnyNode(null, (AnyType) "null", null);
        sentinel.next = new AnyNode(sentinel.next, x, null);
        sentinel.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        sentinel.next.next = sentinel;
        size = 1;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new AnyNode(null, (AnyType) "null", null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel.prev;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((AnyType) other.get(i));
        }
    }

    @Override
    public void addFirst(AnyType x) {
        AnyNode helperNext = sentinel.next;
        sentinel.next = new AnyNode(sentinel.next, x, sentinel.next.next);
        sentinel.next.next = helperNext;
        helperNext.prev = sentinel.next;
        sentinel.next.prev = sentinel;
        size += 1;
    }

    @Override
    public void addLast(AnyType x) {
        AnyNode oldLast = sentinel.prev;
        oldLast.next = new AnyNode(oldLast, x, sentinel);
        AnyNode newLast = oldLast.next;
        sentinel.prev = newLast;
        newLast.prev = oldLast;
        newLast.next = sentinel;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        AnyNode first = sentinel.next;
        int i = 0;
        while (i < size) {
            System.out.print(first.item + " ");
            first = first.next;
            i += 1;
        }
        System.out.println("");
    }

    @Override
    public AnyType removeFirst() {
        if (size == 0) return null;

        AnyNode returnValue = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        sentinel.next.next = returnValue.next.next;
        size -= 1;
        return returnValue.item;
    }

    @Override
    public AnyType removeLast() {
        if (size == 0) return null;

        AnyNode lastToBeRemoved = sentinel.prev;
        AnyNode newLast = lastToBeRemoved.prev;
        newLast.next = sentinel;
        sentinel.prev = newLast;
        size -= 1;

        return lastToBeRemoved.item;
    }

    @Override
    public AnyType get(int index) {
        if (index >= size) return null;

        AnyNode helperFirst = sentinel.next;
        int i = 0;
        while (index > i) {
            helperFirst = helperFirst.next;
            i += 1;
        }
        return helperFirst.item;
    }

    public AnyType getRecursive(int index) {
        if (index >= size) return null;

        if (index == 0) {
            return sentinel.next.item;
        } else if (index == size() - 1) {
            return sentinel.prev.item;
        } else {
            LinkedListDeque<AnyType> copy = new LinkedListDeque<>(this);
            copy.removeFirst();
            return copy.getRecursive(index - 1);
        }

    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> test = new LinkedListDeque<>(5);
        test.addFirst(20);
        test.addLast(50);
        test.addLast(100);
        test.printDeque();
        System.out.println(test.getRecursive(2));
        test.printDeque();
    }

}