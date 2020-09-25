package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEmpty() {
        ArrayRingBuffer<Integer> ints = new ArrayRingBuffer<>(5);
        int expected = 0;
        int actual = ints.fillCount();
        assertEquals(expected, actual);
    }

    @Test
    public void testFull() {
        ArrayRingBuffer<Integer> ints = new ArrayRingBuffer<>(5);
        ints.enqueue(1);
        ints.enqueue(1);
        ints.enqueue(1);
        ints.enqueue(1);
        ints.enqueue(1);
        assertEquals(5, ints.capacity());
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Integer> ints = new ArrayRingBuffer<>(5);
        ints.enqueue(1);
        ints.enqueue(2);
        ints.enqueue(3);
        ints.enqueue(4);
        ints.enqueue(5);
        assertEquals(1, (int) ints.peek());
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> ints = new ArrayRingBuffer<>(5);
        ints.enqueue(1);
        ints.enqueue(2);
        ints.enqueue(3);
        ints.enqueue(4);
        ints.enqueue(5);
        assertEquals(1, (int) ints.dequeue());
    }

}
