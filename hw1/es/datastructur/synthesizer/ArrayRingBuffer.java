package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T []) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    private class RBIterator implements Iterator<T> {
        private int pos;

        public RBIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < capacity();
        }

        @Override
        public T next() {
            T returnItem = rb[pos];
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
        if (last == capacity()) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T returnValue = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;
        if (first == capacity()) {
            first = 0;
        }
        return returnValue;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        return rb[first];
    }

    public boolean contains(T item) {
        for (int i = 0; i < capacity(); i ++) {
            if (rb[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public T get(int i) {
        return rb[i];
    }

    @Override
    public Iterator<T> iterator() {
        return new RBIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof ArrayRingBuffer)){
            return false;
        }

        ArrayRingBuffer<T> compared = (ArrayRingBuffer<T>) o;
        if (this.capacity() != compared.capacity()) {
            return false;
        }

        for (int i = 0; i < capacity(); i++) {
            if (this.get(i) != compared.get(i)) {
                return false;
            }
        }
        return true;
    }
}

