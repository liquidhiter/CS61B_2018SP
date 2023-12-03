package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last += 1;
        fillCount += 1;

        /* Wrap around the index of last */
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T retElem = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;

        /* Wrap around the index of first */
        if (first == capacity) {
            first = 0;
        }

        return retElem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("No item can peek from an empty Ring buffer!");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }

    private class RingBufferIterator implements Iterator<T> {
        private int nextIdx;
        private int cnt;

        public RingBufferIterator() {
            nextIdx = first;
            cnt = 0;
        }

        @Override
        public boolean hasNext() {
            return cnt < fillCount();
        }

        @Override
        public T next() {
            T nextItem = rb[nextIdx];
            nextIdx = (nextIdx + 1) % capacity;
            cnt += 1;
            return nextItem;
        }
    }
}
