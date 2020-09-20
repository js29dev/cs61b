public interface Deque<AnyType> {
    void addFirst(AnyType item);
    void addLast(AnyType item);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();
    void printDeque();
    AnyType removeFirst();
    AnyType removeLast();
    AnyType get(int index);
}
