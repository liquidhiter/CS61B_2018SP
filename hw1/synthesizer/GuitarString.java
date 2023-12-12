package synthesizer;

import java.util.Set;
import java.util.HashSet;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    private void initBufferWithConst(double initVal) {
        while (!buffer.isFull()) {
            buffer.enqueue(initVal);
        }
    }

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        initBufferWithConst(0.0);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int volume = buffer.capacity();
        Set<Double> randNums = new HashSet<>();
        for (int i = 0; i < volume; ++i) {
            randNums.add(Math.random() - 0.5);
        }

        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }

        for (Double item : randNums) {
            buffer.enqueue(item);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        buffer.enqueue((buffer.dequeue() + sample()) * 0.5 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
